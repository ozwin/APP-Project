package com.app.project.service;

import com.app.project.entity.Tenant;
import com.app.project.interfaces.IRepository;
import com.app.project.repository.TenantRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * This class all the services performed to tenant leases.
 */
public class TenantServices {
    private TenantRepository tenantRepository;
    private PropertyServices propertyServices;
    private NotificationServices notificationServices;


    public TenantServices(IRepository repository, PropertyServices propertyServices) {
        this.tenantRepository = (TenantRepository) repository;
        this.propertyServices = propertyServices;
        this.notificationServices = new NotificationServices();
    }

    public TenantServices(PropertyServices propertyServices) {
        this.tenantRepository = TenantRepository.getInstance();
        this.propertyServices = propertyServices;
    }

    public List<Tenant> findMany(List<UUID> userIds) {
        return this.tenantRepository.findMany(userIds);
    }

    public Tenant getTenant(UUID userID) {
        return this.tenantRepository.findByKey(userID);
    }

    public void addTenant(Tenant tenant) {
        // here we are assigning tenant a property and registering them into the database.
        this.propertyServices.assignATenant(tenant);
        this.tenantRepository.insert(tenant);
    }

    public void addAndRent(Tenant tenant) throws Exception {
        addTenant(tenant);
        this.propertyServices.moveTenantToProperty(tenant.getOccupiedPropertyId(), tenant.getUserID());
    }

    public Tenant get(UUID tenantId) {
        return tenantRepository.findByKey(tenantId);
    }

    public ArrayList<Tenant> getAll() {
        return this.tenantRepository.getAll();
    }
}
