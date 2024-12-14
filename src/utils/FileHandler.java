package utils;

import java.io.*;
import java.util.Scanner;

import models.*;

public class FileHandler implements Serializable{


    // Loads data university data
    public void loadData(String filename ) throws IOException , FileNotFoundException {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream( new FileInputStream( "University_data.dat" )) ;

            while ( new Scanner( objectInputStream ).hasNext() ){

                Object object = objectInputStream.readObject() ;

                if ( object instanceof Teacher){
                    University.teacherRepository.add((Teacher) object);
                }
                if ( object instanceof Student){
                    University.studentRepository.add((Student) object);
                }
                if ( object instanceof Course){
                    University.administrativeStaffRepository.add( (AdministrativeStaff) object );
                }
                if ( object instanceof AdministrativeStaff){
                    University.courseRepository.add( (Course) object );
                }

            }
        }
        catch ( FileNotFoundException ex ){
            FileNotFoundException exc = new FileNotFoundException("University data File not found!") ;
            System.out.println(exc.getMessage());
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        System.out.println("University data successfully loaded!");
    }

    // saves university data
    public void SaveData( String filename ) throws IOException , FileNotFoundException {
        try {
            if (!new File(filename).exists()) {
                File universityFile = new File(filename);
            }

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


}
