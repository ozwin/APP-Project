package com.app.project.repository;

import com.app.project.entity.Tenant;
import com.app.project.interfaces.IRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class TenantRepository implements IRepository {
    private static ArrayList<Tenant> tenants;
    private static TenantRepository tenantRepository;

    private TenantRepository() {
        tenants = new ArrayList<Tenant>();
    }

    public static synchronized TenantRepository getInstance() {
        if (tenantRepository == null)
            tenantRepository = new TenantRepository();
        return tenantRepository;
    }

    public void add(Tenant tenant) {
        this.tenants.add(tenant);
    }

    public ArrayList<Tenant> getAll() {
        return this.tenants;
    }

    public Tenant findByKey(UUID userID) {
        return this.tenants.stream().filter(t -> t.getUserID().equals(userID)).findFirst().orElse(null);
    }

    public List<Tenant> findMany(List<UUID> userIDs) {
        return this.tenants.stream().filter(t -> userIDs.contains(t.getUserID())).collect(Collectors.toCollection(ArrayList::new));
    }

}
