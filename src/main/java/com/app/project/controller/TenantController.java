package com.app.project.controller;

import com.app.project.entity.Tenant;
import com.app.project.repository.PropertiesRepository;
import com.app.project.repository.TenantRepository;
import com.app.project.service.PropertyServices;
import com.app.project.service.TenantServices;
import com.app.project.views.TenantView;

import java.util.ArrayList;
import java.util.UUID;

/**
 * This class consists of implementations for Tenant.
 */
public class TenantController {
    private TenantServices tenantServices;
    private TenantView view;

    public TenantController(TenantView view) {
        this.tenantServices = new TenantServices(TenantRepository.getInstance(), new PropertyServices(PropertiesRepository.getInstance()));//can be injected using IOC framework
        this.view = view;
        this.view.setController(this);
    }

    /**
     * Fina a tenant by his ID.
     *
     * @param tenantID
     * @return
     */
    public Tenant getTenant(UUID tenantID) {
        return this.tenantServices.getTenant(tenantID);
    }

    /**
     * Add a tenant into the system.
     *
     * @param tenant
     */
    public void addTenant(Tenant tenant) {
        this.tenantServices.add(tenant);
    }

    /**
     * Calls the view to add the tenant.
     */
    public void addTenantView() {
        view.add();
    }

    /**
     * Add a tenant to a property.
     *
     * @param propertyID
     */
    public void addTenantToProperty(UUID propertyID) {
        view.addToProperty(propertyID);
    }

    /**
     * Add and rent a unit.
     *
     * @param tenant
     */
    public void addAndRent(Tenant tenant) {
        try {
            tenantServices.addAndRent(tenant);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Display all the tenants.
     */
    public void displayAllTenants() {
        ArrayList<Tenant> tenants = this.tenantServices.getAll();
        view.displayAll(tenants);
    }

}
