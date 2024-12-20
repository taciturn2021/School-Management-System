package gui;

import javax.swing.*;
import java.util.List;
import models.*;

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

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(150, 60, 500, 500);
        add(scrollPane);

        setVisible(true);
    }
}