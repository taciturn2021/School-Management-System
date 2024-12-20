package models;

import models.Student;
import models.Teacher;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {
    private String courseID;
    private String courseName;
    private int courseCredits;
    private Teacher assignedTeacher;
    private ArrayList<Student> enrolledStudents;
    private ArrayList<Double> studentGrades; // ArrayList to track grades

    public Course() {
        this("0", "", 0, null, new ArrayList<Student>());
    }

    public Course(String courseID, String courseName, int courseCredits) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseCredits = courseCredits;
        this.enrolledStudents = new ArrayList<>();
        this.studentGrades = new ArrayList<>(); // Initialize the ArrayList
    }

    public Course(String courseID, String courseName, int courseCredits, Teacher assignedTeacher, ArrayList<Student> enrolledStudents) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseCredits = courseCredits;
        this.assignedTeacher = assignedTeacher;
        this.enrolledStudents = enrolledStudents;
        this.studentGrades = new ArrayList<>(); // Initialize the ArrayList
    }

    // Functionality

    public void addStudent(Student student) {
        if (student != null && !enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
            studentGrades.add(0.0); // Initialize grade to 0.0
            System.out.println("Student " + student.getName() + " Added to " + getCourseName());
        }
    }

    public void removeStudent(Student student) {
        int index = enrolledStudents.indexOf(student);
        if (index != -1) {
            enrolledStudents.remove(index);
            studentGrades.remove(index); // Remove the student's grade
        }
    }

    public void assignGrade(Student student, double grade) {
        int index = enrolledStudents.indexOf(student);
        if (index != -1) {
            studentGrades.set(index, grade); // Assign or update the grade
        }
    }

    public Double getGrade(Student student) {
        int index = enrolledStudents.indexOf(student);
        if (index != -1) {
            return studentGrades.get(index); // Retrieve the grade for a student
        }
        return null;
    }

    public void calculateAverageGrade() {
        double totalGrades = 0;
        for (Double grade : studentGrades) {
            totalGrades += grade;
        }
        double averageGrade = totalGrades / studentGrades.size();
        System.out.println("Average Grade: " + averageGrade);
    }

    // Getters and Setters

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public ArrayList<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(ArrayList<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public Teacher getAssignedTeacher() {
        return assignedTeacher;
    }

    public void setAssignedTeacher(Teacher assignedTeacher) {
        this.assignedTeacher = assignedTeacher;
    }

    public int getCourseCredits() {
        return courseCredits;
    }

    public void setCourseCredits(int courseCredits) {
        this.courseCredits = courseCredits;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String toString() {
        return getCourseID() + " " + getCourseName();
    }
}