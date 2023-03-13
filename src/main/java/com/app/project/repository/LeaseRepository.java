package com.app.project.repository;

import com.app.project.entity.Lease;
import com.app.project.interfaces.IRepository;

import java.util.ArrayList;
import java.util.UUID;

public class LeaseRepository  implements IRepository {
    private static ArrayList<Lease> leases;
    private static LeaseRepository leaseRepository;

    public static synchronized LeaseRepository getInstance() {
        if (leaseRepository == null)
            leaseRepository = new LeaseRepository();
        return leaseRepository;
    }

    private LeaseRepository(){
        leases = new ArrayList<>();
    }

    public void add(Lease lease){
        leases.add(lease);
    }

    public void remove(UUID propertyID){
        leases.remove(findByKey(propertyID));
    }

    public Lease findByKey(UUID propertyID){
        return leases.stream().filter(r -> r.getPropertyID().equals(propertyID)).findFirst().orElse(null);
    }
    public ArrayList<Lease> getLeases(){return leases;}
}
