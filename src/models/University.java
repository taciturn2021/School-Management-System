package models;

import repositories.Repository;

import java.util.ArrayList;

// To handle system-wide operations

public class University {
    private Repository<Student> studentRepository;
    private Repository<Teacher> teacherRepository;
    private Repository<Course> courseRepository;
    private Repository<AdministrativeStaff> administrativeStaffRepository;
    public University(){
        this.studentRepository = new Repository<>();
        this.teacherRepository = new Repository<>();
        this.courseRepository = new Repository<>();
        this.administrativeStaffRepository = new Repository<>();
    }

    // Functionality



    // Getters and Setters



}
