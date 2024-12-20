package gui;

import models.Course;
import models.Student;
import models.University;
import utils.ExceptionUtility;
import utils.FileHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRemoveCourseFromStudent extends JFrame {

    AddRemoveCourseFromStudent(int WIDTH, int HEIGHT){
        createAddRemoveCourseFromStudentForm(WIDTH , HEIGHT);
    }

    private void createAddRemoveCourseFromStudentForm(int WIDTH, int HEIGHT) {
        setTitle("Add/Remove From Course");
        setSize(WIDTH, HEIGHT);
        setLayout(null);

        JButton backButton = new JButton("Back");
        backButton.setBounds(700, 10, 80, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JLabel actionLabel = new JLabel("Action:");
        actionLabel.setBounds(50, 60, 500, 30);
        String[] actions = {"Add", "Remove"};
        JComboBox<String> actionComboBox = new JComboBox<>(actions);
        actionComboBox.setBounds(150, 60, 500, 30);

        JLabel studentIDLabel = new JLabel("Student ID:");
        studentIDLabel.setBounds(50, 100, 500, 30);

        JTextField studentIDField = new JTextField();
        studentIDField.setBounds(150, 100, 500, 30);

        JLabel courseIDLabel = new JLabel("Course ID:");
        courseIDLabel.setBounds(50, 140, 500, 30);

        JTextField courseIDField = new JTextField();
        courseIDField.setBounds(150, 140, 500, 30);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(330, 180, 100, 30);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final Student[] searchedStudent = {null};

                for (Student student : University.studentRepository.getAll()){
                    try{
                        if ( student.getStudentID() == ExceptionUtility.parseStudentID(studentIDField.getText())){
                            searchedStudent[0] = student;
                        }

                    } catch (Exception ex){
                        JOptionPane.showMessageDialog(AddRemoveCourseFromStudent.this , ex.getMessage());
                    }
                }

                if (searchedStudent[0] == null){
                    JOptionPane.showMessageDialog(AddRemoveCourseFromStudent.this, "Student not found");
                    return;
                }

                final Course[] searchedCourse = {null};

                for ( Course course : University.courseRepository.getAll()){
                    try{
                        if ( course.getCourseID().equals(courseIDField.getText())){
                            searchedCourse[0] = course;
                        }
                    } catch (Exception ex){
                        JOptionPane.showMessageDialog(AddRemoveCourseFromStudent.this, ex.getMessage());
                    }
                }

                if ( searchedCourse[0] == null){
                    JOptionPane.showMessageDialog(AddRemoveCourseFromStudent.this, "Course not found");
                    return;
                }

                if (actionComboBox.getSelectedItem().equals("Add")){
                    searchedStudent[0].enrollInCourse(searchedCourse[0]);
                    JOptionPane.showMessageDialog(AddRemoveCourseFromStudent.this, "Course added successfully!");

                } else {
                    searchedStudent[0].dropCourse(searchedCourse[0]);
                    JOptionPane.showMessageDialog(AddRemoveCourseFromStudent.this, "Course removed successfully!");
                }

                try{
                    FileHandler.saveData();
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(AddRemoveCourseFromStudent.this, "Error: " + ex.getMessage());
                }
            }
        });

        add(backButton);
        add(actionLabel);
        add(actionComboBox);
        add(studentIDLabel);
        add(studentIDField);
        add(courseIDLabel);
        add(courseIDField);
        add(submitButton);

        setVisible(true);
    }
}