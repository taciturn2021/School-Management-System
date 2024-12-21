package gui;

import models.AdministrativeStaff;
import models.Teacher;
import models.University;
import utils.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GUIWindow extends JFrame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    // Constructor to initialize the GUIWindow frame
    public GUIWindow() {
        setTitle("School Management System");
        setSize(WIDTH, HEIGHT);

        // Set the background color of the content pane
        getContentPane().setBackground(Color.GRAY);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        try {
            // Load data from file
            FileHandler.loadData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage());
        }
        loadComponents(); // Load components
        setVisible(true);
    }

    // Method to load components into the frame
    private void loadComponents() {
        // Create the top bar menu
        addMenuItems();

        // Set the layout and add components
        setLayout(new BorderLayout());

        // Create a panel to hold the welcome label and image label
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.GRAY);

        // Load the image
        ImageIcon originalIcon = new ImageIcon("src/images/School Management System.png");
        Image originalImage = originalIcon.getImage();

        // Scale the image to the desired size
        Image scaledImage = originalImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Create a label with the scaled image
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create the welcome label
        JLabel welcomeLabel = new JLabel("Welcome to the School Management System", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 25));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add the image label and welcome label to the panel
        panel.add(Box.createVerticalGlue());
        panel.add(imageLabel);
        panel.add(Box.createVerticalStrut(20)); // Add some space between the image and the text
        panel.add(welcomeLabel);
        panel.add(Box.createVerticalGlue());

        // Add the panel to the frame
        add(panel, BorderLayout.CENTER);
    }

    // Method to create and add menu items to the menu bar
    private void addMenuItems() {
        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();
        createCourseMenu(menuBar); // Create the Courses menu
        createStudentMenu(menuBar); // Create the Students menu
        createTeacherMenu(menuBar); // Create the Teacher menu
        createAdministrationMenu(menuBar); // Create the Administration menu
    }

    // Method to create the Students menu
    private void createStudentMenu(JMenuBar menuBar) {
        JMenu studentMenu = new JMenu("Students");
        JMenuItem addStudent = new JMenuItem("Add/Remove Student");
        JMenuItem viewStudents = new JMenuItem("View Students");
        JMenuItem searchStudents = new JMenuItem("Search By Name");
        JMenuItem checkEnrolledCourses = new JMenuItem("Check Enrolled Courses");

        studentMenu.add(addStudent); // Add menu items to the menu
        studentMenu.add(viewStudents);
        studentMenu.add(searchStudents);
        studentMenu.add(checkEnrolledCourses);

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
                showStudentTable();
            }
        });

        searchStudents.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showSearchStudent();
            }
        });

        checkEnrolledCourses.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showDisplayEnrolled();
            }
        });
    }

    // Method to create the Courses menu
    private void createCourseMenu(JMenuBar menuBar) {
        JMenu courseMenu = new JMenu("Courses");
        JMenuItem addCourse = new JMenuItem("Add/Remove Course");
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

    // Method to create the Teachers menu
    private void createTeacherMenu(JMenuBar menuBar) {
        JMenu teacherMenu = new JMenu("Teachers");
        JMenuItem hireTeacher = new JMenuItem("Add/Remove Teacher");
        JMenuItem listTeacherCourses = new JMenuItem("List Teacher Courses");
        JMenuItem assignCourse = new JMenuItem("Assign Course to Teacher");
        JMenuItem viewTeachers = new JMenuItem("View Teachers");
        JMenuItem generateReport = new JMenuItem("Generate Report");

        teacherMenu.add(hireTeacher);
        teacherMenu.add(viewTeachers);
        teacherMenu.add(assignCourse);
        teacherMenu.add(listTeacherCourses);
        teacherMenu.add(generateReport);

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

        viewTeachers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showTeacherTable();
            }
        });

        generateReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generateTeacherReport();
            }
        });
    }

    // Method to create the Administration menu
    private void createAdministrationMenu(JMenuBar menuBar) {
        JMenu administrationMenu = new JMenu("Administrative Staff");
        JMenuItem hireAdministration = new JMenuItem("Hire Administration Staff");
        JMenuItem generateReport = new JMenuItem("Generate Report");
        JMenuItem viewAdministrative = new JMenuItem("View Administrative Staff");
        administrationMenu.add(hireAdministration);
        administrationMenu.add(viewAdministrative);
        administrationMenu.add(generateReport);

        menuBar.add(administrationMenu);

        hireAdministration.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAdministrativeHiringForm();
            }
        });

        generateReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showReportDialog();
            }
        });

        viewAdministrative.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAdministrativeTable();
            }
        });
    }

    // Method to show the report dialog
    private void showReportDialog() {
        String reportContent = generateReportContent();
        JTextArea textArea = new JTextArea(reportContent);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton saveButton = new JButton("Save Report");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    AdministrativeStaff.exportToFile();
                    JOptionPane.showMessageDialog(null, "Report saved successfully.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error saving report: " + ex.getMessage());
                }
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(saveButton, BorderLayout.SOUTH);

        JDialog dialog = new JDialog(this, "Administrative Staff Report", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        dialog.add(panel);
        dialog.setVisible(true);
    }

    // Method to generate the report content
    private String generateReportContent() {
        return "Number of Students: " + University.studentCounter + "\n" +
                "Number of Teachers: " + University.teacherCounter + "\n" +
                "Number of Courses: " + University.courseCounter + "\n" +
                "Number of Administrative Staff: " + University.administrativeStaffCounter;
    }

    // Method to show the Add Course form
    private void showAddCourseForm() {
        new CourseAddForm(WIDTH, HEIGHT);
    }

    // Method to show the View Courses table
    private void showViewCourses() {
        new CourseViewTable(WIDTH, HEIGHT);
    }

    // Method to show the Search Course form
    private void showSearchCourse() {
        new CourseSearchForm(WIDTH, HEIGHT);
    }

    // Method to show the Alter Student form
    private void showAlterStudent() {
        new AlterCourseStudent(WIDTH, HEIGHT);
    }

    // Method to show the Add Student form
    private void showAddStudentForm() {
        new StudentAdmissionForm(WIDTH, HEIGHT);
    }

    // Method to show the Display Enrolled Courses form
    private void showDisplayEnrolled() {
        new DisplayEnrolledCourses(WIDTH, HEIGHT);
    }

    // Method to show the Teacher Hiring form
    private void showTeacherHiringForm() {
        new TeacherHiringForm(WIDTH, HEIGHT);
    }

    // Method to list the Teacher Courses
    private void ListTeacherCourses() {
        new ListTeacherCourses(WIDTH, HEIGHT);
    }

    // Method to show the Administrative Hiring form
    private void showAdministrativeHiringForm() {
        new AdministrationStaffHiringForm(WIDTH, HEIGHT);
    }

    // Method to assign a course to a teacher
    private void AssignCourseToTeacher() {
        new AssignCourseToTeacher(WIDTH, HEIGHT);
    }

    // Method to show the Teacher table
    private void showTeacherTable() {
        new ViewTeacherTable(WIDTH, HEIGHT);
    }

    // Method to show the Student table
    private void showStudentTable() {
        new ViewStudentTable(WIDTH, HEIGHT);
    }

    // Method to generate the Teacher report
    private void generateTeacherReport() {
        new GenerateTeacherReport(WIDTH, HEIGHT);
    }

    // Method to show the Administrative table
    private void showAdministrativeTable() {
        new ViewAdministrativeTable(WIDTH, HEIGHT);
    }

    // Method to show the Search Student form
    private void showSearchStudent() {
        new SearchStudentByName(WIDTH, HEIGHT);
    }
}