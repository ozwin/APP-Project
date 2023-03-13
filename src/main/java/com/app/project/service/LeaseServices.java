package com.app.project.service;

import com.app.project.entity.Lease;
import com.app.project.repository.LeaseRepository;
import com.app.project.repository.PropertiesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LeaseServices {
    private LeaseRepository leaseRepository;
    private PropertiesRepository propertiesRepository;
    public LeaseServices(){
        this.leaseRepository = LeaseRepository.getInstance();
        this.propertiesRepository = PropertiesRepository.getInstance();
    }
    public List<UUID> getTenants(UUID propertyID){
        return this.propertiesRepository.findByKey(propertyID).getTenants();

    }
    public void addLease(Lease lease){
        leaseRepository.addLease(lease);
    }
    public void removeLease(UUID leaseID){
        leaseRepository.removeLease(leaseID);
    }

    public ArrayList<Lease> getAllLeases(){return this.leaseRepository.getLeases();}
}
