package models;

import utils.FileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class AdministrativeStaff extends Person implements Serializable {
    private int staffID;
    private String role;
    private String department;

    public AdministrativeStaff(){
        this(0, "", new Date(), "", new Address("", "", "", "", ""), "", "");
    }

    public AdministrativeStaff(int staffID, String name , Date dateOfBirth , String email , Address address , String role, String department){
        super (name, email , dateOfBirth , address);
        this.staffID = staffID;
        this.role = role;
        this.department = department;
    }
    // Functionality

    public String toString(){
        return getStaffID()+ " " + getRole() + " " + getDepartment() ;
    }



    public static void exportToFile() throws FileNotFoundException, IOException {
        try {
            FileHandler.saveAdminStaffReport();
        } catch (IOException e) {
            throw new IOException("Error exporting Administrative Staff report to file.");
        }
    }
    // Getters and Setters


    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
