package gui;

import models.Course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIWindow extends JFrame {

    public GUIWindow() {
        setTitle("School Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        loadComponents(); // Load components
        setVisible(true);
    }

    private void loadComponents(){
        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create the Courses menu
        addMenuItems();

        // Set the layout and add components
        setLayout(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Welcome to the School Management System", JLabel.CENTER);
        add(welcomeLabel, BorderLayout.CENTER);
    }

    private void addMenuItems() {
        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();
        createCourseMenu(menuBar); // Create the Courses menu
    }

    private void createCourseMenu(JMenuBar menuBar) {
        // Create the Courses menu
        JMenu courseMenu = new JMenu("Courses");
        JMenuItem addCourse = new JMenuItem("Add Course");
        JMenuItem viewCourses = new JMenuItem("View Courses");
        JMenuItem searchCourse = new JMenuItem("Search Course");
        courseMenu.add(addCourse); // Add menu items to the menu
        courseMenu.add(viewCourses);
        courseMenu.add(searchCourse);

        // Add menus to the menu bar
        menuBar.add(courseMenu);

        // Set the menu bar for the frame
        setJMenuBar(menuBar);

        // Add action listeners for menu items
        addCourse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAddCourseForm();
            }
        });

        viewCourses.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showViewCourses();
            }
        });

        searchCourse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showSearchCourse();
            }
        });
    }
    public void showAddCourseForm() {
        new CourseAddForm();
    }
    public void showViewCourses() {
        new CourseViewTable();
    }
    public void showSearchCourse() {
        new CourseSearchForm();
    }
    public void showAddStudentForm() {
        new StudentAdmissionForm();
    }



}