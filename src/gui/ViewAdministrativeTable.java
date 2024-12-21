package gui;

import models.AdministrativeStaff;
import javax.swing.*;
import java.util.List;
import models.University;

public class ViewAdministrativeTable extends JFrame {

    // Constructor to initialize the ViewAdministrativeTable frame
    ViewAdministrativeTable(int WIDTH, int HEIGHT) {
        createAdministrativeTable(WIDTH, HEIGHT);
    }

    // Method to create the table view for administrative staff
    private void createAdministrativeTable(int WIDTH, int HEIGHT) {
        setTitle("View Teacher");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setLayout(null);

        // Back button to close the frame
        JButton backButton = new JButton("Back");
        backButton.setBounds(700, 10, 80, 30);
        backButton.addActionListener(e -> dispose());
        add(backButton);

        // Retrieve the list of administrative staff from the repository
        List<AdministrativeStaff> administrativeStaffs = University.administrativeStaffRepository.getAll();
        String[] columnNames = {"Staff ID", "Name", "Email", "Address"};
        Object[][] data = new Object[administrativeStaffs.size()][4];

        // Populate the table data with administrative staff information
        if (!administrativeStaffs.isEmpty()) {
            for (int i = 0; i < administrativeStaffs.size(); i++) {
                AdministrativeStaff administrativeStaff = administrativeStaffs.get(i);
                data[i][0] = administrativeStaff.getStaffID();
                data[i][1] = administrativeStaff.getName();
                data[i][2] = administrativeStaff.getEmail();
                data[i][3] = administrativeStaff.getAddress().toString();
            }
        } else {
            // If no administrative staff are found, display "No Data"
            data = new Object[1][4];
            data[0][0] = "No Data";
            data[0][1] = "No Data";
            data[0][2] = "No Data";
            data[0][3] = "No Data";
        }

        // Create a table with the administrative staff data
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(150, 60, 500, 500);
        add(scrollPane);

        setVisible(true);
    }
}