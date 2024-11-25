package models;

import repositories.Repository;

import java.util.ArrayList;

// To handle system-wide operations

public class University {
    private Repository<Student> studentRepository;
    private Repository<Teacher> teacherRepository;
    private Repository<Course> courseRepository;
    private Repository<AdministrativeStaff> administrativeStaffRepository;

    private static int studentCounter = 0;
    private static int teacherCounter = 0;
    private static int courseCounter = 0;
    private static int administrativeStaffCounter = 0;

    public University(){
        this.studentRepository = new Repository<>();
        this.teacherRepository = new Repository<>();
        this.courseRepository = new Repository<>();
        this.administrativeStaffRepository = new Repository<>();
    }

    public University(Repository<Student> studentRepository, Repository<Teacher> teacherRepository , Repository<Course> courseRepository , Repository<AdministrativeStaff> administrativeStaffRepository ){
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
        this.administrativeStaffRepository = administrativeStaffRepository;
    }

    // Functionality

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
