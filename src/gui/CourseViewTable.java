package gui;

import models.Course;
import models.University;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CourseViewTable extends JFrame {

    public CourseViewTable() {
        setTitle("View Courses"); // Set the title of the window
        setSize(600, 400); // Set the size of the window
        setLayout(new BorderLayout()); // Set the layout manager

        // Retrieve the list of courses from the repository
        List<Course> courses = University.courseRepository.getAll();
        String[] columnNames = {"Course ID", "Title", "Credits", "Teacher"}; // Define column names for the table
        Object[][] data = new Object[courses.size()][4]; // Create a 2D array to hold the course data

        // Populate the data array with course information
        if (courses.size() != 0) {
            for (int i = 0; i < courses.size(); i++) {
                Course course = courses.get(i);
                data[i][0] = course.getCourseID(); // Set course ID
                data[i][1] = course.getCourseName(); // Set course name
                data[i][2] = course.getCourseCredits(); // Set course credits
                if (course.getAssignedTeacher() == null)
                    data[i][3] = "None"; // If no teacher is assigned, set to "None"
                else
                    data[i][3] = course.getAssignedTeacher().getName(); // Set teacher name
            }
        }
        else{
            data = new Object[1][4];
            data[0][0] = "No data";
            data[0][1] = "No data";
            data[0][2] = "No data";
            data[0][3] = "No data";
        }

        // Create a table with the course data and column names
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table); // Add the table to a scroll pane
        add(scrollPane, BorderLayout.CENTER); // Add the scroll pane to the center of the window

        setVisible(true); // Make the window visible
    }
}