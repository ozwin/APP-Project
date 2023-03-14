package com.app.project.repository;

import com.app.project.interfaces.IProperty;
import com.app.project.interfaces.IRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PropertiesRepository implements IRepository {
    private static PropertiesRepository propertiesRepository;
    private ArrayList<IProperty> properties = new ArrayList<>();

    private PropertiesRepository() {

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
        for (IProperty p :
                properties) {
            if (p.getPropertyId().equals(propertyId)) {
                return p;
            }
        }
        return properties.get(0);
//        return this.properties.stream().filter(r -> r.getPropertyId().equals(propertyId)).findFirst().orElse(null);
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

    public ArrayList<IProperty> findMany(List<UUID> propertyIds) {
        return this.properties.stream().filter(t -> propertyIds.contains(t.getPropertyId())).collect(Collectors.toCollection(ArrayList::new));
    }
}
