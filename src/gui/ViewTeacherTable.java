package gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import models.* ;

public class ViewTeacherTable extends JFrame {
    public ViewTeacherTable(int WIDTH, int HEIGHT) {
        createViewTeacherTable(WIDTH , HEIGHT);
    }

    private void createViewTeacherTable(int WIDTH , int HEIGHT) {
        setTitle("View Teacher");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        List<Teacher> teachers = University.teacherRepository.getAll();
        String[] columnNames = {"Teacher ID", "Name", "Email", "Address"};
        Object[][] data = new Object[teachers.size()][4];

        if ( teachers.size() != 0 ){
            for ( int i = 0 ; i < teachers.size() ; i++ ) {
                Teacher teacher = teachers.get(i);
                data[i][0] = teacher.getTeacherID();
                data[i][1] = teacher.getName();
                data[i][2] = teacher.getEmail();
                data[i][3] = teacher.getAddress().toString();
            }
        }
        else {
            data = new Object[1][4];
            data[0][0] = "No Data";
            data[0][1] = "No Data";
            data[0][2] = "No Data";
            data[0][3] = "No Data";
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);


    }
}
