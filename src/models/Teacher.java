package models;

import java.util.ArrayList;

public class Teacher extends Person{
    private int teacherID;
    private String department;
    private String specialization;
    private ArrayList<Course> coursesTaught;

    public Teacher(){
        this(0, "", "", new ArrayList<Course>());
    }

    public Teacher(int teacherID, String department, String specialization, ArrayList<Course> coursesTaught){
        this.teacherID = teacherID;
        this.department = department;
        this.specialization = specialization;
        this.coursesTaught = coursesTaught;
    }
    // Functionality

    public void assignCourse(Course course){
        if ( course != null ) {
            coursesTaught.add( course ) ;
            System.out.println("Teacher " + getName() + "assigned to teach " + course.getCourseName() + "." );
        }
    }

    public void displayCourses(){
        for ( Course enrolled : coursesTaught ){
            System.out.println("Course Id: " + enrolled.getCourseId() );
            System.out.println("Course Name: " + enrolled.getCourseName() );
            System.out.println("Course Credits: " + enrolled.getCourseCredits() );
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



