package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import models.Course;
import models.Teacher;
import models.University;
import utils.ExceptionUtility;

public class GenerateTeacherReport extends JFrame {

    // Constructor to initialize the GenerateTeacherReport frame
    GenerateTeacherReport(int WIDTH, int HEIGHT) {
        generateReport(WIDTH, HEIGHT);
    }

    // Method to generate the report for a teacher
    public void generateReport(int WIDTH, int HEIGHT) {
        setSize(WIDTH, HEIGHT);
        setTitle("Generate Teacher Report");
        setLayout(null);

        // Back button to close the frame
        JButton back = new JButton("Back");
        back.setBounds(WIDTH - 130, 10, 100, 30);

        // Label for entering teacher ID
        JLabel teacherId = new JLabel("Teacher ID");
        teacherId.setBounds(50, 60, 500, 30);

        // Text field for entering teacher ID
        JTextField teacherIdField = new JTextField();
        teacherIdField.setBounds(150, 60, 500, 30);

        // Generate button to trigger the report generation
        JButton generate = new JButton("Generate");
        generate.setBounds(350, 100, 100, 30);

        // Export button to export the report to a file
        JButton export = new JButton("Export");
        export.setBounds(WIDTH - 130, 500, 100, 30);

        // Action listener for the export button
        export.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Teacher teacher = null;
                for (Teacher teach : University.teacherRepository.getAll()) {
                    try {
                        if (teach.getTeacherID() == ExceptionUtility.parseTeacherID(teacherIdField.getText())) {
                            teacher = teach;
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
                if (teacher != null) {
                    try {
                        teacher.exportToFile();
                        JOptionPane.showMessageDialog(null, "Teacher report exported successfully");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Teacher not found");
                }
            }
        });

        // Action listener for the generate button
        generate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Teacher teacher = null;
                for (Teacher teach : University.teacherRepository.getAll()) {
                    try {
                        if (teach.getTeacherID() == ExceptionUtility.parseTeacherID(teacherIdField.getText())) {
                            teacher = teach;
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
                if (teacher != null) {
                    List<Course> courses = teacher.getCoursesTaught();
                    if (courses.size() > 0) {
                        String[] courseNames = new String[courses.size()];
                        for (int i = 0; i < courses.size(); i++) {
                            courseNames[i] = courses.get(i).getCourseName();
                        }
                        JList<String> list = new JList<>(courseNames);
                        list.setBounds(150, 140, 500, 200);

                        JScrollPane scrollPane = new JScrollPane(list);
                        scrollPane.setBounds(150, 140, 500, 200);

                        String columnNames[] = {"Course ID", "Course Name", "Credits", "Number Of Students Enrolled", "Average Grade"};
                        Object[][] data = new Object[courses.size()][5];

                        for (int i = 0; i < courses.size(); i++) {
                            data[i][0] = courses.get(i).getCourseID();
                            data[i][1] = courses.get(i).getCourseName();
                            data[i][2] = courses.get(i).getCourseCredits();
                            data[i][3] = courses.get(i).getEnrolledStudents().size();
                            data[i][4] = courses.get(i).calculateAverageGrade();
                        }

                        JTable table = new JTable(data, columnNames);
                        table.setBounds(150, 360, 500, 200);
                        JScrollPane scrollPaneTable = new JScrollPane(table);
                        scrollPaneTable.setBounds(150, 360, 500, 200);

                        add(scrollPaneTable);
                        add(scrollPane);
                        revalidate();
                        repaint();

                    } else {
                        JOptionPane.showMessageDialog(null, "No courses found for this teacher");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Teacher not found");
                }
            }
        });

        // Action listener for the back button
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Add components to the frame
        add(back);
        add(export);
        add(teacherId);
        add(teacherIdField);
        add(generate);
        setVisible(true);
    }
}