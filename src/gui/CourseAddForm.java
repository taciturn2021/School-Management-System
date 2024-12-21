package gui;

import models.Course;
import models.University;
import utils.ExceptionUtility;
import utils.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CourseAddForm extends JFrame {

    // Constructor to initialize the CourseAddForm frame
    public CourseAddForm(int WIDTH, int HEIGHT) {
        setTitle("Add/Remove Course");
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

        // Label and combo box for selecting the action (Add/Remove)
        JLabel actionLabel = new JLabel("Action:");
        actionLabel.setBounds(50, 60, 500, 30);
        String[] actions = {"Add", "Remove"};
        JComboBox<String> actionComboBox = new JComboBox<>(actions);
        actionComboBox.setBounds(150, 60, 500, 30);

        // Label and text field for entering the course ID
        JLabel courseIDLabel = new JLabel("Course ID:");
        courseIDLabel.setBounds(50, 100, 500, 30);
        JTextField courseIDField = new JTextField();
        courseIDField.setBounds(150, 100, 500, 30);

        // Label and text field for entering the course title
        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(50, 140, 500, 30);
        JTextField titleField = new JTextField();
        titleField.setBounds(150, 140, 500, 30);

        // Label and text field for entering the course credits
        JLabel creditsLabel = new JLabel("Credits:");
        creditsLabel.setBounds(50, 180, 500, 30);
        JTextField creditsField = new JTextField();
        creditsField.setBounds(150, 180, 500, 30);

        // Submit button to perform the selected action
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(330, 220, 100, 30);

        // Action listener to enable/disable fields based on the selected action
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

        // Action listener to handle the submit button click
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedAction = (String) actionComboBox.getSelectedItem();
                String courseID = courseIDField.getText();

                if ("Add".equals(selectedAction)) {
                    try {
                        String title = titleField.getText();
                        if (title.isEmpty()) {
                            throw new Exception("Title field must not be empty.");
                        }
                        int credits = ExceptionUtility.parseCredits(creditsField.getText());

                        // Create a new course and add it to the repository
                        Course newCourse = new Course(courseID, title, credits);
                        University.addToCourseRepository(newCourse);

                        JOptionPane.showMessageDialog(CourseAddForm.this, "Course added successfully!");
                        dispose();
                    } catch (ExceptionUtility.InvalidInputException ex) {
                        JOptionPane.showMessageDialog(CourseAddForm.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(CourseAddForm.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if ("Remove".equals(selectedAction)) {
                    Course course = null;
                    List<Course> courses = University.courseRepository.getAll();
                    for (Course c : courses) {
                        if (c.getCourseID().equalsIgnoreCase(courseID)) {
                            course = c;
                            break;
                        }
                    }

                    if (course != null) {
                        // Remove the course from the repository
                        University.removeFromCourseRepository(course);
                        JOptionPane.showMessageDialog(CourseAddForm.this, "Course removed successfully!");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(CourseAddForm.this, "Course not found!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

                try {
                    // Save the updated data to the file
                    FileHandler.saveData();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(CourseAddForm.this, "Error saving data to file!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add components to the frame
        add(backButton);
        add(actionLabel);
        add(actionComboBox);
        add(courseIDLabel);
        add(courseIDField);
        add(titleLabel);
        add(titleField);
        add(creditsLabel);
        add(creditsField);
        add(submitButton);

        setVisible(true);
    }
}