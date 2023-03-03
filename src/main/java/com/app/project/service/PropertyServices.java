package com.app.project.service;

import com.app.project.entity.Property;
import com.app.project.entity.Tenant;
import com.app.project.repository.PropertiesRepository;

import java.util.ArrayList;
import java.util.UUID;

public class PropertyServices {
    private PropertiesRepository propertiesRepository;

    public PropertyServices() {
        this.propertiesRepository = PropertiesRepository.getInstance();
    }

    public void add(Property property) {
        this.propertiesRepository.insert(property);
    }

    public Property getByKey(UUID key) {
        return this.propertiesRepository.findByKey(key);
    }

    public ArrayList<Property> getAll() {
        return this.propertiesRepository.getAll();
    }
    public ArrayList<Property> findVacant() {
        return this.propertiesRepository.findVacant();
    }
    public ArrayList<Property> findRented() {
        return this.propertiesRepository.findRented();
    }
    public void assignATenant(Tenant tenant){
        Property property=this.propertiesRepository.findByKey(tenant.getOccupiedPropertyId());
        property.addTenantToProperty(tenant);
        this.propertiesRepository.upsert(property);
    }

}
