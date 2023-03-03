package com.app.project.repository;

import com.app.project.entity.Property;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

public class PropertiesRepository {
    private static ArrayList<Property> properties;
    private static PropertiesRepository propertiesRepository;

    private PropertiesRepository() {
        properties = new ArrayList<Property>();
    }

    public static synchronized PropertiesRepository getInstance() {
        if (propertiesRepository == null)
            propertiesRepository = new PropertiesRepository();
        return propertiesRepository;
    }

    public ArrayList<Property> getAll() {
        return this.properties;
    }

    public Property findByKey(UUID propertyId) {
        return this.properties.stream().filter(r -> r.getPropertyId().equals(propertyId)).findFirst().orElse(null);
    }

    public boolean insert(Property property) {
        try {
            properties.add(property);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public ArrayList<Property> findVacant() {
        return this.properties.stream().filter(p -> p.isVacant()).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Property> findRented() {
        return this.properties.stream().filter(p -> !p.isVacant()).collect(Collectors.toCollection(ArrayList::new));
    }

    public void upsert(Property property) {
        int index = this.properties.indexOf(property);
        if (index >= 0)
            this.properties.set(index, property);
        else
            this.properties.add(property);
    }
}
