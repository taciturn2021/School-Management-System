package gui;

import gui.CourseViewTable;
import utils.ExceptionUtility;
import models.Course;
import models.University;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class CourseSearchForm extends JFrame {

    // Constructor to initialize the CourseSearchForm frame
    public CourseSearchForm(int WIDTH, int HEIGHT) {
        setTitle("Search Courses by Credits");
        setSize(WIDTH, HEIGHT);
        setLayout(null);

        // Back button to close the frame
        JButton backButton = new JButton("Back");
        backButton.setBounds(700, 10, 80, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Label and text field for entering the course ID
        JLabel idLabel = new JLabel("Course ID:");
        idLabel.setBounds(40, 60, 500, 30);
        JTextField idField = new JTextField();
        idField.setBounds(150, 60, 500, 30);

        // Label and combo box for selecting the minimum credits
        JLabel creditsLabel = new JLabel("Minimum Credits:");
        creditsLabel.setBounds(40, 100, 500, 30);
        JComboBox<String> creditsField = new JComboBox<>(new String[]{"2", "3", "4"});
        creditsField.setBounds(150, 100, 500, 30);

        // Search button to trigger the search action
        JButton searchButton = new JButton("Search");
        searchButton.setBounds(330, 140, 100, 30);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Parse and validate the minimum credits
                    int minCredits = ExceptionUtility.parseCredits(creditsField.getSelectedItem().toString());
                    String courseID = idField.getText();
                    // Filter courses based on the minimum credits
                    List<Course> filteredCourses = University.filterCoursesByCredits(minCredits);
                    showFilteredCourses(filteredCourses, courseID);
                } catch (ExceptionUtility.InvalidInputException ex) {
                    JOptionPane.showMessageDialog(CourseSearchForm.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add components to the frame
        add(backButton);
        add(idLabel);
        add(idField);
        add(creditsLabel);
        add(creditsField);
        add(searchButton);

        setVisible(true);
    }

    // Method to display the filtered courses in a new frame
    private void showFilteredCourses(List<Course> courses, String courseID) {
        JFrame resultsFrame = new JFrame("Filtered Courses");
        resultsFrame.setSize(800, 600);
        resultsFrame.setLayout(new BorderLayout());

        // Column names for the course table
        String[] columnNames = {"Course ID", "Title", "Credits"};
        List<Course> filteredCourses = new ArrayList<>();
        for (Course course : courses) {
            if (course.getCourseID().contains(courseID)) {
                filteredCourses.add(course);
            }
        }

        // Populate the table data with course information
        Object[][] data = new Object[filteredCourses.size()][3];
        for (int i = 0; i < filteredCourses.size(); i++) {
            Course course = filteredCourses.get(i);
            data[i][0] = course.getCourseID();
            data[i][1] = course.getCourseName();
            data[i][2] = course.getCourseCredits();
        }

        // Create a non-editable table with the course data
        JTable table = new JTable(data, columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false; // Disable cell editing
            }
        };
        JScrollPane scrollPane = new JScrollPane(table);
        resultsFrame.add(scrollPane, BorderLayout.CENTER);

        // Add a mouse listener to handle double-click events on table rows
        table.addMouseListener(new MouseInputAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.getSelectedRow();
                    Course selectedCourse = filteredCourses.get(row);
                    CourseViewTable.showCourseDetails(selectedCourse);
                }
            }
        });

        resultsFrame.setVisible(true);
    }
}