package com.app.project.controller;

import com.app.project.entity.Lease;
import com.app.project.interfaces.IProperty;
import com.app.project.service.LeaseServices;
import com.app.project.views.LeaseView;
import com.app.project.views.PropertyView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * This class consists implementation of Lease generation, removal and access.
 */
public class LeaseController {
    private LeaseServices leaseServices;
    private LeaseView leaseView;
    private PropertyView propertyView;

    /**
     * Constructor for Lease.
     * @param view
     */
    public LeaseController(LeaseView view) {
        this.leaseView = view;
        this.leaseServices = new LeaseServices();
        leaseView.setController(this);
    }

    /**
     * Adds a lease into the system.
     * @param lease
     */
    public void addLease(Lease lease) {
        this.leaseServices.addLease(lease);
    }

    /**
     * Adds leases for a given tenant ID.
     * @param uuid
     */
    public void addLeaseView(UUID uuid) {
        List<UUID> tenants = leaseServices.getTenants(uuid);
        leaseView.add(uuid, tenants, leaseServices.getTenantNames(uuid));
    }

    /**
     * This method displaus all leases.
     */
    public void displayAllLeases() {
        ArrayList<Lease> leases = this.leaseServices.getAllLeases();
        for (Lease lease :
                leases) {
            System.out.println(lease);
        }
    }

    /**
     * This method records the payment for given property
     * @param propertyID
     * @param rent
     */
    public void recordPayment(UUID propertyID, double rent) {
        try {
            this.leaseServices.recordPayment(propertyID, rent);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Method for finding the properties with Pending Payments
     */
    public void findPropertiesWithPendingPayment() {
        ArrayList<IProperty> properties = leaseServices.getPropertiesWithPendingRent();
        propertyView.displayProperties(properties);
    }


}
