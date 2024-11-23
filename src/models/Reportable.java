package models;

public interface Reportable {
    public String getReport();
    public void exportToFile(String filename);
}
