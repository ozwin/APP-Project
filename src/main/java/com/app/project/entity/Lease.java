package com.app.project.entity;

import com.app.project.util.Helper;

import java.util.List;
import java.util.UUID;

public class Lease{
    UUID leaseID;
    List<UUID> occupiedTenants;
    UUID propertyID;
    private int leaseDuration;
    public Lease(List<UUID> tenants, UUID propertyID){
        leaseID = Helper.generateUniqueIdentifier();
        this.occupiedTenants.addAll(tenants);
        this.propertyID = propertyID;
    }

    public void setLeaseDuration(int months) {
        this.leaseDuration = months;
    }

    @Override
    public String toString() {
        return "The lease is : " + leaseID  + "\n" + "The tenants : " + occupiedTenants.toString() + "For a duration of: " + leaseDuration ;
    }
    public UUID getLeaseID(){
        return leaseID;
    }
}
