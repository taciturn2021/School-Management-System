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

    public ListTeacherCourses() {
        createListTeacherCourses();
    }

    public void createListTeacherCourses() {
        setTitle("List Teacher Courses");
        setSize(500, 800);
        setLayout(null);
        setVisible(true);

        JLabel teacherID = new JLabel("Teacher ID");
        teacherID.setBounds(50, 20, 100, 30);

        teacherIDField = new JTextField();
        teacherIDField.setBounds(150, 20, 200, 30);

        searchButton = new JButton("Search");
        searchButton.setBounds(100, 60, 100, 30);

        backButton = new JButton("Back");
        backButton.setBounds(200, 60, 100, 30);

        tablePanel = new JPanel();
        tablePanel.setBounds(50, 100, 400, 400);
        tablePanel.setLayout(new BorderLayout());

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (e.getSource() == backButton) {
                        dispose();

                    } else if (e.getSource() == searchButton) {
                        List<Teacher> teachers = University.teacherRepository.getAll();
                        int teacherID = ExceptionUtility.parseTeacherID(teacherIDField.getText());

                        String columnNames[] = {"Course ID", "Course Name", "Credits"};
                        Teacher teacher = null;
                        List<Course> courses = null;

                        for (Teacher teach : teachers) {
                            if (teach.getTeacherID() == teacherID) {
                                teacher = teach;
                                courses = teach.getCoursesTaught();
                            }
                        }

                        if (teacher == null) {
                            JOptionPane.showMessageDialog(null, "Teacher Not Found");
                            return ;
                        }

                        if (courses != null) {
                            String[][] data = new String[courses.size()][3];
                            for (int i = 0; i < courses.size(); i++) {
                                data[i][0] = String.valueOf(courses.get(i).getCourseID());
                                data[i][1] = courses.get(i).getCourseName();
                                data[i][2] = String.valueOf(courses.get(i).getCourseCredits());
                            }

                            JTable table = new JTable(data, columnNames);
                            JScrollPane scrollPane = new JScrollPane(table);
                            tablePanel.removeAll();
                            tablePanel.add(scrollPane, BorderLayout.CENTER);
                            tablePanel.revalidate();
                            tablePanel.repaint();

                        } else {
                            JOptionPane.showMessageDialog(null, "No Courses Assigned");
                            return ;
                        }

                        JOptionPane.showMessageDialog(null, "Teacher Assigned Courses");

                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        add(teacherID);
        add(teacherIDField);
        add(searchButton);
        add(backButton);
        add(tablePanel);
    }

}