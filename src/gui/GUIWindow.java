package gui;

import com.sun.source.tree.NewArrayTree;
import models.Course;
import models.University;

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
        createStudentMenu(menuBar);// Create the Students menu
        createTeacherMenu(menuBar);// Create the Teacher menu
        createAdministrationMenu(menuBar);// Create the Administration menu
        showSystemStats(menuBar); // Create the System Stats menu
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

    private void createTeacherMenu(JMenuBar menuBar){

        JMenu teacherMenu = new JMenu("Teachers");
        JMenuItem hireTeacher = new JMenuItem("Hire Teacher");
        JMenuItem listTeacherCourses = new JMenuItem("List Teacher Courses");
        JMenuItem assignCourse = new JMenuItem("Assign Course to Teacher");
        teacherMenu.add(hireTeacher);
        teacherMenu.add(listTeacherCourses);
        teacherMenu.add(assignCourse);

        menuBar.add(teacherMenu);

        hireTeacher.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showTeacherHiringForm();
            }
        });

        listTeacherCourses.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ListTeacherCourses();
            }
        });

        assignCourse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AssignCourseToTeacher();
            }
        });

    }

    private void createAdministrationMenu(JMenuBar menuBar){

        JMenu administrationMenu = new JMenu("Administrative Staff");
        JMenuItem hireAdministration = new JMenuItem("Hire Administration Staff");
        administrationMenu.add(hireAdministration);

        menuBar.add(administrationMenu);

        hireAdministration.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAdministrativeHiringForm();
            }
        });

    }

    private void showSystemStats(JMenuBar menuBar) {
        JMenu systemStats = new JMenu("System Stats");
        JMenuItem viewStats = new JMenuItem("View Stats");
        systemStats.add(viewStats);

        menuBar.add(systemStats);

        setJMenuBar(menuBar);

        viewStats.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                University university = new University();
                university.countUniversityData();
                JOptionPane.showMessageDialog(null, "Number of Students: " + university.studentCounter + "\nNumber of Teachers: " + university.teacherCounter + "\nNumber of Courses: " + university.courseCounter + "\nNumber of Administrative Staff: " + university.administrativeStaffCounter);

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

    private void showSearchStudent() {
        new DisplayEnrolledCourses();
    }
  
    private void showTeacherHiringForm() { new TeacherHiringForm(); }
    private void ListTeacherCourses() { new ListTeacherCourses(); }
    private void showAdministrativeHiringForm() { new AdministrationStaffHiringForm(); }
    private void AssignCourseToTeacher() { new AssignCourseToTeacher(); }

}