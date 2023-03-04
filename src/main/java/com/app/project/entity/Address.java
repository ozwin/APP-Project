package com.app.project.entity;

public class Address {
    String streetName;
    String city;
    String postalCode;

    /***
     * Constructor to initalize an Address object
     * @param streetName String representing street name
     * @param city String representing city
     * @param postalCode String representing postal code
     */
    public Address(String streetName, String city, String postalCode) {
        this.streetName = streetName;
        this.city = city;
        this.postalCode = postalCode;
    }

    public String getStreetName() {
        return this.streetName;
    }

    public String getCity() {
        return this.city;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public String toString() {
        return "Street Name: " + this.getStreetName() + "\nCity: " + this.getCity() + "\nPostal Code " + this.getPostalCode();
    }


}
