package models;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public interface Reportable {
    public ArrayList<Course> generateReport();
    public void exportToFile() throws FileNotFoundException , IOException;
}
