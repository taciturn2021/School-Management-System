package gui;

import models.Course;
import models.Student;
import models.University;
import utils.ExceptionUtility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AlterCourseStudent extends JFrame {

    public AlterCourseStudent(int WIDTH , int HEIGHT) {
        setTitle("Alter Course Enrollment");
        setSize(WIDTH, HEIGHT);
        setLayout(new GridLayout(4, 2));

        JLabel studentIDLabel = new JLabel("Student ID:");
        JTextField studentIDField = new JTextField();

        JLabel actionLabel = new JLabel("Action:");
        JComboBox<String> actionComboBox = new JComboBox<>(new String[]{"Add", "Remove"});

        JLabel coursesLabel = new JLabel("Select Courses:");
        DefaultListModel<Course> courseListModel = new DefaultListModel<>();
        List<Course> courses = University.courseRepository.getAll();
        for (Course course : courses) {
            courseListModel.addElement(course);
        }
        JList<Course> courseList = new JList<>(courseListModel);
        courseList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane courseScrollPane = new JScrollPane(courseList);

        JButton submitButton = new JButton("Submit");
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
            }
        });

        add(studentIDLabel);
        add(studentIDField);
        add(actionLabel);
        add(actionComboBox);
        add(coursesLabel);
        add(courseScrollPane);
        add(new JLabel()); // Empty cell
        add(submitButton);

        setVisible(true);
    }
}