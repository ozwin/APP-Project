package com.app.project.service;

import com.app.project.entity.Tenant;
import com.app.project.repository.TenantRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TenantServices {
    private TenantRepository tenantRepository;
    private PropertyServices propertyServices;

    public TenantServices() {
        this.tenantRepository = TenantRepository.getInstance();
        this.propertyServices=new PropertyServices();
    }

    public List<Tenant> findMany(List<UUID> userIds) {
        return this.tenantRepository.findMany(userIds);
    }

    public void add(Tenant tenant) {
        this.propertyServices.assignATenant(tenant);
        this.tenantRepository.add(tenant);
    }

    public ArrayList<Tenant> getAll() {
        return this.tenantRepository.getAll();
    }
}
