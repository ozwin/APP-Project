package com.app.project.service;

import com.app.project.entity.Property;
import com.app.project.entity.Tenant;
import com.app.project.interfaces.IProperty;
import com.app.project.repository.PropertiesRepository;

import java.util.ArrayList;
import java.util.UUID;

public class PropertyServices {
    private PropertiesRepository propertiesRepository;

    public PropertyServices() {
        this.propertiesRepository = PropertiesRepository.getInstance();
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

}
