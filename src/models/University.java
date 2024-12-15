package models;

import repositories.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// To handle system-wide operations

public class University {
    public static Repository<Student> studentRepository = new Repository<Student>();
    public static Repository<Teacher> teacherRepository = new Repository<Teacher>();
    public static Repository<Course> courseRepository = new Repository<Course>();
    public static Repository<AdministrativeStaff> administrativeStaffRepository = new Repository<AdministrativeStaff>();

    public static int studentCounter = 0;
    public static int teacherCounter = 0;
    public static int courseCounter = 0;
    public static int administrativeStaffCounter = 0;

    public University(){
    }


    // Functionality
    public static List<Course> filterCoursesByCredits(int minCredits) {
        List<Course> filteredCourses = new ArrayList<>();
        for (Course course : courseRepository.getAll()) {
            if (course.getCourseCredits() >= minCredits) {
                filteredCourses.add(course);
            }
        }
        return filteredCourses;
    }

    /* Since there is redundancy in the provided document, allowing enrollment into a course by both a course object and a student object
    * this method will be used to ensure there are no inconsistencies in the system by calling
    * both of the corresponding methods in this one method
     */
    public static void addStudentToCourse(Student student, Course course){
        course.addStudent(student);
        student.enrollInCourse(course);
    }

    public static void removeStudentFromCourse(Student student, Course course){
        course.removeStudent(student);
        student.dropCourse(course);
    }

    public void countUniversityData(){

        for ( Student student : this.studentRepository.getAll() ){
            studentCounter++ ;
        }

        for ( Teacher teacher : this.teacherRepository.getAll() ){
            teacherCounter++ ;
        }

        for ( Course course : this.courseRepository.getAll() ){
            courseCounter++ ;
        }

        for ( AdministrativeStaff administrativeStaff : this.administrativeStaffRepository.getAll() ){
            administrativeStaffCounter++ ;
        }

    }

    // Getters and Setters



}
