package com.app.project.repository;

import com.app.project.entity.Property;
import com.app.project.interfaces.IProperty;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

public class PropertiesRepository {
    private static ArrayList<IProperty> properties;
    private static PropertiesRepository propertiesRepository;

    private PropertiesRepository() {
        properties = new ArrayList<IProperty>();
    }

    public static synchronized PropertiesRepository getInstance() {
        if (propertiesRepository == null)
            propertiesRepository = new PropertiesRepository();
        return propertiesRepository;
    }

    public ArrayList<IProperty> getAll() {
        return this.properties;
    }

    public IProperty findByKey(UUID propertyId) {
        return this.properties.stream().filter(r -> r.getPropertyId().equals(propertyId)).findFirst().orElse(null);
    }

    public boolean insert(IProperty property) {
        try {
            properties.add(property);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public ArrayList<IProperty> findVacant() {
        return this.properties.stream().filter(p -> p.isVacant()).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<IProperty> findRented() {
        return this.properties.stream().filter(p -> !p.isVacant()).collect(Collectors.toCollection(ArrayList::new));
    }

    public void upsert(IProperty property) {
        int index = this.properties.indexOf(property);
        if (index >= 0)
            this.properties.set(index, property);
        else
            this.properties.add(property);
    }
}
