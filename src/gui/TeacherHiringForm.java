package gui;

import models.*;
import models.Course;
import models.University;
import utils.ExceptionUtility;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TeacherHiringForm extends JFrame {

    public TeacherHiringForm(){
        createTeacherHiringForm();
    }

    public void createTeacherHiringForm(){
        setTitle("Teacher Hiring Form");
        setSize(500, 800);
        setLayout(null);
        setVisible(true);

        JLabel nameLabel = new JLabel("Enter Teacher's Specifications");
        nameLabel.setBounds(50, 20, 200, 30);

        JLabel teacherID = new JLabel("Teacher ID");
        teacherID.setBounds(50, 60, 100, 30);

        JTextField teacherIDField = new JTextField();
        teacherIDField.setBounds(150, 60, 200, 30);

        JLabel name = new JLabel("Name");
        name.setBounds(50, 100, 100, 30);

        JTextField nameField = new JTextField();
        nameField.setBounds(150, 100, 200, 30);

        JLabel email = new JLabel("Email");
        email.setBounds(50, 140, 100, 30);

        JTextField emailField = new JTextField();
        emailField.setBounds(150, 140, 200, 30);

        JLabel dateOfBirth = new JLabel("Date of Birth");
        dateOfBirth.setBounds(50, 180, 100, 30);

        JTextField dateOfBirthField = new JTextField();
        dateOfBirthField.setBounds(150, 180, 200, 30);

        JLabel address = new JLabel("Address");
        address.setBounds(50, 220, 100, 30);

        JLabel streetAddress = new JLabel("Street Address");
        streetAddress.setBounds(50, 260, 100, 30);

        JTextField streetAddressField = new JTextField();
        streetAddressField.setBounds(150, 260, 200, 30);

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

        JLabel department = new JLabel("Department");
        department.setBounds(50, 460, 100, 30);

        JTextField departmentField = new JTextField();
        departmentField.setBounds(150, 460, 200, 30);

        JLabel specialization = new JLabel("Specialization");
        specialization.setBounds(50, 500, 100, 30);

        JTextField specializationField = new JTextField();
        specializationField.setBounds(150, 500, 200, 30);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(50, 540 , 100, 30); // Adjusted y position to 700

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int teacherID = ExceptionUtility.parseTeacherID(teacherIDField.getText());
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

                    Address address = new Address(streetAddress, city, state, zipCode , country);
                    Teacher teacher = new Teacher(teacherID, name, email, dateOfBirth, address , department, specialization);
                    University.addToTeacherRepository(teacher);

                    JOptionPane.showMessageDialog(null, "Teacher added successfully.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

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
    }
}