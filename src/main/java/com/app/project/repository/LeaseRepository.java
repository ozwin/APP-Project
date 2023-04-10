package com.app.project.repository;

import com.app.project.entity.Lease;

import java.util.UUID;

/**
 * The repository of the Leases where all the leases are stored.
 */
public class LeaseRepository extends Repository<Lease, UUID> {
    private static LeaseRepository leaseRepository;

    private LeaseRepository() {
        super();
    }

    public static synchronized LeaseRepository getInstance() {
        leaseRepository = new LeaseRepository();
        return leaseRepository;
    }

    /**
     * Removes a lease
     *
     * @param propertyID of the property with which lease is assosiated
     */
    public void removeLeaseByPropertyID(UUID propertyID) {
        delete(findLease(propertyID));
    }

    /**
     * Finds a lease
     *
     * @param propertyID
     * @return
     */
    public Lease findLease(UUID propertyID) {
        return datastore.stream().filter(r -> r.getPropertyID().equals(propertyID)).findFirst().orElse(null);
    }


}
