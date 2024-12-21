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

    // Constructor to initialize the DisplayEnrolledCourses frame
    public DisplayEnrolledCourses(int WIDTH, int HEIGHT) {
        setTitle("Search Student by ID");
        setSize(800, 600);
        setLayout(null);

        // Back button to close the frame
        JButton backButton = new JButton("Back");
        backButton.setBounds(700, 10, 80, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Label and text field for entering the student ID
        JLabel idLabel = new JLabel("Student ID:");
        idLabel.setBounds(50, 60, 500, 30);
        JTextField idField = new JTextField();
        idField.setBounds(150, 60, 500, 30);

        // Search button to trigger the search action
        JButton searchButton = new JButton("Search");
        searchButton.setBounds(330, 100, 100, 30);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Parse and validate the student ID
                    int studentID = ExceptionUtility.parseStudentID(idField.getText());
                    List<Student> allStudents = University.studentRepository.getAll();
                    Student student = null;
                    for (Student s : allStudents) {
                        if (s.getStudentID() == studentID) {
                            student = s;
                            break;
                        }
                    }
                    if (student == null) {
                        throw new ExceptionUtility.InvalidInputException("Student not found!");
                    }
                    // Get the list of enrolled courses for the student
                    List<Course> enrolledCourses = student.getEnrolledCourses();
                    showEnrolledCourses(enrolledCourses);
                } catch (ExceptionUtility.InvalidInputException ex) {
                    JOptionPane.showMessageDialog(DisplayEnrolledCourses.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add components to the frame
        add(backButton);
        add(idLabel);
        add(idField);
        add(searchButton);

        setVisible(true);
    }

    // Method to display the enrolled courses in a new frame
    private void showEnrolledCourses(List<Course> courses) {
        JFrame resultsFrame = new JFrame("Filtered Courses");
        resultsFrame.setSize(800, 600);
        resultsFrame.setLayout(new BorderLayout());

        // Column names for the course table
        String[] columnNames = {"Course ID", "Title", "Credits"};
        Object[][] data = new Object[courses.size()][3];

        // Populate the table data with course information
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            data[i][0] = course.getCourseID();
            data[i][1] = course.getCourseName();
            data[i][2] = course.getCourseCredits();
        }

        // Create a table with the course data
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        resultsFrame.add(scrollPane, BorderLayout.CENTER);

        resultsFrame.setVisible(true);
    }
}