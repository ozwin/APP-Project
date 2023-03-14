package com.app.project.controller;

import com.app.project.entity.Lease;
import com.app.project.interfaces.IProperty;
import com.app.project.service.LeaseServices;
import com.app.project.views.LeaseView;
import com.app.project.views.PropertyView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LeaseController {
    private LeaseServices leaseServices;
    private LeaseView leaseView;
    private PropertyView propertyView;

    public LeaseController(LeaseView view) {
        this.leaseView = view;
        this.leaseServices = new LeaseServices();
        leaseView.setController(this);
    }

    public void addLease(Lease lease) {
        this.leaseServices.addLease(lease);
    }

    public void addLeaseView(UUID uuid) {
        List<UUID> tenants = leaseServices.getTenants(uuid);
        leaseView.add(uuid, tenants, leaseServices.getTenantNames(uuid));
    }

    public void displayAllLeases() {
        ArrayList<Lease> leases = this.leaseServices.getAllLeases();
        for (Lease lease :
                leases) {
            System.out.println(lease);
        }
    }

    public void recordPayment(UUID propertyID, double rent) {
        try {
            this.leaseServices.recordPayment(propertyID, rent);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void findPropertiesWithPendingPayment() {
        ArrayList<IProperty> properties = leaseServices.getPropertiesWithPendingRent();
        propertyView.displayProperties(properties);
    }


}
