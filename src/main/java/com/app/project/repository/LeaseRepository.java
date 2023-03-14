package com.app.project.repository;

import com.app.project.entity.Lease;
import com.app.project.interfaces.IRepository;

import java.util.ArrayList;
import java.util.UUID;

/**
 * The repository of the Leases where all the leases are stored.
 */
public class LeaseRepository implements IRepository  {
    private static ArrayList<Lease> leases;
    private static LeaseRepository leaseRepository;

    private LeaseRepository() {
        leases = new ArrayList<>();
    }

    public static synchronized LeaseRepository getInstance() {
        leaseRepository = new LeaseRepository();
        return leaseRepository;
    }

    /**
     * Adds a lease
     * @param l
     */
    public void addLease(Lease l) {
        leases.add(l);
    }

    /**
     * Removes a lease
     * @param prpopertyID
     */
    public void removeLease(UUID prpopertyID) {
        leases.remove(findLease(prpopertyID));
    }

    /**
     * Finds a lease
     * @param propertyID
     * @return
     */
    public Lease findLease(UUID propertyID) {
        return leases.stream().filter(r -> r.getPropertyID().equals(propertyID)).findFirst().orElse(null);
    }

    /**
     * Returns the leases in form of a list.
     * @return
     */
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
