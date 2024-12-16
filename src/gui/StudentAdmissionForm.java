package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Course;
import models.Student;
import models.University;
import models.Address;
import utils.ExceptionUtility;

public class StudentAdmissionForm extends JFrame {

    public StudentAdmissionForm() {
        createStudentAdmissionForm();
    }

    void createStudentAdmissionForm() {
        setTitle("Student Admission Form");
        setSize(500, 700);
        setLayout(null);

        JLabel nameLabel = new JLabel("Enter your Specifications");
        nameLabel.setBounds(50, 20, 200, 30);

        JLabel studentID = new JLabel("Student ID");
        studentID.setBounds(50, 60, 100, 30);

        JTextField studentIDField = new JTextField();
        studentIDField.setBounds(150, 60, 200, 30);

        JLabel name = new JLabel("Name");
        name.setBounds(50, 100, 100, 30);

        JTextField nameField = new JTextField();
        nameField.setBounds(150, 100, 200, 30);

        JLabel email = new JLabel("Email");
        email.setBounds(50, 140, 100, 30);

        JTextField emailField = new JTextField();
        emailField.setBounds(150, 140, 200, 30);

        JLabel dateOfBirth = new JLabel("DOB (DD-MM-YYYY)");
        dateOfBirth.setBounds(50, 180, 100, 30);

        JTextField dateOfBirthField = new JTextField();
        dateOfBirthField.setBounds(150, 180, 200, 30);

        JLabel address = new JLabel("Address");
        address.setBounds(50, 220, 100, 30);

        JLabel streetAddress = new JLabel("Street Address");
        streetAddress.setBounds(50, 260, 100, 30);

        JTextField addressField = new JTextField();
        addressField.setBounds(150, 260, 200, 30);

        JLabel city = new JLabel("City");
        city.setBounds(50, 300, 100, 30);

        JTextField cityField = new JTextField();
        cityField.setBounds(150, 300, 200, 30);

        JLabel state = new JLabel("State");
        state.setBounds(50, 340, 100, 30);

        JTextField stateField = new JTextField();
        stateField.setBounds(150, 340, 200, 30);

        JLabel zipCode = new JLabel("Zip Code");
        zipCode.setBounds(50, 380, 100, 30);

        JTextField zipCodeField = new JTextField();
        zipCodeField.setBounds(150, 380, 200, 30);

        JLabel country = new JLabel("Country");
        country.setBounds(50, 420, 100, 30);

        JTextField countryField = new JTextField();
        countryField.setBounds(150, 420, 200, 30);

        JLabel coursesLabel = new JLabel("Select Courses");
        coursesLabel.setBounds(50, 460, 100, 30);

        DefaultListModel<Course> courseListModel = new DefaultListModel<>(); // List of courses student can enroll into
        List<Course> courses = University.courseRepository.getAll();
        for (Course course : courses) {
            courseListModel.addElement(course);
        }

        JList<Course> courseList = new JList<>(courseListModel);
        courseList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane courseScrollPane = new JScrollPane(courseList);
        courseScrollPane.setBounds(150, 460, 200, 100);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(200, 570, 100, 30);

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

                    for (int i = 0; i < courseListModel.getSize(); i++) {
                        University.addStudentToCourse(newStudent, courseListModel.getElementAt(i));
                    }

                    JOptionPane.showMessageDialog(StudentAdmissionForm.this, "Student added successfully!");
                    dispose();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(StudentAdmissionForm.this, "An error occurred: " + ex.getMessage());
                }
            }
        });

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





