package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import models.Student;
import models.University;
import models.Address;
import utils.ExceptionUtility;
import utils.FileHandler;

public class StudentAdmissionForm extends JFrame {

    // Constructor to initialize the StudentAdmissionForm frame
    public StudentAdmissionForm(int WIDTH, int HEIGHT) {
        createStudentAdmissionForm(WIDTH, HEIGHT);
    }

    // Method to create the form for student admission
    void createStudentAdmissionForm(int WIDTH, int HEIGHT) {
        setTitle("Student Admission Form");
        setSize(800, 600);
        setLayout(null);

        // Back button to close the form
        JButton backButton = new JButton("Back");
        backButton.setBounds(WIDTH - 130, 10, 100, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Label and combo box for selecting action (Add or Remove)
        JLabel actionLabel = new JLabel("Action:");
        actionLabel.setBounds(50, 20, 500, 30);
        String[] actions = {"Add", "Remove"};
        JComboBox<String> actionComboBox = new JComboBox<>(actions);
        actionComboBox.setBounds(150, 20, 500, 30);

        // Label for entering student's specifications
        JLabel nameLabel = new JLabel("Enter your Specifications");
        nameLabel.setBounds(50, 60, 500, 30);

        // Labels and text fields for student's details
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

        // Submit button to add or remove a student
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(330, 510, 100, 30);

        // Action listener to enable or disable fields based on selected action
        actionComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedAction = (String) actionComboBox.getSelectedItem();
                boolean isAddAction = "Add".equals(selectedAction);
                nameField.setEnabled(isAddAction);
                emailField.setEnabled(isAddAction);
                dateOfBirthField.setEnabled(isAddAction);
                addressField.setEnabled(isAddAction);
                cityField.setEnabled(isAddAction);
                stateField.setEnabled(isAddAction);
                zipCodeField.setEnabled(isAddAction);
                countryField.setEnabled(isAddAction);
            }
        });

        // Action listener to handle submit button click
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedAction = (String) actionComboBox.getSelectedItem();
                String studentIDText = studentIDField.getText();

                if ("Add".equals(selectedAction)) {
                    try {
                        // Check if any field is empty
                        if (studentIDField.getText().isEmpty() || nameField.getText().isEmpty() || emailField.getText().isEmpty() ||
                                dateOfBirthField.getText().isEmpty() || addressField.getText().isEmpty() || cityField.getText().isEmpty() ||
                                stateField.getText().isEmpty() || zipCodeField.getText().isEmpty() || countryField.getText().isEmpty()) {
                            throw new Exception("All fields must be filled out.");
                        }

                        // Parse and validate input fields
                        String name = nameField.getText();
                        String email = emailField.getText();
                        int studentID = ExceptionUtility.parseStudentID(studentIDText, true);
                        Date dateOfBirth = ExceptionUtility.parseDateOfBirth(dateOfBirthField.getText());
                        Address address = new Address(addressField.getText(), cityField.getText(), stateField.getText(), zipCodeField.getText(), countryField.getText());

                        // Create a new student and add to the repository
                        Student newStudent = new Student(studentID, name, email, dateOfBirth, address);
                        University.addToStudentRepository(newStudent);

                        JOptionPane.showMessageDialog(StudentAdmissionForm.this, "Student added successfully!");

                        // Save data to file
                        try {
                            FileHandler.saveData();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(StudentAdmissionForm.this, "An error occurred: " + ex.getMessage());
                        }

                        dispose();

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(StudentAdmissionForm.this, "An error occurred: " + ex.getMessage());
                    }
                } else if ("Remove".equals(selectedAction)) {
                    try {
                        // Parse and validate student ID
                        int studentID = ExceptionUtility.parseStudentID(studentIDText);
                        Student student = null;
                        for (Student s : University.studentRepository.getAll()) {
                            if (s.getStudentID() == studentID) {
                                student = s;
                                break;
                            }
                        }

                        // Remove student from the repository if found
                        if (student != null) {
                            University.removeFromStudentRepository(student);
                            JOptionPane.showMessageDialog(StudentAdmissionForm.this, "Student removed successfully!");
                        } else {
                            JOptionPane.showMessageDialog(StudentAdmissionForm.this, "Student not found!", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        // Save data to file
                        try {
                            FileHandler.saveData();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(StudentAdmissionForm.this, "An error occurred: " + ex.getMessage());
                        }

                        dispose();

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(StudentAdmissionForm.this, "An error occurred: " + ex.getMessage());
                    }
                }
            }
        });

        // Add components to the frame
        add(backButton);
        add(actionLabel);
        add(actionComboBox);
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
        add(submitButton);

        setVisible(true);
    }
}