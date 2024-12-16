package gui;

import models.Course;
import models.University;
import utils.ExceptionUtility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CourseAddForm extends JFrame {

    public CourseAddForm() {
        setTitle("Add/Remove Course"); // Set the title of the window
        setSize(500, 400); // Set the size of the window
        setLayout(new GridLayout(7, 2)); // Set the layout manager

        JLabel actionLabel = new JLabel("Action:"); // Label for action
        String[] actions = {"Add", "Remove"};
        JComboBox<String> actionComboBox = new JComboBox<>(actions); // ComboBox for selecting action

        JLabel courseIDLabel = new JLabel("Course ID:"); // Label for course ID
        JTextField courseIDField = new JTextField(); // Text field for course ID
        JLabel titleLabel = new JLabel("Title:"); // Label for course title
        JTextField titleField = new JTextField(); // Text field for course title
        JLabel creditsLabel = new JLabel("Credits:"); // Label for course credits
        JTextField creditsField = new JTextField(); // Text field for course credits
        JButton submitButton = new JButton("Submit"); // Button to submit the form

        // Action listener for the ComboBox
        actionComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedAction = (String) actionComboBox.getSelectedItem();
                if ("Remove".equals(selectedAction)) {
                    titleField.setEnabled(false);
                    creditsField.setEnabled(false);
                } else {
                    titleField.setEnabled(true);
                    creditsField.setEnabled(true);
                }
            }
        });

        // Action listener for the submit button
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedAction = (String) actionComboBox.getSelectedItem();
                String courseID = courseIDField.getText(); // Get course ID from text field

                if ("Add".equals(selectedAction)) {
                    try {
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
                } else if ("Remove".equals(selectedAction)) {
                    Course course = null;
                    // Find the course in the repository
                    List<Course> courses = University.courseRepository.getAll();
                    for (Course c : courses) {
                        if (c.getCourseID().equals(courseID)) {
                            course = c;
                            break;
                        }
                    }

                    if (course != null) {
                        University.removeFromCourseRepository(course); // Remove the course from the repository
                        // Show success message
                        JOptionPane.showMessageDialog(CourseAddForm.this, "Course removed successfully!");
                        dispose(); // Close the form
                    } else {
                        // Show error message if the course is not found
                        JOptionPane.showMessageDialog(CourseAddForm.this, "Course not found!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Add components to the form
        add(actionLabel);
        add(actionComboBox);
        add(courseIDLabel);
        add(courseIDField);
        add(titleLabel);
        add(titleField);
        add(creditsLabel);
        add(creditsField);
        add(new JLabel()); // Empty cell
        add(submitButton);

        setVisible(true); // Make the form visible
    }
}