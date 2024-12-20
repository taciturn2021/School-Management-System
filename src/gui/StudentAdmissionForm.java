package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import models.Course;
import models.Student;
import models.University;
import models.Address;
import utils.ExceptionUtility;
import utils.FileHandler;

public class StudentAdmissionForm extends JFrame {

    public StudentAdmissionForm(int WIDTH, int HEIGHT) {
        createStudentAdmissionForm(WIDTH, HEIGHT);
    }

    void createStudentAdmissionForm(int WIDTH, int HEIGHT) {
        setTitle("Student Admission Form");
        setSize(800, 600);
        setLayout(null);

        JButton backButton = new JButton("Back");
        backButton.setBounds(WIDTH - 130, 10, 100, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JLabel nameLabel = new JLabel("Enter your Specifications");
        nameLabel.setBounds(50, 60, 500, 30);

        JLabel studentID = new JLabel("Student ID");
        studentID.setBounds(50, 100, 500, 30);

        JTextField studentIDField = new JTextField();
        studentIDField.setBounds(150, 100, 500, 30);

        JLabel name = new JLabel("Name");
        name.setBounds(50, 140, 500, 30);

        JTextField nameField = new JTextField();
        nameField.setBounds(150, 140, 500, 30);

        JLabel email = new JLabel("Email");
        email.setBounds(50, 180, 500, 30);

        JTextField emailField = new JTextField();
        emailField.setBounds(150, 180, 500, 30);

        JLabel dateOfBirth = new JLabel("DOB (DD-MM-YYYY)");
        dateOfBirth.setBounds(50, 220, 500, 30);

        JTextField dateOfBirthField = new JTextField();
        dateOfBirthField.setBounds(200, 220, 500, 30);

        JLabel address = new JLabel("Address");
        address.setBounds(50, 260, 500, 30);

        JLabel streetAddress = new JLabel("Street Address");
        streetAddress.setBounds(50, 300, 500, 30);

        JTextField addressField = new JTextField();
        addressField.setBounds(150, 300, 500, 30);

        JLabel city = new JLabel("City");
        city.setBounds(50, 340, 500, 30);

        JTextField cityField = new JTextField();
        cityField.setBounds(150, 340, 500, 30);

        JLabel state = new JLabel("State");
        state.setBounds(50, 380, 500, 30);

        JTextField stateField = new JTextField();
        stateField.setBounds(150, 380, 500, 30);

        JLabel zipCode = new JLabel("Zip Code");
        zipCode.setBounds(50, 420, 500, 30);

        JTextField zipCodeField = new JTextField();
        zipCodeField.setBounds(150, 420, 500, 30);

        JLabel country = new JLabel("Country");
        country.setBounds(50, 460, 500, 30);

        JTextField countryField = new JTextField();
        countryField.setBounds(150, 460, 500, 30);

        JLabel coursesLabel = new JLabel("Select Courses");
        coursesLabel.setBounds(50, 500, 500, 30);

        DefaultListModel<Course> courseListModel = new DefaultListModel<>();
        List<Course> courses = University.courseRepository.getAll();
        for (Course course : courses) {
            courseListModel.addElement(course);
        }

        JList<Course> courseList = new JList<>(courseListModel);
        courseList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane courseScrollPane = new JScrollPane(courseList);
        courseScrollPane.setBounds(150, 500, 500, 100);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(330, 610, 100, 30);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    String email = emailField.getText();
                    int studentID = ExceptionUtility.parseStudentID(studentIDField.getText());
                    Date dateOfBirth = ExceptionUtility.parseDateOfBirth(dateOfBirthField.getText());
                    Address address = new Address(addressField.getText(), cityField.getText(), stateField.getText(), zipCodeField.getText(), countryField.getText());

                    Student newStudent = new Student(studentID, name, email, dateOfBirth, address);
                    University.addToStudentRepository(newStudent);

                    for (Course course : courseList.getSelectedValuesList()) {
                        University.addStudentToCourse(newStudent, course);
                    }

                    JOptionPane.showMessageDialog(StudentAdmissionForm.this, "Student added successfully!");

                    try {
                        FileHandler.saveData();
                    }
                    catch (Exception ex) {
                        JOptionPane.showMessageDialog(StudentAdmissionForm.this, "An error occurred: " + ex.getMessage());
                    }

                    dispose();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(StudentAdmissionForm.this, "An error occurred: " + ex.getMessage());
                }


            }
        });



        add(backButton);
        add(nameLabel);
        add(studentID);
        add(studentIDField);
        add(name);
        add(nameField);
        add(email);
        add(emailField);
        add(dateOfBirth);
        add(dateOfBirthField);
        add(address);
        add(streetAddress);
        add(addressField);
        add(city);
        add(cityField);
        add(state);
        add(stateField);
        add(zipCode);
        add(zipCodeField);
        add(country);
        add(countryField);
        add(coursesLabel);
        add(courseScrollPane);
        add(submitButton);

        setVisible(true);
    }
}





