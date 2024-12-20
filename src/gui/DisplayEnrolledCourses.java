package gui;

import models.Student;
import utils.ExceptionUtility;
import models.Course;
import models.University;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DisplayEnrolledCourses extends JFrame {

    public DisplayEnrolledCourses(int WIDTH , int HEIGHT) {
        setTitle("Search Student by ID"); // Set the title of the window
        setSize(WIDTH, HEIGHT); // Set the size of the window
        setLayout(new GridLayout(3, 2)); // Set the layout manager

        JLabel idLabel = new JLabel("Student ID: "); // Label for student ID
        JTextField idField = new JTextField(); // Text field for student ID

        JButton searchButton = new JButton("Search"); // Button to search for the student
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int studentID = ExceptionUtility.parseStudentID(idField.getText()); // Parse student ID from text field
                    List<Student> allStudents = University.studentRepository.getAll(); // Retrieve all students from the repository
                    Student student = null;
                    for (Student s : allStudents) {
                        if (s.getStudentID() == studentID) {
                            student = s; // Find the student with the matching ID
                            break;
                        }
                    }
                    if (student == null) {
                        throw new ExceptionUtility.InvalidInputException("Student not found!"); // Throw exception if student not found
                    }
                    List<Course> enrolledCourses = student.getEnrolledCourses(); // Get the list of courses the student is enrolled in
                    showEnrolledCourses(enrolledCourses); // Display the enrolled courses
                } catch (ExceptionUtility.InvalidInputException ex) {
                    JOptionPane.showMessageDialog(DisplayEnrolledCourses.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); // Show error message if an exception occurs
                }
            }
        });

        // Add components to the form
        add(idLabel);
        add(idField);
        add(new JLabel()); // Empty cell
        add(searchButton);

        setVisible(true); // Make the form visible
    }

    private void showEnrolledCourses(List<Course> courses) {
        JFrame resultsFrame = new JFrame("Filtered Courses"); // Create a new frame for displaying enrolled courses
        resultsFrame.setSize(600, 400); // Set the size of the window
        resultsFrame.setLayout(new BorderLayout()); // Set the layout manager

        String[] columnNames = {"Course ID", "Title", "Credits"}; // Define column names for the table
        Object[][] data = new Object[courses.size()][3]; // Create a 2D array to hold the course data

        // Populate the data array with course information
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            data[i][0] = course.getCourseID(); // Set course ID
            data[i][1] = course.getCourseName(); // Set course name
            data[i][2] = course.getCourseCredits(); // Set course credits
        }

        // Create a table with the course data and column names
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table); // Add the table to a scroll pane
        resultsFrame.add(scrollPane, BorderLayout.CENTER); // Add the scroll pane to the center of the window

        resultsFrame.setVisible(true); // Make the window visible
    }
}