package gui;

import models.AdministrativeStaff;
import models.University;
import utils.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.IllegalFormatWidthException;

public class GUIWindow extends JFrame {
    public static final int WIDTH = 800 ;
    public static final int HEIGHT = 600 ;

    public GUIWindow() {
        setTitle("School Management System");
        setSize(WIDTH, 400);

        // Set the background color of the content pane
        getContentPane().setBackground(Color.GRAY);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        try {
            FileHandler.loadData();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage());
        }
        loadComponents(); // Load components
        setVisible(true);
    }

    private void loadComponents() {
        // Create the top bar menu
        addMenuItems();

        // Set the layout and add components
        setLayout(new BorderLayout());

        // Create a panel to hold the welcome label and image label
        JPanel panel = new JPanel(new BorderLayout());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.GRAY);

        // Load the image
        ImageIcon originalIcon = new ImageIcon("src/images/School Management System.png");
        Image originalImage = originalIcon.getImage();

        // Scale the image to the desired size
        Image scaledImage = originalImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Create a label with the scaled image
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create the welcome label
        JLabel welcomeLabel = new JLabel("Welcome to the School Management System", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 25));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add the image label and welcome label to the panel
        panel.add(imageLabel , BorderLayout.CENTER);
        panel.add(welcomeLabel , BorderLayout.CENTER);

        // Add the panel to the frame
        add(panel, BorderLayout.CENTER);
    }

    private void addMenuItems() {
        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();
        createCourseMenu(menuBar); // Create the Courses menu
        createStudentMenu(menuBar);// Create the Students menu
        createTeacherMenu(menuBar);// Create the Teacher menu
        createAdministrationMenu(menuBar);// Create the Administration menu
    }

    private void createStudentMenu(JMenuBar menuBar) {
        // Create the Students menu
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

    private void createCourseMenu(JMenuBar menuBar) {
        // Create the Courses menu
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

    private void createTeacherMenu(JMenuBar menuBar){

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

    private String generateReportContent() {
        return "Number of Students: " + University.studentCounter + "\n" +
                "Number of Teachers: " + University.teacherCounter + "\n" +
                "Number of Courses: " + University.courseCounter + "\n" +
                "Number of Administrative Staff: " + University.administrativeStaffCounter;
    }


    private void showAddCourseForm() {
        new CourseAddForm(WIDTH , HEIGHT);
    }
    private void showViewCourses() {
        new CourseViewTable(WIDTH , HEIGHT);
    }
    private void showSearchCourse() {
        new CourseSearchForm(WIDTH , HEIGHT);
    }
  
    private void showAlterStudent() {
        new AlterCourseStudent(WIDTH , HEIGHT);
    }

    private void showAddStudentForm() {
        new StudentAdmissionForm(WIDTH , HEIGHT);
    }

    private void showDisplayEnrolled() {
        new DisplayEnrolledCourses(WIDTH , HEIGHT);
    }

  
    private void showTeacherHiringForm() { new TeacherHiringForm(WIDTH , HEIGHT); }
    private void ListTeacherCourses() { new ListTeacherCourses(WIDTH , HEIGHT); }
    private void showAdministrativeHiringForm() { new AdministrationStaffHiringForm(WIDTH , HEIGHT); }
    private void AssignCourseToTeacher() { new AssignCourseToTeacher(WIDTH , HEIGHT); }
    private void showTeacherTable() { new ViewTeacherTable(WIDTH , HEIGHT); }
    private void showStudentTable() { new ViewStudentTable(WIDTH , HEIGHT); }
    private void generateTeacherReport() { new GenerateTeacherReport(WIDTH , HEIGHT); }
    private void showAdministrativeTable() { new ViewAdministrativeTable(WIDTH , HEIGHT); }
    private void showSearchStudent() { new SearchStudentByName(WIDTH , HEIGHT); }

}