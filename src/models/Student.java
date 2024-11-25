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
    @Override
    public void displayDetails(){
        for ( Course enrolled : enrolledCourses ){
            System.out.println("Course Id: " + enrolled.getCourseID() );
            System.out.println("Course Name: " + enrolled.getCourseName() );
            System.out.println("Course Credits: " + enrolled.getCourseCredits() );
        }
    }

    public void enrollinCourse(Course course){
        if ( course != null ){
            enrolledCourses.add(course) ;
        }
    }

    public String toString() {
        return super.toString() + "," + "Student ID: " + getStudentID() + "," + getEnrolledCourses();
    }

    // Getters and Setters

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        if ( studentID < 0 ) {
            this.studentID = studentID;
        }else{
            System.out.println("Invalid Student ID entered!");
        }
    }

    public ArrayList<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(ArrayList<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }



}
