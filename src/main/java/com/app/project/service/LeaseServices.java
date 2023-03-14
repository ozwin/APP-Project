package com.app.project.service;

import com.app.project.entity.Lease;
import com.app.project.entity.Tenant;
import com.app.project.repository.LeaseRepository;
import com.app.project.repository.PropertiesRepository;
import com.app.project.repository.TenantRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LeaseServices {
    private LeaseRepository leaseRepository;
    private PropertiesRepository propertiesRepository;

    private TenantRepository tenantRepository;

    public LeaseServices() {
        this.leaseRepository = LeaseRepository.getInstance();
        this.propertiesRepository = PropertiesRepository.getInstance();
        this.tenantRepository = TenantRepository.getInstance();
    }

    public List<UUID> getTenants(UUID propertyID) {
        return this.propertiesRepository.findByKey(propertyID).getTenants();
    }

    public List<String> getTenantNames(UUID propertyID) {
        List<UUID> propertyTenants = this.propertiesRepository.findByKey(propertyID).getTenants();
        List<String> occupants = new ArrayList<>();
        for (UUID tenant :
                propertyTenants) {
            Tenant t = this.tenantRepository.findByKey(tenant);
            occupants.add(t.fullName());
        }
        return occupants;
    }

    public void addLease(Lease lease) {
        leaseRepository.addLease(lease);
    }

    public void removeLease(UUID leaseID) {
        leaseRepository.removeLease(leaseID);
    }

    public ArrayList<Lease> getAllLeases() {
        return this.leaseRepository.getLeases();
    }
}
