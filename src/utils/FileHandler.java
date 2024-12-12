package utils;

import java.io.*;
import java.util.Scanner;

import models.*;

public class FileHandler implements Serializable{

    University university = new University() ;

    // Loads data university data
    public void loadData(String filename ) throws IOException {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream( new FileInputStream( "University_data.dat" )) ;

            while ( new Scanner( objectInputStream ).hasNext() ){

                Object object = objectInputStream.readObject() ;

                if ( object instanceof Teacher){
                    university.teacherRepository.add((Teacher) object);
                }
                if ( object instanceof Student){
                    university.studentRepository.add((Student) object);
                }
                if ( object instanceof Course){
                    university.administrativeStaffRepository.add((AdministrativeStaff) object);
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
    public void SaveData( String filename ) throws IOException {
        try {
            if (!new File(filename).exists()) {
                File universityFile = new File(filename);
                System.out.println("No data to be loaded for now");
                return;
            }

            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename));

        }
        catch (FileNotFoundException ex){
            FileNotFoundException exc = new FileNotFoundException("University data File not found!") ;
            System.out.println(exc.getMessage());
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }


}
