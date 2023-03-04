package com.app.project.entity;

import com.app.project.interfaces.IProperty;
import com.app.project.util.Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

abstract public class Property implements IProperty {
    protected Address address;
    protected List<ProspectiveTenant> waitingList;
    protected List<UUID> tenants;
    private UUID ID;

    public Property() {
        this.ID = Helper.generateUniqueIdentifier();
        this.waitingList = new ArrayList<ProspectiveTenant>();
        this.tenants = new ArrayList<UUID>();
    }

    public Property(Address address) {
        this.ID = Helper.generateUniqueIdentifier();
        this.address = address;
        this.waitingList = new ArrayList<ProspectiveTenant>();
        this.tenants = new ArrayList<UUID>();
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
        this.tenants.add(tenant.getUserID());
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

}
