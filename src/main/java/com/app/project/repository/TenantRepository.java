package com.app.project.repository;

import com.app.project.entity.Tenant;
import com.app.project.interfaces.IRepository;
import com.app.project.service.NotificationServices;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * The repository to store Tenants in the database.
 */
public class TenantRepository extends Repository {
    private static final NotificationServices notificationServices = new NotificationServices();
    private static ArrayList<Tenant> tenants;
    private static TenantRepository tenantRepository;

    public TenantRepository() {
        tenants = new ArrayList<>();
    }

    public static synchronized TenantRepository getInstance() {
        if (tenantRepository == null)
            tenantRepository = new TenantRepository();
        return tenantRepository;
    }

    /**
     * Add a tenant.
     * @param tenant
     */
    public void add(Tenant tenant) {
        this.tenants.add(tenant);
    }

    /**
     * Get all the tenants.
     * @return
     */
    public ArrayList<Tenant> getAll() {
        return this.tenants;
    }

    /**
     * Find the tenant by the ID.
     * @param userID
     * @return
     */
    public Tenant findByKey(UUID userID) {
        return tenants.stream().filter(t -> t.getUserID().equals(userID)).findFirst().orElse(null);
    }

    public List<Tenant> findMany(List<UUID> userIDs) {
        return this.tenants.stream().filter(t -> userIDs.contains(t.getUserID())).collect(Collectors.toCollection(ArrayList::new));
    }


}
