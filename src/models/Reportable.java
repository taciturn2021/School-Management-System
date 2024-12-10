package models;

public interface Reportable {
    public String generateReport();
    public void exportToFile(String filename);
}
