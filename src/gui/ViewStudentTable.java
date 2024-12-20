package gui;

import models.Course;
import models.Student;
import models.University;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

public class ViewStudentTable extends JFrame {

    public ViewStudentTable(int WIDTH, int HEIGHT) {
        createStudentTable(WIDTH, HEIGHT);
    }

    public void createStudentTable(int WIDTH, int HEIGHT) {
        setTitle("View Students");
        setSize(WIDTH, HEIGHT);
        setLayout(null);
        setLocationRelativeTo(null);

        JButton backButton = new JButton("Back");
        backButton.setBounds(700, 10, 80, 30);
        backButton.addActionListener(e -> dispose());
        add(backButton);

        String[] columnNames = {"Student ID", "Name", "Email", "Address"};
        List<Student> students = University.studentRepository.getAll();
        Object[][] data = new Object[students.size()][4];

        if (!students.isEmpty()) {
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                data[i][0] = student.getStudentID();
                data[i][1] = student.getName();
                data[i][2] = student.getEmail();
                data[i][3] = student.getAddress().toString();
            }
        } else {
            data = new Object[1][4];
            data[0][0] = "No Data";
            data[0][1] = "No Data";
            data[0][2] = "No Data";
            data[0][3] = "No Data";
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
                    Student selectedStudent = students.get(row);
                    showStudentDetails(selectedStudent);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(150, 60, 500, 500);
        add(scrollPane);

        setVisible(true);
    }

    public static void showStudentDetails(Student student) {
        JFrame detailsFrame = new JFrame("Student Details");
        detailsFrame.setSize(800, 600);
        detailsFrame.setLayout(new BorderLayout());

        JPanel studentInfoPanel = new JPanel();
        studentInfoPanel.setLayout(new GridLayout(5, 2));
        studentInfoPanel.add(new JLabel("Student ID:"));
        studentInfoPanel.add(new JLabel(String.valueOf(student.getStudentID())));
        studentInfoPanel.add(new JLabel("Name:"));
        studentInfoPanel.add(new JLabel(student.getName()));
        studentInfoPanel.add(new JLabel("Email:"));
        studentInfoPanel.add(new JLabel(student.getEmail()));
        studentInfoPanel.add(new JLabel("Date of Birth:"));
        studentInfoPanel.add(new JLabel(student.getDateOfBirth().toString()));
        studentInfoPanel.add(new JLabel("Address:"));
        studentInfoPanel.add(new JLabel(student.getAddress().toString()));

        List<Course> courses = student.getEnrolledCourses();
        String[] courseColumnNames = {"Course ID", "Course Name", "Grade"};
        Object[][] courseData = new Object[courses.size()][3];

        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            courseData[i][0] = course.getCourseID();
            courseData[i][1] = course.getCourseName();
            courseData[i][2] = course.getGrade(student);
        }

        JTable courseTable = new JTable(courseData, courseColumnNames);
        JScrollPane courseScrollPane = new JScrollPane(courseTable);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> detailsFrame.dispose());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(backButton);

        detailsFrame.add(studentInfoPanel, BorderLayout.NORTH);
        detailsFrame.add(courseScrollPane, BorderLayout.CENTER);
        detailsFrame.add(bottomPanel, BorderLayout.SOUTH);
        detailsFrame.setVisible(true);
    }
}