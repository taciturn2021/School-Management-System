package utils;

import java.io.*;
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
    public static void SaveData() throws IOException , FileNotFoundException {
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
            saveCounts(University.courseCounter, University.studentCounter, University.teacherCounter, University.administrativeStaffCounter);

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

    private static void saveCounts(int courseCounter, int studentCounter, int teacherCounter, int administrativeStaffCounter) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter("resources/data/counts.txt");
            fileWriter.write(courseCounter + "\n");
            fileWriter.write(studentCounter + "\n");
            fileWriter.write(teacherCounter + "\n");
            fileWriter.write(administrativeStaffCounter + "\n");
            fileWriter.close();
        } catch (FileNotFoundException ex) {
            FileNotFoundException exc = new FileNotFoundException("University data File not found!");
            System.out.println(exc.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void loadCounts() throws IOException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("resources/data/counts.txt"));
            University.courseCounter = Integer.parseInt(bufferedReader.readLine());
            University.studentCounter = Integer.parseInt(bufferedReader.readLine());
            University.teacherCounter = Integer.parseInt(bufferedReader.readLine());
            University.administrativeStaffCounter = Integer.parseInt(bufferedReader.readLine());
        } catch (FileNotFoundException ex) {
            FileNotFoundException exc = new FileNotFoundException("University data File not found!");
            System.out.println(exc.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


}
