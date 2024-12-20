
package gui;

import models.Student;
import models.University;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.stream.Collectors;

public class SearchStudentByName extends JFrame {

    public SearchStudentByName(int WIDTH, int HEIGHT) {
        setTitle("Search Students by Name");
        setSize(WIDTH, HEIGHT);
        setLayout(null);

        JButton backButton = new JButton("Back");
        backButton.setBounds(700, 10, 80, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JLabel nameLabel = new JLabel("Student Name:");
        nameLabel.setBounds(40, 60, 500, 30);
        JTextField nameField = new JTextField();
        nameField.setBounds(150, 60, 500, 30);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(330, 100, 100, 30);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                List<Student> filteredStudents = University.studentRepository.getAll().stream()
                        .filter(student -> student.getName().toLowerCase().contains(name.toLowerCase()))
                        .collect(Collectors.toList());
                showFilteredStudents(filteredStudents);
            }
        });

        add(backButton);
        add(nameLabel);
        add(nameField);
        add(searchButton);

        setVisible(true);
    }

    private void showFilteredStudents(List<Student> students) {
        JFrame resultsFrame = new JFrame("Filtered Students");
        resultsFrame.setSize(800, 600);
        resultsFrame.setLayout(new BorderLayout());

        String[] columnNames = {"Student ID", "Name", "Email", "Address"};
        Object[][] data = new Object[students.size()][4];

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            data[i][0] = student.getStudentID();
            data[i][1] = student.getName();
            data[i][2] = student.getEmail();
            data[i][3] = student.getAddress().toString();
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
                    ViewStudentTable.showStudentDetails(selectedStudent);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        resultsFrame.add(scrollPane, BorderLayout.CENTER);

        resultsFrame.setVisible(true);
    }
}