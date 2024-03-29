package com.app.project.entity;

import com.app.project.interfaces.IEntity;
import com.app.project.util.Helper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Describes the structure of a lease
 */
public class Lease implements IEntity<UUID> {

    private UUID ID;
    private List<UUID> occupiedTenants = new ArrayList<>();
    private UUID propertyID;
    private List<String> tenantNames = new ArrayList<>();
    private int leaseDuration;
    private double agreedMonthlyRent;
    private LocalDate signedOn;
    private ArrayList<LocalDate> rentHistory;

    public Lease(List<UUID> tenants, UUID propertyID) {
        ID = Helper.generateUniqueIdentifier();
        this.occupiedTenants.addAll(tenants);
        this.propertyID = propertyID;
        this.signedOn = LocalDate.now();
        this.rentHistory = new ArrayList<LocalDate>();
    }

    /**
     * Assign a lease duration.
     *
     * @param months
     */
    public void setLeaseDuration(int months) {
        this.leaseDuration = months;
    }

    /**
     * Assign tenants to the lease.
     *
     * @param names
     */
    public void setTenantNames(List<String> names) {
        tenantNames.addAll(names);
    }

    @Override
    public String toString() {
        return "Lease Id : " + ID + "\n" + "The tenants : " + String.join(",", tenantNames) + "\n For a duration of: " + leaseDuration+"\n Monthly rent of: "+agreedMonthlyRent+"\n Signed on: "+signedOn;
    }

    public UUID getLeaseID() {
        return ID;
    }

    /**
     * Return true if the rent is paid for this month.
     *
     * @return
     */
    public boolean isRentPaidForThisMonth() {
        if (rentHistory.size() == 0)
            return false;
        LocalDate lastPaidMonth = rentHistory.get(rentHistory.size() - 1);
        int month = LocalDate.now().getMonth().getValue();
        int year = LocalDate.now().getYear();
        LocalDate currentMonth = LocalDate.of(year, month, 1);
        if (lastPaidMonth.isAfter(currentMonth) || lastPaidMonth.isEqual(currentMonth)) {
            return true;
        }
        return false;
    }

    public void recordPayment() {
        this.rentHistory.add(LocalDate.now());
    }

    public double getAgreedMonthlyRent() {
        return this.agreedMonthlyRent;
    }

    public void setAgreedMonthlyRent(double rent) {
        this.agreedMonthlyRent = rent;
    }

    public UUID getPropertyID() {
        return propertyID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Lease lease = (Lease) obj;
        if (lease.getLeaseID().equals(this.getLeaseID()))
            return true;
        return false;
    }

    @Override
    public UUID getID() {
        return this.ID;
    }
}
