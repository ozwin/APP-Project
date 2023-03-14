package com.app.project.repository;

import com.app.project.entity.Lease;

import java.util.ArrayList;
import java.util.UUID;

public class LeaseRepository {
    private static ArrayList<Lease> leases;
    private static LeaseRepository leaseRepository;

    private LeaseRepository() {
        leases = new ArrayList<>();
    }

    public static synchronized LeaseRepository getInstance() {
        leaseRepository = new LeaseRepository();
        return leaseRepository;
    }

    public void addLease(Lease l) {
        leases.add(l);
    }

    public void removeLease(UUID prpopertyID) {
        leases.remove(findLease(prpopertyID));
    }

    public Lease findLease(UUID propertyID) {
        return leases.stream().filter(r -> r.getPropertyID().equals(propertyID)).findFirst().orElse(null);
    }

    public ArrayList<Lease> getLeases() {
        return leases;
    }

    public void upsert(Lease lease) {
        int index = this.leases.indexOf(lease);
        if (index >= 0)
            this.leases.set(index, lease);
        else
            this.leases.add(lease);
    }

}
