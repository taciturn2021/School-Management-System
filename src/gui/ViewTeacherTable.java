package gui;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;
import models.*;

public class ViewTeacherTable extends JFrame {
    // Constructor to initialize the ViewTeacherTable frame
    public ViewTeacherTable(int WIDTH, int HEIGHT) {
        createViewTeacherTable(WIDTH, HEIGHT);
    }

    // Method to create the table view for teachers
    private void createViewTeacherTable(int WIDTH, int HEIGHT) {
        setTitle("View Teacher");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setLayout(null);

        // Back button to close the frame
        JButton backButton = new JButton("Back");
        backButton.setBounds(700, 10, 80, 30);
        backButton.addActionListener(e -> dispose());
        add(backButton);

        // Retrieve the list of teachers from the repository
        List<Teacher> teachers = University.teacherRepository.getAll();
        String[] columnNames = {"Teacher ID", "Name", "Email", "Address"};
        Object[][] data = new Object[teachers.size()][4];

        // Populate the table data with teacher information
        if (teachers.size() != 0) {
            for (int i = 0; i < teachers.size(); i++) {
                Teacher teacher = teachers.get(i);
                data[i][0] = teacher.getTeacherID();
                data[i][1] = teacher.getName();
                data[i][2] = teacher.getEmail();
                data[i][3] = teacher.getAddress().toString();
            }
        } else {
            // If no teachers are found, display "No Data"
            data = new Object[1][4];
            data[0][0] = "No Data";
            data[0][1] = "No Data";
            data[0][2] = "No Data";
            data[0][3] = "No Data";
        }

        // Create a non-editable table with the teacher data
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
                    Teacher selectedTeacher = teachers.get(row);
                    showTeacherDetails(selectedTeacher);
                }
            }
        });

        // Add the table to a scroll pane and set its bounds
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 60, 800, 500);
        add(scrollPane);

        setVisible(true);
    }

    // Method to display the details of a selected teacher
    public static void showTeacherDetails(Teacher teacher) {
        JFrame detailsFrame = new JFrame("Teacher Details");
        detailsFrame.setSize(800, 600);
        detailsFrame.setLayout(new BorderLayout());

        // Panel to display teacher information
        JPanel teacherInfoPanel = new JPanel();
        teacherInfoPanel.setLayout(new GridLayout(5, 2));
        teacherInfoPanel.add(new JLabel("Teacher ID:"));
        teacherInfoPanel.add(new JLabel(String.valueOf(teacher.getTeacherID())));
        teacherInfoPanel.add(new JLabel("Name:"));
        teacherInfoPanel.add(new JLabel(teacher.getName()));
        teacherInfoPanel.add(new JLabel("Email:"));
        teacherInfoPanel.add(new JLabel(teacher.getEmail()));
        teacherInfoPanel.add(new JLabel("Date of Birth:"));
        teacherInfoPanel.add(new JLabel(teacher.getDateOfBirth().toString()));
        teacherInfoPanel.add(new JLabel("Address:"));
        teacherInfoPanel.add(new JLabel(teacher.getAddress().toString()));

        // Retrieve the list of courses taught by the teacher
        List<Course> courses = teacher.getCoursesTaught();
        String[] courseColumnNames = {"Course ID", "Course Name", "Credits"};
        Object[][] courseData = new Object[courses.size()][3];

        // Populate the table data with course information
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            courseData[i][0] = course.getCourseID();
            courseData[i][1] = course.getCourseName();
            courseData[i][2] = course.getCourseCredits();
        }

        // Create a table to display the courses taught by the teacher
        JTable courseTable = new JTable(courseData, courseColumnNames);
        JScrollPane courseScrollPane = new JScrollPane(courseTable);

        // Back button to close the details frame
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> detailsFrame.dispose());

        // Panel to hold the back button
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(backButton);

        // Add components to the details frame
        detailsFrame.add(teacherInfoPanel, BorderLayout.NORTH);
        detailsFrame.add(courseScrollPane, BorderLayout.CENTER);
        detailsFrame.add(bottomPanel, BorderLayout.SOUTH);
        detailsFrame.setVisible(true);
    }
}