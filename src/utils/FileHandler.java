package utils;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import models.*;

public class FileHandler implements Serializable{


    // Loads data university data
    public static void loadData() throws IOException, FileNotFoundException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("resources/data/uniData.dat"))) {
            while (true) {
                try {
                    Object object = objectInputStream.readObject();
                    if (object instanceof Teacher) {
                        University.teacherRepository.add((Teacher) object);

                    } else if (object instanceof Student) {
                        University.studentRepository.add((Student) object);

                    } else if (object instanceof Course) {
                        University.courseRepository.add((Course) object);

                    } else if (object instanceof AdministrativeStaff) {
                        University.administrativeStaffRepository.add((AdministrativeStaff) object);

                    }
                } catch (EOFException e) {
                    break; // End of file reached
                }
            }
            loadCounts();
        } catch (FileNotFoundException ex) {
            System.out.println("University data File not found!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("University data successfully loaded!");
    }

    // saves university data
    public static void saveData() throws IOException , FileNotFoundException {
        try {
            String filename = "resources/data/uniData.dat" ;

            ObjectOutputStream objectoutputStream = new ObjectOutputStream(new FileOutputStream(filename));

            for ( Teacher teacher : University.teacherRepository.getAll() ){
                Teacher tempTeacher = teacher ;
                objectoutputStream.writeObject(tempTeacher);
            }

            for ( Student student : University.studentRepository.getAll() ){
                Student tempStudent = student ;
                objectoutputStream.writeObject(tempStudent);
            }

            for ( AdministrativeStaff administrativeStaff : University.administrativeStaffRepository.getAll()){
                AdministrativeStaff tempAdminStaff = administrativeStaff ;
                objectoutputStream.writeObject(tempAdminStaff);
            }

            for ( Course course : University.courseRepository.getAll()){
                Course tempCourse = course ;
                objectoutputStream.writeObject(tempCourse);
            }
            saveAdminStaffReport();

        }
        catch (FileNotFoundException ex){
            FileNotFoundException exc = new FileNotFoundException("University data File not found!") ;
            System.out.println(exc.getMessage());
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        System.out.println("Data Successfully saved to the University data file");
    }


    private static void loadCounts() throws IOException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("resources/reports/System Reports/SystemReport_20241221_160005.txt"));
            bufferedReader.readLine(); // Skip the "Current System Stats:" line
            University.studentCounter = Integer.parseInt(bufferedReader.readLine().split(": ")[1]);
            University.teacherCounter = Integer.parseInt(bufferedReader.readLine().split(": ")[1]);
            University.courseCounter = Integer.parseInt(bufferedReader.readLine().split(": ")[1]);
            University.administrativeStaffCounter = Integer.parseInt(bufferedReader.readLine().split(": ")[1]);

        } catch (FileNotFoundException ex) {
            FileNotFoundException exc = new FileNotFoundException("University data File not found!");
            System.out.println(exc.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void saveTeacherReport(Teacher teacher) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        String directoryPath = "resources/reports/Teacher Reports/";
        String fileName = "resources/reports/Teacher Reports/" + teacher.getName().replaceAll("\\s+", "_") + "_" + formattedDateTime + ".txt";

        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs(); // Create the directory if it does not exist
        }

        if ( !new File( fileName ).exists() ){
            new File( fileName ).createNewFile();
        }

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Teacher: " + teacher.getName() + "\n");
            writer.write("Department: " + teacher.getDepartment() + "\n");
            writer.write("Specialization: " + teacher.getSpecialization() + "\n");
            writer.write("Courses Taught:\n");

            for (Course course : teacher.getCoursesTaught()) {
                writer.write("Course ID: " + course.getCourseID() + "\n");
                writer.write("Course Name: " + course.getCourseName() + "\n");
                writer.write("Course Credits: " + course.getCourseCredits() + "\n");
                writer.write("Number of Students Enrolled: " + course.getEnrolledStudents().size() + "\n");
                writer.write("Average Grade: " + course.calculateAverageGrade() + "\n");
                writer.write("\n");
            }
        } catch (IOException e) {
            throw new IOException("Error saving teacher report to file: " + e.getMessage());
        }
    }

    public static void saveAdminStaffReport() throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        String directoryPath = "resources/reports/System Reports/";
        String fileName = directoryPath + "SystemReport_" + formattedDateTime + ".txt";

        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs(); // Create the directory if it does not exist
        }

        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Current System Stats:\n");
            writer.write("Students: " + University.studentCounter + "\n");
            writer.write("Teachers: " + University.teacherCounter + "\n");
            writer.write("Courses: " + University.courseCounter + "\n");
            writer.write("Administrative Staff: " + University.administrativeStaffCounter + "\n");

        } catch (IOException e) {
            throw new IOException("Error saving administrative staff report to file: " + e.getMessage());
        }
    }
}




