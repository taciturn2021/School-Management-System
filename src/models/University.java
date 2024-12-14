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

    private static int studentCounter = 0;
    private static int teacherCounter = 0;
    private static int courseCounter = 0;
    private static int administrativeStaffCounter = 0;

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

    public void displaySystemStats(){

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

        System.out.println("Students: " + studentCounter);
        System.out.println("Teachers: " + teacherCounter);
        System.out.println("Courses: " + courseCounter);
        System.out.println("AdministrativeStaff: " + administrativeStaffCounter);

    }

    // Getters and Setters



}
