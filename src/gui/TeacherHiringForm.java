package gui;

import models.*;
import utils.ExceptionUtility;
import utils.FileHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class TeacherHiringForm extends JFrame {

    // Constructor to initialize the TeacherHiringForm frame
    public TeacherHiringForm(int WIDTH, int HEIGHT) {
        createTeacherHiringForm(WIDTH, HEIGHT);
    }

    // Method to create the form for hiring teachers
    private void createTeacherHiringForm(int WIDTH, int HEIGHT) {
        setTitle("Teacher Hiring Form");
        setSize(WIDTH, HEIGHT + 50);
        setLayout(null);

        // Label and combo box for selecting action (Add or Remove)
        JLabel actionLabel = new JLabel("Action:");
        actionLabel.setBounds(50, 20, 500, 30);
        String[] actions = {"Add", "Remove"};
        JComboBox<String> actionComboBox = new JComboBox<>(actions);
        actionComboBox.setBounds(250, 20, 500, 30);

        // Label for entering teacher's specifications
        JLabel nameLabel = new JLabel("Enter Teacher's Specifications");
        nameLabel.setBounds(50, 60, 500, 30);

        // Labels and text fields for teacher's details
        JLabel teacherID = new JLabel("Teacher ID");
        teacherID.setBounds(50, 100, 500, 30);
        JTextField teacherIDField = new JTextField();
        teacherIDField.setBounds(250, 100, 500, 30);

        JLabel name = new JLabel("Name");
        name.setBounds(50, 140, 500, 30);
        JTextField nameField = new JTextField();
        nameField.setBounds(250, 140, 500, 30);

        JLabel email = new JLabel("Email");
        email.setBounds(50, 180, 500, 30);
        JTextField emailField = new JTextField();
        emailField.setBounds(250, 180, 500, 30);

        JLabel dateOfBirth = new JLabel("Date of Birth");
        dateOfBirth.setBounds(50, 220, 500, 30);
        JTextField dateOfBirthField = new JTextField();
        dateOfBirthField.setBounds(250, 220, 500, 30);

        JLabel address = new JLabel("Address");
        address.setBounds(50, 260, 500, 30);

        JLabel streetAddress = new JLabel("Street Address");
        streetAddress.setBounds(50, 300, 500, 30);
        JTextField streetAddressField = new JTextField();
        streetAddressField.setBounds(250, 300, 500, 30);

        JLabel city = new JLabel("City");
        city.setBounds(50, 340, 500, 30);
        JTextField cityField = new JTextField();
        cityField.setBounds(250, 340, 500, 30);

        JLabel state = new JLabel("State");
        state.setBounds(50, 380, 500, 30);
        JTextField stateField = new JTextField();
        stateField.setBounds(250, 380, 500, 30);

        JLabel zipCode = new JLabel("Zip Code");
        zipCode.setBounds(50, 420, 500, 30);
        JTextField zipCodeField = new JTextField();
        zipCodeField.setBounds(250, 420, 500, 30);

        JLabel country = new JLabel("Country");
        country.setBounds(50, 460, 500, 30);
        JTextField countryField = new JTextField();
        countryField.setBounds(250, 460, 500, 30);

        JLabel department = new JLabel("Department");
        department.setBounds(50, 500, 500, 30);
        JTextField departmentField = new JTextField();
        departmentField.setBounds(250, 500, 500, 30);

        JLabel specialization = new JLabel("Specialization");
        specialization.setBounds(50, 540, 500, 30);
        JTextField specializationField = new JTextField();
        specializationField.setBounds(250, 540, 500, 30);

        // Submit button to add or remove a teacher
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(400, 580, 100, 30);

        // Back button to close the form
        JButton backButton = new JButton("Back");
        backButton.setBounds(550, 580, 100, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Action listener to enable or disable fields based on selected action
        actionComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedAction = (String) actionComboBox.getSelectedItem();
                boolean isAddAction = "Add".equals(selectedAction);
                nameField.setEnabled(isAddAction);
                emailField.setEnabled(isAddAction);
                dateOfBirthField.setEnabled(isAddAction);
                streetAddressField.setEnabled(isAddAction);
                cityField.setEnabled(isAddAction);
                stateField.setEnabled(isAddAction);
                zipCodeField.setEnabled(isAddAction);
                countryField.setEnabled(isAddAction);
                departmentField.setEnabled(isAddAction);
                specializationField.setEnabled(isAddAction);
            }
        });

        // Action listener to handle submit button click
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedAction = (String) actionComboBox.getSelectedItem();
                String teacherIDText = teacherIDField.getText();

                if ("Add".equals(selectedAction)) {
                    try {
                        int teacherID = ExceptionUtility.parseTeacherID(teacherIDText, true);
                        String name = nameField.getText();
                        String email = emailField.getText();
                        Date dateOfBirth = ExceptionUtility.parseDateOfBirth(dateOfBirthField.getText());
                        String streetAddress = streetAddressField.getText();
                        String city = cityField.getText();
                        String state = stateField.getText();
                        String country = countryField.getText();
                        String department = departmentField.getText();
                        String specialization = specializationField.getText();
                        String zipCode = zipCodeField.getText();

                        Address address = new Address(streetAddress, city, state, zipCode, country);
                        Teacher teacher = new Teacher(teacherID, name, email, dateOfBirth, address, department, specialization);
                        University.addToTeacherRepository(teacher);

                        JOptionPane.showMessageDialog(null, "Teacher added successfully.");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                    }

                    try {
                        FileHandler.saveData();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                    }

                } else if ("Remove".equals(selectedAction)) {
                    try {
                        int teacherID = ExceptionUtility.parseTeacherID(teacherIDText);
                        Teacher teacher = null;
                        for (Teacher t : University.teacherRepository.getAll()) {
                            if (t.getTeacherID() == teacherID) {
                                teacher = t;
                                break;
                            }
                        }

                        if (teacher != null) {
                            University.removeFromTeacherRepository(teacher);
                            JOptionPane.showMessageDialog(null, "Teacher removed successfully.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Teacher not found!", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        try {
                            FileHandler.saveData();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                        }

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                    }
                }
            }
        });

        // Add components to the frame
        add(actionLabel);
        add(actionComboBox);
        add(nameLabel);
        add(teacherID);
        add(teacherIDField);
        add(name);
        add(nameField);
        add(email);
        add(emailField);
        add(dateOfBirth);
        add(dateOfBirthField);
        add(address);
        add(streetAddress);
        add(streetAddressField);
        add(city);
        add(cityField);
        add(state);
        add(stateField);
        add(zipCode);
        add(zipCodeField);
        add(country);
        add(countryField);
        add(department);
        add(departmentField);
        add(specialization);
        add(specializationField);
        add(submitButton);
        add(backButton);

        setVisible(true);
    }
}