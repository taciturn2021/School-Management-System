package models;
// To store the address of a person

import java.io.Serializable;

public class Address implements Serializable {
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    public Address() {
        this("", "", "", "", "");
    }
    public Address(String streetAddress, String city, String state, String zipCode, String country) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

    public String toString() {
        return "Street Address: " + getStreetAddress() + "," + "Country: " + getCountry() + "," + "City: " + getCity() + "," + "State: " + getState() + "," + "Zip Code: " + getZipCode()  ;
    }

    // Getters and Setters

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
