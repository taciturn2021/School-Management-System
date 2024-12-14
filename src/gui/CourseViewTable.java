package gui;

import models.Course;
import models.University;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CourseViewTable extends JFrame {

    public CourseViewTable() {
        setTitle("View Courses");
        setSize(600, 400);
        setLayout(new BorderLayout());

        List<Course> courses = University.courseRepository.getAll();
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
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}