package gui;

import models.Course;
import models.Teacher;
import models.University;
import utils.ExceptionUtility;
import utils.FileHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AssignCourseToTeacher extends JFrame {

    public AssignCourseToTeacher(int WIDTH , int HEIGHT) {
        createAssignCourseToTeacher(WIDTH , HEIGHT);
    }

    public void createAssignCourseToTeacher(int WIDTH , int HEIGHT) {
        setTitle("Assign Course To Teacher");
        setSize(WIDTH, HEIGHT);
        setLayout(null);
        setVisible(true);

        JTextField teacherIDField;
        JList<Course> courseList;
        DefaultListModel<Course> courseListModel;
        JButton assignButton;

        JLabel teacherIDLabel = new JLabel("Teacher ID");
        teacherIDLabel.setBounds(50, 20, 100, 30);

        teacherIDField = new JTextField();
        teacherIDField.setBounds(150, 20, 200, 30);

        JLabel coursesLabel = new JLabel("Select Courses");
        coursesLabel.setBounds(50, 60, 100, 30);

        courseListModel = new DefaultListModel<>();
        List<Course> courses = University.courseRepository.getAll();
        for (Course course : courses) {
            if (course != null) {
                courseListModel.addElement(course);
            }
        }

        courseList = new JList<>(courseListModel);
        courseList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane courseScrollPane = new JScrollPane(courseList);
        courseScrollPane.setBounds(150, 60, 200, 200);

        assignButton = new JButton("Assign");
        assignButton.setBounds(200, 300, 100, 30);

        assignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int teacherID = ExceptionUtility.parseTeacherID(teacherIDField.getText());
                    Teacher teacher = null;

                    for (Teacher teach : University.teacherRepository.getAll()) {
                        if (teach.getTeacherID() == teacherID) {
                            teacher = teach;
                            break;
                        }
                    }

                    if (teacher != null) {
                        List<Course> selectedCourses = courseList.getSelectedValuesList();

                        for (Course course : selectedCourses) {
                            if ( teacher.getCoursesTaught() != null && teacher.getCoursesTaught().contains(course) ) {
                                JOptionPane.showMessageDialog(null, "Course already assigned to teacher.");
                                return;
                            }
                            teacher.assignCourse(course);
                            FileHandler.SaveData();
                        }

                        JOptionPane.showMessageDialog(null, "Courses assigned successfully.");

                    } else {
                        JOptionPane.showMessageDialog(null, "Teacher not found.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        add(teacherIDLabel);
        add(teacherIDField);
        add(coursesLabel);
        add(courseScrollPane);
        add(assignButton);
    }

}