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

    public CourseSearchForm() {
        setTitle("Search Courses by Credits");
        setSize(400, 300);
        setLayout(new GridLayout(3, 2));

        JLabel creditsLabel = new JLabel("Minimum Credits:");
        JTextField creditsField = new JTextField();

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int minCredits = ExceptionUtility.parseCredits(creditsField.getText());
                    List<Course> filteredCourses = University.filterCoursesByCredits(minCredits);
                    showFilteredCourses(filteredCourses);
                } catch (ExceptionUtility.InvalidInputException ex) {
                    JOptionPane.showMessageDialog(CourseSearchForm.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(creditsLabel);
        add(creditsField);
        add(new JLabel()); // Empty cell
        add(searchButton);

        setVisible(true);
    }

    private void showFilteredCourses(List<Course> courses) {
        JFrame resultsFrame = new JFrame("Filtered Courses");
        resultsFrame.setSize(600, 400);
        resultsFrame.setLayout(new BorderLayout());

        String[] columnNames = {"Course ID", "Title", "Credits"};
        Object[][] data = new Object[courses.size()][3];

        for (int i = 0; i < courses.size(); i++) {
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