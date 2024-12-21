package gui;

import models.Teacher;
import models.University;
import models.Course;
import utils.ExceptionUtility;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.BorderLayout;

public class ListTeacherCourses extends JFrame {

    private JTextField teacherIDField;
    private JButton searchButton;
    private JButton backButton;
    private JPanel tablePanel;

    // Constructor to initialize the ListTeacherCourses frame
    public ListTeacherCourses(int WIDTH, int HEIGHT) {
        createListTeacherCourses(WIDTH, HEIGHT);
    }

    // Method to create the form for listing teacher courses
    public void createListTeacherCourses(int WIDTH, int HEIGHT) {
        setTitle("List Teacher Courses");
        setSize(WIDTH, HEIGHT);
        setLayout(null);
        setVisible(true);

        // Label for entering teacher ID
        JLabel teacherID = new JLabel("Teacher ID");
        teacherID.setBounds(50, 60, 500, 30);

        // Text field for entering teacher ID
        teacherIDField = new JTextField();
        teacherIDField.setBounds(150, 60, 500, 30);

        // Search button to trigger the search action
        searchButton = new JButton("Search");
        searchButton.setBounds(350, 100, 100, 30);

        // Back button to close the frame
        backButton = new JButton("Back");
        backButton.setBounds(WIDTH - 130, 10, 100, 30);

        // Panel to display the table of courses
        tablePanel = new JPanel();
        tablePanel.setBounds(50, 140, 700, 400);
        tablePanel.setLayout(new BorderLayout());

        // Action listener for search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (e.getSource() == backButton) {
                        dispose();
                    } else if (e.getSource() == searchButton) {
                        // Retrieve the list of teachers from the repository
                        List<Teacher> teachers = University.teacherRepository.getAll();
                        int teacherID = ExceptionUtility.parseTeacherID(teacherIDField.getText());

                        String columnNames[] = {"Course ID", "Course Name", "Credits"};
                        Teacher teacher = null;
                        List<Course> courses = null;

                        // Find the teacher by ID and get their courses
                        for (Teacher teach : teachers) {
                            if (teach.getTeacherID() == teacherID) {
                                teacher = teach;
                                courses = teach.getCoursesTaught();
                            }
                        }

                        if (teacher == null) {
                            JOptionPane.showMessageDialog(null, "Teacher Not Found");
                            return;
                        }

                        if (courses != null) {
                            // Populate the table data with course information
                            String[][] data = new String[courses.size()][3];
                            for (int i = 0; i < courses.size(); i++) {
                                data[i][0] = String.valueOf(courses.get(i).getCourseID());
                                data[i][1] = courses.get(i).getCourseName();
                                data[i][2] = String.valueOf(courses.get(i).getCourseCredits());
                            }

                            // Create a table with the course data
                            JTable table = new JTable(data, columnNames);
                            JScrollPane scrollPane = new JScrollPane(table);
                            tablePanel.removeAll();
                            tablePanel.add(scrollPane, BorderLayout.CENTER);
                            tablePanel.revalidate();
                            tablePanel.repaint();
                        } else {
                            JOptionPane.showMessageDialog(null, "No Courses Assigned");
                            return;
                        }

                        JOptionPane.showMessageDialog(null, "Teacher Assigned Courses");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        // Action listener for back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Add components to the frame
        add(teacherID);
        add(teacherIDField);
        add(searchButton);
        add(backButton);
        add(tablePanel);
    }
}