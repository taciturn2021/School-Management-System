package gui;

import utils.ExceptionUtility;
import models.Course;
import models.University;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CourseSearchForm extends JFrame {

    public CourseSearchForm(int WIDTH, int HEIGHT) {
        setTitle("Search Courses by Credits"); // Set the title of the window
        setSize(WIDTH, HEIGHT); // Set the size of the window
        setLayout(new GridLayout(3, 2)); // Set the layout manager

        JLabel idLabel = new JLabel("Course ID: "); // Label for course ID
        JTextField idField = new JTextField(); // Text field for course ID
        JLabel creditsLabel = new JLabel("Minimum Credits:"); // Label for minimum credits
        JComboBox<String> creditsField = new JComboBox<>(new String[]{"2", "3", "4"}); // Combo box for selecting minimum credits

        JButton searchButton = new JButton("Search"); // Button to search courses
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int minCredits = ExceptionUtility.parseCredits(creditsField.getSelectedItem().toString()); // Parse selected credits
                    String courseID = idField.getText(); // Get course ID from text field
                    List<Course> filteredCourses = University.filterCoursesByCredits(minCredits); // Filter courses by credits
                    showFilteredCourses(filteredCourses, courseID); // Show filtered courses
                } catch (ExceptionUtility.InvalidInputException ex) {
                    // Show error message if an exception occurs
                    JOptionPane.showMessageDialog(CourseSearchForm.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add components to the form
        add(idLabel);
        add(idField);
        add(creditsLabel);
        add(creditsField);
        add(new JLabel()); // Empty cell
        add(searchButton);

        setVisible(true); // Make the form visible
    }

    private void showFilteredCourses(List<Course> courses, String courseID) {
        JFrame resultsFrame = new JFrame("Filtered Courses"); // Create a new frame for displaying filtered courses
        resultsFrame.setSize(600, 400); // Set the size of the window
        resultsFrame.setLayout(new BorderLayout()); // Set the layout manager

        String[] columnNames = {"Course ID", "Title", "Credits"}; // Define column names for the table
        Object[][] data = new Object[courses.size()][3]; // Create a 2D array to hold the course data

        // Populate the data array with course information
        for (int i = 0; i < courses.size(); i++) {
            if (!courses.get(i).getCourseID().contains(courseID))
                continue;
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