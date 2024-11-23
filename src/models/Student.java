package models;

import java.util.ArrayList;

public class Student extends Person{
    private int studentID;
    private ArrayList<Course> enrolledCourses;

    Student(){
        this(0, new ArrayList<Course>());
    }
    Student(int studentID, ArrayList<Course> enrolledCourses){
        this.studentID = studentID;
        this.enrolledCourses = enrolledCourses;
    }
    // Functionality

    // Implement here

    // Getters and Setters

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public ArrayList<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(ArrayList<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }


}
