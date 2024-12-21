package gui;

import models.Address;
import models.AdministrativeStaff;
import models.University;
import utils.ExceptionUtility;
import utils.FileHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class AdministrationStaffHiringForm extends JFrame {

    // Constructor to initialize the AdministrationStaffHiringForm frame
    public AdministrationStaffHiringForm(int WIDTH, int HEIGHT) {
        createAdministarionStaffHiringForm(WIDTH, HEIGHT);
    }

    // Method to create and set up the AdministrationStaffHiringForm frame
    private void createAdministarionStaffHiringForm(int WIDTH, int HEIGHT) {
        setTitle("Administrative Staff Hiring Form");
        setSize(WIDTH, HEIGHT); // Set frame size
        setLayout(null);
        setVisible(true);

        // Label and text field for entering the administrative staff ID
        JLabel adminStaffId = new JLabel("Administrative Staff ID");
        adminStaffId.setBounds(50, 50, 200, 30);
        JTextField adminStaffIdField = new JTextField();
        adminStaffIdField.setBounds(250, 50, 500, 30);

        // Label and text field for entering the name
        JLabel name = new JLabel("Name");
        name.setBounds(50, 90, 200, 30);
        JTextField nameField = new JTextField();
        nameField.setBounds(250, 90, 500, 30);

        // Label and text field for entering the email
        JLabel email = new JLabel("Email");
        email.setBounds(50, 130, 200, 30);
        JTextField emailField = new JTextField();
        emailField.setBounds(250, 130, 500, 30);

        // Label and text field for entering the date of birth
        JLabel dateOfBirth = new JLabel("Date of Birth");
        dateOfBirth.setBounds(50, 170, 200, 30);
        JTextField dateOfBirthField = new JTextField();
        dateOfBirthField.setBounds(250, 170, 500, 30);

        // Label for entering the address
        JLabel address = new JLabel("Address");
        address.setBounds(50, 210, 200, 30);

        // Label and text field for entering the street address
        JLabel streetAddress = new JLabel("Street Address");
        streetAddress.setBounds(50, 250, 200, 30);
        JTextField streetAddressField = new JTextField();
        streetAddressField.setBounds(250, 250, 500, 30);

        // Label and text field for entering the city
        JLabel city = new JLabel("City");
        city.setBounds(50, 290, 200, 30);
        JTextField cityField = new JTextField();
        cityField.setBounds(250, 290, 500, 30);

        // Label and text field for entering the state
        JLabel state = new JLabel("State");
        state.setBounds(50, 330, 200, 30);
        JTextField stateField = new JTextField();
        stateField.setBounds(250, 330, 500, 30);

        // Label and text field for entering the zip code
        JLabel zipCode = new JLabel("Zip Code");
        zipCode.setBounds(50, 370, 200, 30);
        JTextField zipCodeField = new JTextField();
        zipCodeField.setBounds(250, 370, 500, 30);

        // Label and text field for entering the country
        JLabel country = new JLabel("Country");
        country.setBounds(50, 410, 200, 30);
        JTextField countryField = new JTextField();
        countryField.setBounds(250, 410, 500, 30);

        // Label and text field for entering the role
        JLabel role = new JLabel("Role");
        role.setBounds(50, 450, 200, 30);
        JTextField roleField = new JTextField();
        roleField.setBounds(250, 450, 500, 30);

        // Label and text field for entering the department
        JLabel department = new JLabel("Department");
        department.setBounds(50, 490, 200, 30);
        JTextField departmentField = new JTextField();
        departmentField.setBounds(250, 490, 500, 30);

        // Submit button to add the administrative staff
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(350, 530, 100, 30);

        // Action listener to handle the submit button click
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Check if any field is empty
                    if (adminStaffIdField.getText().isEmpty() || nameField.getText().isEmpty() || emailField.getText().isEmpty() ||
                            dateOfBirthField.getText().isEmpty() || streetAddressField.getText().isEmpty() || cityField.getText().isEmpty() ||
                            stateField.getText().isEmpty() || zipCodeField.getText().isEmpty() || countryField.getText().isEmpty() ||
                            roleField.getText().isEmpty() || departmentField.getText().isEmpty()) {
                        throw new Exception("All fields must be filled out.");
                    }

                    // Parse and validate the input fields
                    int staffId = ExceptionUtility.parseStaffID(adminStaffIdField.getText(), true);
                    String staffName = nameField.getText();
                    String staffEmail = emailField.getText();
                    Date staffDateOfBirth = ExceptionUtility.parseDateOfBirth(dateOfBirthField.getText());
                    String staffStreetAddress = streetAddressField.getText();
                    String staffCity = cityField.getText();
                    String staffState = stateField.getText();
                    String staffZipCode = zipCodeField.getText();
                    String staffCountry = countryField.getText();
                    String staffRole = roleField.getText();
                    String staffDepartment = departmentField.getText();

                    // Create an address object
                    Address address = new Address(staffStreetAddress, staffCity, staffState, staffZipCode, staffCountry);

                    // Create an administrative staff object
                    AdministrativeStaff administrativeStaff = new AdministrativeStaff(staffId, staffName, staffDateOfBirth, staffEmail, address, staffRole, staffDepartment);

                    // Add the administrative staff to the repository
                    University.addToAdministrativeStaffRepository(administrativeStaff);

                    JOptionPane.showMessageDialog(null, "Administration Staff added successfully.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }

                try {
                    // Save the updated data to the file
                    FileHandler.saveData();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        // Add components to the frame
        add(adminStaffId);
        add(adminStaffIdField);
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
        add(role);
        add(roleField);
        add(department);
        add(departmentField);
        add(submitButton);
    }
}