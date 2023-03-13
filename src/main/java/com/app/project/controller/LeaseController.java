package com.app.project.controller;

import com.app.project.entity.Lease;
import com.app.project.repository.LeaseRepository;
import com.app.project.repository.PropertiesRepository;
import com.app.project.repository.TenantRepository;
import com.app.project.service.LeaseServices;
import com.app.project.service.PropertyServices;
import com.app.project.service.TenantServices;
import com.app.project.views.LeaseView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LeaseController {
    private LeaseServices leaseServices;
    private LeaseView view;

    public LeaseController(LeaseView view){
        this.leaseServices = new LeaseServices(LeaseRepository.getInstance());
        this.view = view;
        this.view.setController(this);
    }
    private void setLeaseServices(LeaseServices services){
        this.leaseServices = services;
    }

    public void addLease(Lease lease){
        this.leaseServices.addLease(lease);
    }
    public void addLeaseView(UUID uuid){
        List<UUID> tenants = leaseServices.getTenants(uuid);
        view.add(uuid, tenants, leaseServices.getTenantNames(uuid));
    }
    public void displayAllLeases(){
        ArrayList<Lease> leases = this.leaseServices.getAllLeases();
        for (Lease lease:
             leases) {
            System.out.println(lease);
        }
    }
}
