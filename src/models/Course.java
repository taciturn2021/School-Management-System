package models;

import java.util.ArrayList;

public class Course {
    private int courseID;
    private String courseName;
    private int courseCredits;
    private Teacher assignedTeacher;
    private ArrayList<Student> enrolledStudents;

    public Course(){
        this(0, "", 0, new Teacher(), new ArrayList<Student>());
    }

    public Course(int courseID, String courseName, int courseCredits, Teacher assignedTeacher, ArrayList<Student> enrolledStudents){
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseCredits = courseCredits;
        this.assignedTeacher = assignedTeacher;
        this.enrolledStudents = enrolledStudents;
    }
    // Functionality

    public void addStudent(Student student){
        if ( student != null){
            enrolledStudents.add( student ) ;
            System.out.println("Student " + student.getName() + " Added to " + getCourseName() );
        }
    }

    public void removeStudent(Student std){
        for (Student student : enrolledStudents ){
            if ( student.equals(std) ){
                enrolledStudents.remove(student) ;
            }
        }
    }

    public void calculateAverageGrade(){
        double averageGrade = 0;

        for ( Student student : enrolledStudents ){
            
        }
    }

    // Getters and Setters


    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
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
}
