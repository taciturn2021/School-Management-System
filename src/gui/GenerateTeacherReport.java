package gui;

import models.Teacher;
import models.University;
import utils.ExceptionUtility;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import models.Course;

public class GenerateTeacherReport extends JFrame {

    GenerateTeacherReport(int WIDTH , int HEIGHT){
        generateReport(WIDTH , HEIGHT);
    }

    public void generateReport(int WIDTH , int HEIGHT) {
        setSize(WIDTH, HEIGHT);
        setTitle("Generate Teacher Report");
        setLayout(null);

        JButton back = new JButton("Back");
        back.setBounds(WIDTH - 130, 10, 100, 30);

        JLabel teacherId = new JLabel("Teacher ID");
        teacherId.setBounds(50, 60, 500, 30);

        JTextField teacherIdField = new JTextField();
        teacherIdField.setBounds(150, 60, 500, 30);

        JButton generate = new JButton("Generate");
        generate.setBounds(350, 100, 100, 30);

        generate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Teacher teacher = null ;
                for ( Teacher teach : University.teacherRepository.getAll()){
                    try {
                        if (teach.getTeacherID() == ExceptionUtility.parseTeacherID(teacherIdField.getText())) {
                            teacher = teach;
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
                if ( teacher != null ) {
                    List<Course> courses = teacher.getCoursesTaught();
                    JList list = new JList(courses.toArray());
                    list.setBounds(150, 140, 500, 200);

                    JScrollPane scrollPane = new JScrollPane(list);
                    scrollPane.setBounds(150, 140, 500, 200);

                    String columnNames[] = {"Course ID", "Course Name", "Credits" , "Number Of Students Enrolled" , "Average Grade"};
                    Object[][] data = new Object[courses.size()][5];

                    if (courses.size() > 0) {
                        for (int i = 0; i < courses.size(); i++) {
                            data[i][0] = courses.get(i).getCourseID();
                            data[i][1] = courses.get(i).getCourseName();
                            data[i][2] = courses.get(i).getCourseCredits();
                            data[i][3] = courses.get(i).getEnrolledStudents().size();
                            data[i][4] = courses.get(i).calculateAverageGrade();
                        }

                    }else {
                        JOptionPane.showMessageDialog(null, "No courses found for this teacher");
                    }

                    JTable table = new JTable(data, columnNames);
                    table.setBounds(150, 360, 500, 200);
                    JScrollPane scrollPaneTable = new JScrollPane(table);
                    scrollPaneTable.setBounds(150, 360, 500, 200);

                    add(scrollPaneTable);
                    add(scrollPane);
                    repaint();

                }else {
                    JOptionPane.showMessageDialog(null, "Teacher not found");

                }

            }

        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        add(back);
        add(teacherId);
        add(teacherIdField);
        add(generate);
        setVisible(true);

    }
}