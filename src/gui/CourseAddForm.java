package gui;

import models.Teacher;
import utils.ExceptionUtility;
import models.Course;
import models.University;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CourseAddForm extends JFrame {

    public CourseAddForm() {
        setTitle("Add Course"); // Set the title of the window
        setSize(500, 400); // Set the size of the window
        setLayout(new GridLayout(5, 2)); // Set the layout manager

        JLabel courseIDLabel = new JLabel("Course ID:"); // Label for course ID
        JTextField courseIDField = new JTextField(); // Text field for course ID
        JLabel titleLabel = new JLabel("Title:"); // Label for course title
        JTextField titleField = new JTextField(); // Text field for course title
        JLabel creditsLabel = new JLabel("Credits:"); // Label for course credits
        JTextField creditsField = new JTextField(); // Text field for course credits
        JButton addButton = new JButton("Add Course"); // Button to add the course

        // Action listener for the add button
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String courseID = courseIDField.getText(); // Get course ID from text field
                    String title = titleField.getText(); // Get course title from text field
                    int credits = ExceptionUtility.parseCredits(creditsField.getText()); // Parse credits from text field

                    // Create a new course object
                    Course newCourse = new Course(courseID, title, credits);
                    // Add the new course to the university's course repository
                    University.addToCourseRepository(newCourse);

                    // Show success message
                    JOptionPane.showMessageDialog(CourseAddForm.this, "Course added successfully!");
                    dispose(); // Close the form
                } catch (ExceptionUtility.InvalidInputException ex) {
                    // Show error message if an exception occurs
                    JOptionPane.showMessageDialog(CourseAddForm.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add components to the form
        add(courseIDLabel);
        add(courseIDField);
        add(titleLabel);
        add(titleField);
        add(creditsLabel);
        add(creditsField);
        add(new JLabel()); // Empty cell
        add(addButton);

        setVisible(true); // Make the form visible
    }
}