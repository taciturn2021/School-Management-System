package gui;

import models.Course;
import models.Student;
import utils.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AssignGradeForm extends JFrame {
    private Course course;

    // Constructor to initialize the AssignGradeForm frame
    public AssignGradeForm(Course course, int WIDTH, int HEIGHT) {
        this.course = course;
        setTitle("Assign Grade");
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

        // Label and combo box for selecting the student
        JLabel studentLabel = new JLabel("Student:");
        studentLabel.setBounds(50, 60, 500, 30);
        JComboBox<Student> studentComboBox = new JComboBox<>(course.getEnrolledStudents().toArray(new Student[0]));
        studentComboBox.setBounds(150, 60, 500, 30);

        // Label and text field for entering the grade
        JLabel gradeLabel = new JLabel("Grade:");
        gradeLabel.setBounds(50, 100, 500, 30);
        JTextField gradeField = new JTextField();
        gradeField.setBounds(150, 100, 500, 30);

        // Button to assign the grade to the selected student
        JButton assignButton = new JButton("Assign Grade");
        assignButton.setBounds(320, 140, 150, 30);
        assignButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get the selected student and the entered grade
                    Student selectedStudent = (Student) studentComboBox.getSelectedItem();
                    double grade = Double.parseDouble(gradeField.getText());
                    if (grade < 0 || grade > 100) {
                        throw new IllegalArgumentException("Grade must be between 0 and 100.");
                    }
                    // Assign the grade to the student
                    course.assignGrade(selectedStudent, grade);
                    JOptionPane.showMessageDialog(AssignGradeForm.this, "Grade assigned successfully!");
                    // Save the updated data to the file
                    FileHandler.saveData();
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AssignGradeForm.this, "Please enter a valid number for the grade.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(AssignGradeForm.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(AssignGradeForm.this, "An error occurred. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add components to the frame
        add(backButton);
        add(studentLabel);
        add(studentComboBox);
        add(gradeLabel);
        add(gradeField);
        add(assignButton);

        setVisible(true);
    }
}