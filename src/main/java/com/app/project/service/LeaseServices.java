package com.app.project.service;

import com.app.project.entity.Lease;
import com.app.project.entity.Tenant;
import com.app.project.interfaces.IRepository;
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
    public LeaseServices(IRepository repository){
        this.leaseRepository = (LeaseRepository)repository;
        this.propertiesRepository = PropertiesRepository.getInstance();
        this.tenantRepository = TenantRepository.getInstance();
    }
    public List<UUID> getTenants(UUID propertyID){
        return this.propertiesRepository.findByKey(propertyID).getTenants();
    }
    public List<String> getTenantNames(UUID propertyID){
        List<UUID> propertyTenants = this.propertiesRepository.findByKey(propertyID).getTenants();
        return  this.tenantRepository.findMany(propertyTenants).stream().map(x->x.fullName()).collect(Collectors.toCollection(ArrayList::new));
    }
    public void addLease(Lease lease){
        this.leaseRepository.add(lease);
    }
    public void removeLease(UUID leaseID){
        this.leaseRepository.remove(leaseID);
    }

    public ArrayList<Lease> getAllLeases(){return this.leaseRepository.getLeases();}
}
