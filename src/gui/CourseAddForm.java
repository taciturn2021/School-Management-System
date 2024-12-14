package gui;

import utils.ExceptionUtility;
import models.Course;
import models.University;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourseAddForm extends JFrame {

    public CourseAddForm() {
        setTitle("Add Course");
        setSize(400, 300);
        setLayout(new GridLayout(5, 2));

        JLabel courseIDLabel = new JLabel("Course ID:");
        JTextField courseIDField = new JTextField();
        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField();
        JLabel creditsLabel = new JLabel("Credits:");
        JTextField creditsField = new JTextField();

        JButton addButton = new JButton("Add Course");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String courseID = courseIDField.getText();
                    String title = titleField.getText();
                    int credits = ExceptionUtility.parseCredits(creditsField.getText());

                    Course newCourse = new Course(courseID, title, credits);
                    University.courseRepository.add(newCourse);

                    JOptionPane.showMessageDialog(CourseAddForm.this, "Course added successfully!");
                    dispose();
                } catch (ExceptionUtility.InvalidInputException ex) {
                    JOptionPane.showMessageDialog(CourseAddForm.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(courseIDLabel);
        add(courseIDField);
        add(titleLabel);
        add(titleField);
        add(creditsLabel);
        add(creditsField);
        add(new JLabel()); // Empty cell
        add(addButton);

        setVisible(true);
    }
}