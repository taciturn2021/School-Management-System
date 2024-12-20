package models;

import repositories.Repository;
import utils.ExceptionUtility;
import utils.FileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
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

    // Adds a course to the course repository ensuring that the course ID is unique
    // University.java
    public static void addToCourseRepository(Course course) throws ExceptionUtility.InvalidInputException {
        if (ExceptionUtility.nullCheck(course)) {
            for (Course existingCourse : courseRepository.getAll()) {
                if (existingCourse.getCourseID().equalsIgnoreCase(course.getCourseID())) {
                    throw new ExceptionUtility.DuplicateIDException("Course ID must be unique.");
                }
            }
            courseRepository.add(course);
            courseCounter++;
            try {
                FileHandler.saveData();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Removes a course from the course repository and ensures that the course is removed from all students and teachers
    public static void removeFromCourseRepository(Course course) {
        try {
            if (ExceptionUtility.nullCheck(course)) {
                // Remove the course from each student's enrolled courses list
                for (Student student : course.getEnrolledStudents()) {
                    student.dropCourse(course);
                }
                // Clear the enrolled students list in the course
                course.setEnrolledStudents(new ArrayList<>());

                // Remove the course from each teacher's assigned courses list
                for (Teacher teacher : teacherRepository.getAll()) {
                    if (teacher.getCoursesTaught().contains(course)) {
                        teacher.getCoursesTaught().remove(course);
                    }
                }

                // Remove the course from the repository
                courseRepository.remove(course);
                courseCounter--;
                FileHandler.saveData();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Adds a student to the student repository
    public static void addToStudentRepository(Student student){
        try{
            if (ExceptionUtility.nullCheck(student)) {
                studentRepository.add(student);
                studentCounter++;
                FileHandler.saveData();
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    // adds a teacher to the teacher repository
    public static void addToTeacherRepository(Teacher teacher){
        try{
            if (ExceptionUtility.nullCheck(teacher)) {
                teacherRepository.add(teacher);
                teacherCounter++;
                FileHandler.saveData();
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    // adds an administrative staff member to the administrative staff repository
    public static void addToAdministrativeStaffRepository(AdministrativeStaff administrativeStaff){
        try{
            if (ExceptionUtility.nullCheck(administrativeStaff)) {
                administrativeStaffRepository.add(administrativeStaff);
                administrativeStaffCounter++;
                FileHandler.saveData();
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void exportToFile(String fileName) throws FileNotFoundException, IOException {
        FileHandler.saveData();
    }

    // Getters and Setters



}
