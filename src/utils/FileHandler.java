package utils;

import java.io.*;

public class FileHandler {

    // Loads data university data
    public void loadData( String filename ){

        File enrolledDetails = new File( filename );
        if ( !enrolledDetails.exists() ) {
            enrolledDetails.createNewFile() ;
        }

        BufferedReader enrolledDetailsReader = new BufferedReader( new FileReader(filename) ) ;

        while(enrolledDetailsReader.ready()){
            System.out.println(enrolledDetailsReader.readLine());
        }

        System.out.println("Data Successfully loaded from" + filename );

    }

    // saves university data
    public void SaveData( String filename ){

        File enrolledDetails = new File( filename );
        if ( !enrolledDetails.exists() ) {
            enrolledDetails.createNewFile() ;
        }

        BufferedWriter enrolledDetailsWriter = new BufferedWriter( new FileWriter(filename) ) ;

        enrolledDetailsWriter.write() ;
        enrolledDetailsWriter.newLine();
    }


}
