package com.app.project.entity;

import com.app.project.interfaces.IProperty;
import com.app.project.util.Helper;

import java.util.*;

/**
 * Describes the behaviour of a property.
 */
abstract public class Property extends Observable implements IProperty {
    protected Address address;
    protected List<UUID> waitingList;

    protected List<UUID> tenants;
    protected List<Observer> observers;
    private UUID ID;
    private UUID leaseId;

    public Property() {
        this.ID = Helper.generateUniqueIdentifier();
        this.waitingList = new ArrayList<>();
        this.tenants = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public Property(Address address) {
        this.ID = Helper.generateUniqueIdentifier();
        this.address = address;
        this.waitingList = new ArrayList<>();
        this.tenants = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public UUID getPropertyId() {
        return this.ID;
    }

    public Address getAddress() {
        return this.address;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Property ID:" + getPropertyId() + "\n");
        builder.append(address.toString());
        return builder.toString();
    }

    /**
     * Adds a tenant to the property.
     * @param tenant
     */
    public void addTenantToProperty(Tenant tenant) {
        this.waitingList.add(tenant.getUserID());
    }

    public List<UUID> getTenants() {
        return this.tenants;
    }

    public boolean isVacant() {
        return this.tenants.isEmpty();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Property property = (Property) obj;
        if (property.getPropertyId().equals(this.getPropertyId()))
            return true;
        return false;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this, null);
        }
    }
    public void addTenant(Tenant tenant){
        this.tenants.add(tenant.getUserID());
    }

    /**
     * Checks if the tenant is already in the waitlist, if yes remove from it and make him tenant.
     * @param userID
     */
    public void moveTenant(UUID userID) {
        if (waitingList.contains(userID)) {
            tenants.add(userID);
            waitingList.remove(userID);
        } else {
            tenants.add(userID);
        }
    }

    public void removeTenants() {
        tenants.clear();
    }

    public List<UUID> getWaitingList() {
        return waitingList;
    }

    public UUID getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(UUID leaseId) {
        this.leaseId = leaseId;
    }
}
