package gui;

import models.Course;
import models.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AssignGradeForm extends JFrame {
    private Course course;

    public AssignGradeForm(Course course) {
        this.course = course;
        setTitle("Assign Grade");
        setSize(400, 300);
        setLayout(new GridLayout(3, 2));

        JLabel studentLabel = new JLabel("Student:");
        JComboBox<Student> studentComboBox = new JComboBox<>(course.getEnrolledStudents().toArray(new Student[0]));
        JLabel gradeLabel = new JLabel("Grade:");
        JTextField gradeField = new JTextField();

        JButton assignButton = new JButton("Assign Grade");
        assignButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Student selectedStudent = (Student) studentComboBox.getSelectedItem();
                    double grade = Double.parseDouble(gradeField.getText());
                    if (grade < 0 || grade > 100) {
                        throw new IllegalArgumentException("Grade must be between 0 and 100.");
                    }
                    course.assignGrade(selectedStudent, grade);
                    JOptionPane.showMessageDialog(AssignGradeForm.this, "Grade assigned successfully!");
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AssignGradeForm.this, "Please enter a valid number for the grade.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(AssignGradeForm.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(studentLabel);
        add(studentComboBox);
        add(gradeLabel);
        add(gradeField);
        add(new JLabel()); // Empty cell
        add(assignButton);

        setVisible(true);
    }
}