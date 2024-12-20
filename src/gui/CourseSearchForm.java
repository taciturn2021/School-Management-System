package gui;

import utils.ExceptionUtility;
import models.Course;
import models.University;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CourseSearchForm extends JFrame {

    public CourseSearchForm(int WIDTH, int HEIGHT) {
        setTitle("Search Courses by Credits");
        setSize(WIDTH, HEIGHT);
        setLayout(null);

        JButton backButton = new JButton("Back");
        backButton.setBounds(700, 10, 80, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JLabel idLabel = new JLabel("Course ID:");
        idLabel.setBounds(40, 60, 500, 30);
        JTextField idField = new JTextField();
        idField.setBounds(150, 60, 500, 30);

        JLabel creditsLabel = new JLabel("Minimum Credits:");
        creditsLabel.setBounds(40, 100, 500, 30);
        JComboBox<String> creditsField = new JComboBox<>(new String[]{"2", "3", "4"});
        creditsField.setBounds(150, 100, 500, 30);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(330, 140, 100, 30);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int minCredits = ExceptionUtility.parseCredits(creditsField.getSelectedItem().toString());
                    String courseID = idField.getText();
                    List<Course> filteredCourses = University.filterCoursesByCredits(minCredits);
                    showFilteredCourses(filteredCourses, courseID);
                } catch (ExceptionUtility.InvalidInputException ex) {
                    JOptionPane.showMessageDialog(CourseSearchForm.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(backButton);
        add(idLabel);
        add(idField);
        add(creditsLabel);
        add(creditsField);
        add(searchButton);

        setVisible(true);
    }

    private void showFilteredCourses(List<Course> courses, String courseID) {
        JFrame resultsFrame = new JFrame("Filtered Courses");
        resultsFrame.setSize(800, 600);
        resultsFrame.setLayout(new BorderLayout());

        String[] columnNames = {"Course ID", "Title", "Credits"};
        Object[][] data = new Object[courses.size()][3];

        for (int i = 0; i < courses.size(); i++) {
            if (!courses.get(i).getCourseID().contains(courseID))
                continue;
            Course course = courses.get(i);
            data[i][0] = course.getCourseID();
            data[i][1] = course.getCourseName();
            data[i][2] = course.getCourseCredits();
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        resultsFrame.add(scrollPane, BorderLayout.CENTER);

        resultsFrame.setVisible(true);
    }
}