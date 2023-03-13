package com.app.project.entity;

import com.app.project.util.Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Lease{
    UUID leaseID;
    List<UUID> occupiedTenants = new ArrayList<>();
    UUID propertyID;
    private int leaseDuration;

    List<String> tenantNames = new ArrayList<>();
    public Lease(List<UUID> tenants, UUID propertyID){
        leaseID = Helper.generateUniqueIdentifier();
        this.occupiedTenants.addAll(tenants);
        this.propertyID = propertyID;
    }

    public void setLeaseDuration(int months) {
        this.leaseDuration = months;
    }

    public void setTenantNames(List<String> names){
        tenantNames.addAll(names);
    }
    @Override
    public String toString() {
        return "The lease is : " + leaseID  + "\n" + "The tenants : " + String.join(",", tenantNames) + "For a duration of: " + leaseDuration ;
    }
    public UUID getLeaseID(){
        return leaseID;
    }

    public UUID getPropertyID(){return propertyID; }
}
