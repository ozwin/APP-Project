package com.app.project.entity;

import com.app.project.util.Helper;

import java.util.UUID;

public class User {
    protected String firstName;
    protected String lastName;
    protected Contact contact;
    private UUID ID;

    public User() {
        this.ID = Helper.generateUniqueIdentifier();
    }

    public User(String firstName, String lastName, Contact contact) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contact = contact;
        this.ID = Helper.generateUniqueIdentifier();
    }

    public String fullName() {
        return this.firstName + " " + this.lastName;
    }

    public UUID getUserID() {
        return this.ID;
    }

    public String toString() {
        return String.format("User Id: %s\n Full Name: %s \n Contact Details: %s\n", this.getUserID().toString(), this.fullName(), this.contact.toString());
    }

}
