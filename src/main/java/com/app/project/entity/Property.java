package com.app.project.entity;

import com.app.project.interfaces.IProperty;
import com.app.project.util.Helper;

import java.util.*;

abstract public class Property extends Observable implements IProperty {
    protected Address address;
    protected List<UUID> waitingList;

    protected List<UUID> tenants;
    private UUID ID;
    protected List<Observer> observers;

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

    public void addTenantToProperty(Tenant tenant) {
        //checking if the property is vacant or not
        this.waitingList.add(tenant.getUserID());
    }
    public List<UUID> getTenants(){return this.tenants;}

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
    public void moveTenant(UUID userID){
        // checks if the user is in the waiting list
        if (waitingList.contains(userID)){
            tenants.add(userID);
             waitingList.remove(userID);
        }else{
            tenants.add(userID);
        }
    }
    public List<UUID> removeTenants(){
        tenants.clear();
        // clear the lease
        // call a notify method on all waiting list
        return waitingList;
    }

}
