package gui;

import models.Course;
import models.Student;
import models.University;
import utils.ExceptionUtility;
import utils.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;

public class AlterCourseStudent extends JFrame {

    public AlterCourseStudent(int WIDTH, int HEIGHT) {
        setTitle("Alter Course Enrollment");
        setSize(WIDTH, HEIGHT);
        setLayout(null);

        JButton backButton = new JButton("Back");
        backButton.setBounds(700, 10, 80, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JLabel studentIDLabel = new JLabel("Student ID:");
        studentIDLabel.setBounds(50, 50, 500, 30);
        JTextField studentIDField = new JTextField();
        studentIDField.setBounds(150, 50, 500, 30);

        JLabel actionLabel = new JLabel("Action:");
        actionLabel.setBounds(50, 100, 500, 30);
        JComboBox<String> actionComboBox = new JComboBox<>(new String[]{"Add", "Remove"});
        actionComboBox.setBounds(150, 100, 500, 30);

        JLabel coursesLabel = new JLabel("Select Courses:");
        coursesLabel.setBounds(50, 150, 500, 30);
        DefaultListModel<Course> courseListModel = new DefaultListModel<>();
        List<Course> courses = University.courseRepository.getAll();
        for (Course course : courses) {
            courseListModel.addElement(course);
        }
        JList<Course> courseList = new JList<>(courseListModel);
        courseList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane courseScrollPane = new JScrollPane(courseList);
        courseScrollPane.setBounds(150, 150, 500, 200);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(350, 370, 100, 30);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int studentID = ExceptionUtility.parseStudentID(studentIDField.getText());
                    Student student = null;
                    List<Student> allStudents = University.studentRepository.getAll();
                    for (Student s : allStudents) {
                        if (s.getStudentID() == studentID) {
                            student = s;
                            break;
                        }
                    }
                    if (student == null) {
                        throw new ExceptionUtility.InvalidInputException("Student not found!");
                    }

                    List<Course> selectedCourses = courseList.getSelectedValuesList();
                    String action = (String) actionComboBox.getSelectedItem();

                    if ("Add".equals(action)) {
                        for (Course course : selectedCourses) {
                            University.addStudentToCourse(student, course);
                        }
                    } else if ("Remove".equals(action)) {
                        for (Course course : selectedCourses) {
                            University.removeStudentFromCourse(student, course);
                        }
                    }

                    JOptionPane.showMessageDialog(AlterCourseStudent.this, "Operation successful!");
                } catch (ExceptionUtility.InvalidInputException ex) {
                    JOptionPane.showMessageDialog(AlterCourseStudent.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

                try {
                    FileHandler.saveData();
                }
                catch (FileNotFoundException ex){
                    JOptionPane.showMessageDialog(AlterCourseStudent.this, "Error saving data to file!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(AlterCourseStudent.this, "An error occurred!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }

        });

        add(backButton);
        add(studentIDLabel);
        add(studentIDField);
        add(actionLabel);
        add(actionComboBox);
        add(coursesLabel);
        add(courseScrollPane);
        add(submitButton);

        setVisible(true);
    }
}