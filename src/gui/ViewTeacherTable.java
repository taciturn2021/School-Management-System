package gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import models.*;

public class ViewTeacherTable extends JFrame {
    public ViewTeacherTable(int WIDTH, int HEIGHT) {
        createViewTeacherTable(WIDTH, HEIGHT);
    }

    private void createViewTeacherTable(int WIDTH, int HEIGHT) {
        setTitle("View Teacher");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton backButton = new JButton("Back");
        backButton.setBounds(700, 10, 80, 30);
        backButton.addActionListener(e -> dispose());
        add(backButton);

        List<Teacher> teachers = University.teacherRepository.getAll();
        String[] columnNames = {"Teacher ID", "Name", "Email", "Address"};
        Object[][] data = new Object[teachers.size()][4];

        if (teachers.size() != 0) {
            for (int i = 0; i < teachers.size(); i++) {
                Teacher teacher = teachers.get(i);
                data[i][0] = teacher.getTeacherID();
                data[i][1] = teacher.getName();
                data[i][2] = teacher.getEmail();
                data[i][3] = teacher.getAddress().toString();
            }
        } else {
            data = new Object[1][4];
            data[0][0] = "No Data";
            data[0][1] = "No Data";
            data[0][2] = "No Data";
            data[0][3] = "No Data";
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(150, 60, 500, 500);
        add(scrollPane);

        setVisible(true);
    }
}