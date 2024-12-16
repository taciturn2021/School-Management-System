package models;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

// Generic Inheritable Person class

public class Person implements Serializable {
    private String name;
    private String email;
    private Date dateOfBirth;
    private Address address;

    Person(){
        this("", "", new Date(), new Address("", "", "", "", ""));
    }

    Person(String name, String email, Date dateOfBirth, Address address){
        this.name = name;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }
    // Functionality

    public void generateReport( List<Person> people ){
        int teacherCount = 0 ;
        int studentCount = 0 ;
        int coursesCount = 0 ;

        for ( Person p : people ) {

            if (people instanceof Teacher) {
                teacherCount++;
            }

            if (people instanceof Student) {
                studentCount++;
            }

            if (people instanceof Course) {
                coursesCount++;
            }

        }

        System.out.println("Total Teachers: " + teacherCount);
        System.out.println("Total Students: " + studentCount);
        System.out.println("Total Courses: " + coursesCount);

    }

    public void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Address: " + address);
    }

    public String toString(){
        return "Name: " + getName() + "," + "Email: " + getEmail() + "," + "Date of Birth: " + getDateOfBirth() + "," + getAddress().toString();
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
