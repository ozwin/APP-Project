package com.app.project.entity;

/**
 * Describes the behaviour of an Private House.
 */
public class PrivateHouse extends Property {
    String streetNumber;

    public PrivateHouse(String streetNumber, Address address) {
        super(address);
        this.streetNumber = streetNumber;
    }

    public PrivateHouse(Address address) {
        super(address);
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }
}

