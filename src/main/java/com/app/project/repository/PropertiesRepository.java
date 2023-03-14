package com.app.project.repository;

import com.app.project.interfaces.IProperty;
import com.app.project.interfaces.IRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * The repository where are the properties are stored.
 */
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

    /**
     * Get all the properties from database.
     * @return
     */
    public ArrayList<IProperty> getAll() {
        return this.properties;
    }

    /**
     * Find the property by property ID.
     * @param propertyId
     * @return
     */
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

    /**
     * insert a property into the database.
     * @param property
     * @return
     */
    public boolean insert(IProperty property) {
        try {
            properties.add(property);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    /**
     * Find vacant units.
     * @return
     */
    public ArrayList<IProperty> findVacant() {
        return this.properties.stream().filter(p -> p.isVacant()).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Find rented units.
     * @return
     */
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
