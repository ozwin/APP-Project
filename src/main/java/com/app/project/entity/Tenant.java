package com.app.project.entity;

import com.app.project.service.NotificationServices;

import java.util.UUID;

/**
 * Describes the behaviour of a Tenant
 */
public class Tenant extends User {
    UUID occupiedPropertyId;
    private NotificationServices notificationServices = new NotificationServices();

    public Tenant(String firstName, String lastName, Contact contact, UUID occupiedPropertyId) {
        super(firstName, lastName, contact);
        this.occupiedPropertyId = occupiedPropertyId;

    }

    public UUID getOccupiedPropertyId() {
        return occupiedPropertyId;
    }

    public void setOccupiedPropertyId(UUID occupiedPropertyId) {
        this.occupiedPropertyId = occupiedPropertyId;
    }

    @Override
    public String toString() {
        return String.format("Property Interested/Occupied : %s \n %s", getOccupiedPropertyId().toString(), super.toString());
    }
}
