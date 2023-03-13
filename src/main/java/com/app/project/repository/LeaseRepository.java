package com.app.project.repository;

import com.app.project.entity.Lease;

import java.util.ArrayList;
import java.util.UUID;

public class LeaseRepository {
    private static ArrayList<Lease> leases;
    private static LeaseRepository leaseRepository;

    public static LeaseRepository getInstance() {
        return leaseRepository;
    }

    private LeaseRepository(){
        leases = new ArrayList<>();
    }

    public void addLease(Lease l){
        leases.add(l);
    }

    public void removeLease(UUID leaseID){
        leases.remove(findLease(leaseID));
    }

    public Lease findLease(UUID leaseID){
        return leases.stream().filter(r -> r.getLeaseID().equals(leaseID)).findFirst().orElse(null);
    }

    public ArrayList<Lease> getLeases(){return leases;}
}
