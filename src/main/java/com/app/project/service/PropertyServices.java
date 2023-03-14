package com.app.project.service;

import com.app.project.entity.Tenant;
import com.app.project.interfaces.IProperty;
import com.app.project.repository.LeaseRepository;
import com.app.project.repository.PropertiesRepository;
import com.app.project.repository.TenantRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PropertyServices {
    private PropertiesRepository propertiesRepository;
    private NotificationServices notificationServices;
    private TenantRepository tenantRepository;

    private LeaseRepository leaseRepository;

    public PropertyServices() {
        this.propertiesRepository = PropertiesRepository.getInstance();
        this.notificationServices = new NotificationServices();
        this.tenantRepository = TenantRepository.getInstance();
        this.leaseRepository = LeaseRepository.getInstance();
    }

    public PropertyServices(PropertiesRepository repository) {
        this.propertiesRepository = repository;
        this.notificationServices = new NotificationServices();
        this.tenantRepository = TenantRepository.getInstance();
        this.leaseRepository = LeaseRepository.getInstance();
    }

    public void add(IProperty property) {
        this.propertiesRepository.insert(property);
    }

    public IProperty getByKey(UUID key) {
        return this.propertiesRepository.findByKey(key);
    }

    public ArrayList<IProperty> getAll() {
        return this.propertiesRepository.getAll();
    }

    public ArrayList<IProperty> findVacant() {
        return this.propertiesRepository.findVacant();
    }

    public ArrayList<IProperty> findRented() {
        return this.propertiesRepository.findRented();
    }

    public void assignATenant(Tenant tenant) {
        IProperty property = this.propertiesRepository.findByKey(tenant.getOccupiedPropertyId());
        property.addTenantToProperty(tenant);
        this.propertiesRepository.upsert(property);
    }

    public void moveTenantToProperty(UUID propertyID, UUID userID) {
        IProperty property = this.propertiesRepository.findByKey(propertyID);
        property.moveTenant(userID);
    }

    public void removeTenants(UUID propertyID) {
        IProperty property = this.propertiesRepository.findByKey(propertyID);
        List<UUID> notifiers = property.removeTenants();
        leaseRepository.removeLease(propertyID);
        for (UUID user :
                notifiers) {
            Tenant u = this.tenantRepository.findByKey(user);
            notificationServices.sendMessage("The Apartment " + property, u.toString());
        }
    }

}
