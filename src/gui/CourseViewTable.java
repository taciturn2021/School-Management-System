package gui;

import models.Course;
import models.Student;
import models.University;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;

public class CourseViewTable extends JFrame {

    public CourseViewTable() {
        setTitle("View Courses");
        setSize(600, 400);
        setLayout(new BorderLayout());

        List<Course> courses = University.courseRepository.getAll();
        String[] columnNames = {"Course ID", "Title", "Credits", "Teacher"};
        Object[][] data = new Object[courses.size()][4];

        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            data[i][0] = course.getCourseID();
            data[i][1] = course.getCourseName();
            data[i][2] = course.getCourseCredits();
            data[i][3] = course.getAssignedTeacher() == null ? "None" : course.getAssignedTeacher().getName();
        }

        JTable table = new JTable(data, columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false; // Disable cell editing
            }
        };
        table.addMouseListener(new MouseInputAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.getSelectedRow();
                    Course selectedCourse = courses.get(row);
                    showCourseDetails(selectedCourse);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JButton assignGradeButton = new JButton("Assign Grade");
        assignGradeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    Course selectedCourse = courses.get(row);
                    new AssignGradeForm(selectedCourse);
                } else {
                    JOptionPane.showMessageDialog(CourseViewTable.this, "Please select a course first.");
                }
            }
        });

        add(assignGradeButton, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void showCourseDetails(Course course) {
        JFrame detailsFrame = new JFrame("Course Details");
        detailsFrame.setSize(600, 400);
        detailsFrame.setLayout(new BorderLayout());

        JPanel courseInfoPanel = new JPanel();
        courseInfoPanel.setLayout(new GridLayout(4, 2));
        courseInfoPanel.add(new JLabel("Course ID:"));
        courseInfoPanel.add(new JLabel(course.getCourseID()));
        courseInfoPanel.add(new JLabel("Course Name:"));
        courseInfoPanel.add(new JLabel(course.getCourseName()));
        courseInfoPanel.add(new JLabel("Credits:"));
        courseInfoPanel.add(new JLabel(String.valueOf(course.getCourseCredits())));
        courseInfoPanel.add(new JLabel("Teacher:"));
        courseInfoPanel.add(new JLabel(course.getAssignedTeacher() == null ? "None" : course.getAssignedTeacher().getName()));

        List<Student> students = course.getEnrolledStudents();
        String[] studentColumnNames = {"Student Name", "Grade"};
        Object[][] studentData = new Object[students.size()][2];

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            studentData[i][0] = student.getName();
            studentData[i][1] = course.getGrade(student);
        }

        JTable studentTable = new JTable(studentData, studentColumnNames);
        JScrollPane studentScrollPane = new JScrollPane(studentTable);

        detailsFrame.add(courseInfoPanel, BorderLayout.NORTH);
        detailsFrame.add(studentScrollPane, BorderLayout.CENTER);
        detailsFrame.setVisible(true);
    }
}