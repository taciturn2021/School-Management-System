package models;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import utils.FileHandler;

public class Teacher extends Person implements Serializable , Reportable{
    private int teacherID;
    private String department;
    private String specialization;
    private ArrayList<Course> coursesTaught;

    public Teacher() {
        this(0, "", "", new Date(), new Address(), "", "");
        this.coursesTaught = new ArrayList<>();
    }

    public Teacher(int teacherID, String name, String email, Date dateOfBirth, Address address , String department, String specialization) {
        super(name, email, dateOfBirth , address);
        this.teacherID = teacherID;
        this.department = department;
        this.specialization = specialization;
        this.coursesTaught = new ArrayList<>();
    }

    // Functionality

    public void assignCourse(Course course){
        if ( course != null ) {
            coursesTaught.add( course ) ;
            System.out.println("Teacher " + getName() + "assigned to teach " + course.getCourseName() + "." );
        }
    }

    public void displayDetails(){
        for ( Course enrolled : coursesTaught ){
            System.out.println("Course Id: " + enrolled.getCourseID() );
            System.out.println("Course Name: " + enrolled.getCourseName() );
            System.out.println("Course Credits: " + enrolled.getCourseCredits() );
        }
    }

    public String toString(){
        return super.toString() + "," + "Teacher ID: " + getTeacherID() + "," + "Department: " + getDepartment() + "," + "Specialization: " + getSpecialization() + "," + "Courses Taught: " + getCoursesTaught() ;
    }

    public ArrayList<Course> generateReport(){
        return coursesTaught ;
    }

    @Override
    public void exportToFile() throws IOException {
        try {
            FileHandler.saveData();

        } catch (IOException e) {
            throw new IOException("Error exporting Teacher to file: " + e.getMessage());
        }
    }
    // Getters and Setters


    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public ArrayList<Course> getCoursesTaught() {
        return coursesTaught;
    }

    public void setCoursesTaught(ArrayList<Course> coursesTaught) {
        this.coursesTaught = coursesTaught;
    }
}



