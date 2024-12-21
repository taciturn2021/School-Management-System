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

    // Constructor to initialize the ViewStudentTable frame
    public ViewStudentTable(int WIDTH, int HEIGHT) {
        createStudentTable(WIDTH, HEIGHT);
    }

    // Method to create the table view for students
    public void createStudentTable(int WIDTH, int HEIGHT) {
        setTitle("View Students");
        setSize(WIDTH, HEIGHT);
        setLayout(null);
        setLocationRelativeTo(null);

        // Back button to close the frame
        JButton backButton = new JButton("Back");
        backButton.setBounds(700, 10, 80, 30);
        backButton.addActionListener(e -> dispose());
        add(backButton);

        // Column names for the student table
        String[] columnNames = {"Student ID", "Name", "Email", "Address"};
        // Retrieve the list of students from the repository
        List<Student> students = University.studentRepository.getAll();
        Object[][] data = new Object[students.size()][4];

        // Populate the table data with student information
        if (!students.isEmpty()) {
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                data[i][0] = student.getStudentID();
                data[i][1] = student.getName();
                data[i][2] = student.getEmail();
                data[i][3] = student.getAddress().toString();
            }
        } else {
            // If no students are found, display "No Data"
            data = new Object[1][4];
            data[0][0] = "No Data";
            data[0][1] = "No Data";
            data[0][2] = "No Data";
            data[0][3] = "No Data";
        }

        // Create a non-editable table with the student data
        JTable table = new JTable(data, columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false; // Disable cell editing
            }
        };

        // Add a mouse listener to handle double-click events on table rows
        table.addMouseListener(new MouseInputAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.getSelectedRow();
                    Student selectedStudent = students.get(row);
                    showStudentDetails(selectedStudent);
                }
            }
        });

        // Add the table to a scroll pane and set its bounds
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 60, 800, 500);
        add(scrollPane);

        setVisible(true);
    }

    // Method to display the details of a selected student
    public static void showStudentDetails(Student student) {
        JFrame detailsFrame = new JFrame("Student Details");
        detailsFrame.setSize(800, 600);
        detailsFrame.setLayout(new BorderLayout());

        // Panel to display student information
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

        // Retrieve the list of courses the student is enrolled in
        List<Course> courses = student.getEnrolledCourses();
        String[] courseColumnNames = {"Course ID", "Course Name", "Grade"};
        Object[][] courseData = new Object[courses.size()][3];

        // Populate the table data with course information
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            courseData[i][0] = course.getCourseID();
            courseData[i][1] = course.getCourseName();
            courseData[i][2] = course.getGrade(student);
        }

        // Create a table to display the courses the student is enrolled in
        JTable courseTable = new JTable(courseData, courseColumnNames);
        JScrollPane courseScrollPane = new JScrollPane(courseTable);

        // Back button to close the details frame
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> detailsFrame.dispose());

        // Panel to hold the back button
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(backButton);

        // Add components to the details frame
        detailsFrame.add(studentInfoPanel, BorderLayout.NORTH);
        detailsFrame.add(courseScrollPane, BorderLayout.CENTER);
        detailsFrame.add(bottomPanel, BorderLayout.SOUTH);
        detailsFrame.setVisible(true);
    }
}