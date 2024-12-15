package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student extends Person {
    private int studentID;
    private List<Course> enrolledCourses;

    Student() {
        this(0, new ArrayList<Course>());
    }

    Student(int studentID, List<Course> enrolledCourses) {
        this.studentID = studentID;
        this.enrolledCourses = enrolledCourses;
    }

    public Student(int studentID, String name, String email, Date dateOfBirth, Address address) {
        super(name, email, dateOfBirth, address);
        this.studentID = studentID;
    }

    @Override
    public void displayDetails() {
        for (Course enrolled : enrolledCourses) {
            System.out.println("Course Id: " + enrolled.getCourseID());
            System.out.println("Course Name: " + enrolled.getCourseName());
            System.out.println("Course Credits: " + enrolled.getCourseCredits());
        }
    }

    public void enrollInCourse(Course course) {
        if (course != null && !enrolledCourses.contains(course)) {
            enrolledCourses.add(course);
        }
    }

    public void dropCourse(Course course) {
        if (course != null && enrolledCourses.contains(course)) {
            enrolledCourses.remove(course);
        }
    }

    public String toString() {
        return super.toString() + ", Student ID: " + getStudentID() + ", " + getEnrolledCourses();
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        if (studentID < 0) {
            this.studentID = studentID;
        } else {
            System.out.println("Invalid Student ID entered!");
        }
    }

    public boolean equals(Student student) {
        return this.studentID == student.getStudentID();
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }
}