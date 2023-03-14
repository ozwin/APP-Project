package com.app.project.service;

import com.app.project.entity.Lease;
import com.app.project.entity.Property;
import com.app.project.entity.Tenant;
import com.app.project.interfaces.IProperty;
import com.app.project.repository.LeaseRepository;
import com.app.project.repository.PropertiesRepository;
import com.app.project.repository.TenantRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        Property property = (Property) propertiesRepository.findByKey(lease.getPropertyID());
        property.setLeaseId(lease.getLeaseID());
        propertiesRepository.upsert(property);
        leaseRepository.addLease(lease);
    }

    public void removeLease(UUID leaseID) {
        leaseRepository.removeLease(leaseID);
    }

    public ArrayList<Lease> getAllLeases() {
        return this.leaseRepository.getLeases();
    }

    public void recordPayment(UUID propertyId, double rent) throws Exception {
        Property property = (Property) propertiesRepository.findByKey(propertyId);
        Lease lease = leaseRepository.findLease(property.getLeaseId());
        if (rent < lease.getAgreedMonthlyRent()) {
            throw new Exception("Amount is less than the agreed sum, please pay:" + lease.getAgreedMonthlyRent());
        }
        lease.recordPayment();
        leaseRepository.upsert(lease);
    }

    public ArrayList<IProperty> getPropertiesWithPendingRent() {
        List<UUID> propertyIds = getAllLeases().stream().filter(x -> !x.isRentPaidForThisMonth()).map(x -> x.getPropertyID()).collect(Collectors.toCollection(ArrayList::new));
        return propertiesRepository.findMany(propertyIds);
    }
}
