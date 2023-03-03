package com.app.project.entity;

import java.util.UUID;

public class Tenant extends User {
    UUID occupiedPropertyId; //is it possible to occupy multiple properties?

    public Tenant(String firstName, String lastName, Contact contact, UUID occupiedPropertyId) {
        super(firstName, lastName, contact);
        this.occupiedPropertyId=occupiedPropertyId;
    }

    public UUID getOccupiedPropertyId() {
        return occupiedPropertyId;
    }
    @Override
    public String toString(){
       return String.format("Occupied Property Id: %s \n %s",getOccupiedPropertyId().toString(),super.toString());
    }
}
