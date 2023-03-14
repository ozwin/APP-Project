package com.app.project.entity;

public class Apartment extends Property {
    protected int numberOfBedrooms;
    protected int numberOfBathrooms;
    protected float squareFoot;

    public Apartment(int numberOfBathrooms, int numberOfBedrooms, float squareFoot, Address address) {
        super(address);
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.squareFoot = squareFoot;
    }

    public Apartment(Address address) {
        super(address);
    }

    public Apartment() {

    }

    public int getNumberOfBedrooms() {
        return this.numberOfBedrooms;
    }

    public void setNumberOfBedrooms(int numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public int getNumberOfBathrooms() {
        return this.numberOfBathrooms;
    }

    public void setNumberOfBathrooms(int numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public float getSquareFoot() {
        return this.squareFoot;
    }

    public void setSquareFoot(float squareFoot) {
        this.squareFoot = squareFoot;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Property ID:" + getPropertyId() + "\n");
        builder.append(address.toString());
        builder.append("Number of Bedrooms: " + getNumberOfBedrooms() + "\n");
        builder.append("Number of Bathrooms: " + getNumberOfBathrooms() + "\n");
        builder.append("Square foot area: " + getSquareFoot() + "\n");
        return builder.toString();
    }

}
