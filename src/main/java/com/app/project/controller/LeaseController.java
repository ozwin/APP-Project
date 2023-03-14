package com.app.project.controller;

import com.app.project.entity.Lease;
import com.app.project.service.LeaseServices;
import com.app.project.views.LeaseView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LeaseController {
    private LeaseServices leaseServices;
    private LeaseView leaseView;

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
}
