package com.app.project.controller;

import com.app.project.entity.Tenant;
import com.app.project.interfaces.IProperty;
import com.app.project.interfaces.IPropertyController;
import com.app.project.repository.PropertiesRepository;
import com.app.project.repository.TenantRepository;
import com.app.project.service.PropertyServices;
import com.app.project.service.TenantServices;
import com.app.project.views.TenantView;

import java.util.ArrayList;
import java.util.UUID;

public class TenantController {
    private TenantServices tenantServices;
    private TenantView view;

    public TenantController(TenantView view) {
        this.tenantServices = new TenantServices(TenantRepository.getInstance(),new PropertyServices(PropertiesRepository.getInstance()));//can be injected using IOC framework
        this.view = view;
        this.view.setController(this);
    }
    public Tenant getTenant(UUID tenantID){
        return this.tenantServices.getTenant(tenantID);
    }
    public void addTenant(Tenant tenant) {
        this.tenantServices.add(tenant);
    }

    public void addTenantView() {
        view.add();
    }
    public void addTenantToProperty(UUID propertyID, IPropertyController controller){
        UUID userid = view.addToProperty(propertyID);
        controller.moveTenants(propertyID, userid);
    }

    public void displayAllTenants() {
        ArrayList<Tenant> tenants = this.tenantServices.getAll();
        view.displayAll(tenants);
    }

}
