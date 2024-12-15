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

        // Create the top bar menu
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
        createStudentMenu(menuBar); // Create the Students menu
    }

    private void createStudentMenu(JMenuBar menuBar) {
        // Create the Students menu
        JMenu studentMenu = new JMenu("Students");
        JMenuItem addStudent = new JMenuItem("Add Student");
        JMenuItem viewStudents = new JMenuItem("View Students");
        JMenuItem searchStudent = new JMenuItem("Check Enrolled Courses");
        studentMenu.add(addStudent); // Add menu items to the menu
        studentMenu.add(viewStudents);
        studentMenu.add(searchStudent);

        // Add menus to the menu bar
        menuBar.add(studentMenu);

        // Set the menu bar for the frame
        setJMenuBar(menuBar);

        // Add action listeners for menu items
        addStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAddStudentForm();
            }
        });

        viewStudents.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // showViewStudents();
            }
        });

        searchStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showSearchStudent();
            }
        });
    }

    private void createCourseMenu(JMenuBar menuBar) {
        // Create the Courses menu
        JMenu courseMenu = new JMenu("Courses");
        JMenuItem addCourse = new JMenuItem("Add Course");
        JMenuItem viewCourses = new JMenuItem("View Courses");
        JMenuItem searchCourse = new JMenuItem("Search Course");
        JMenuItem alterStudent = new JMenuItem("Add/Remove Student");
        courseMenu.add(addCourse); // Add menu items to the menu
        courseMenu.add(viewCourses);
        courseMenu.add(searchCourse);
        courseMenu.add(alterStudent);

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

        alterStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAlterStudent();
            }
        });
    }
    private void showAddCourseForm() {
        new CourseAddForm();
    }
    private void showViewCourses() {
        new CourseViewTable();
    }
    private void showSearchCourse() {
        new CourseSearchForm();
    }
    private void showAlterStudent() {
        new AlterCourseStudent();
    }

    private void showAddStudentForm() {
        new StudentAdmissionForm();
    }
//    private void showViewStudents() {
//        new StudentViewTable();
//    }
    private void showSearchStudent() {
        new DisplayEnrolledCourses();
    }

}