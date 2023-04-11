package com.app.project.repository;

import com.app.project.interfaces.IProperty;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * The repository where are the properties are stored.
 */
public class PropertiesRepository extends Repository<IProperty, UUID> {
    private static PropertiesRepository propertiesRepository;

    private PropertiesRepository() {
        super();
    }

    public static synchronized PropertiesRepository getInstance() {
        if (propertiesRepository == null)
            propertiesRepository = new PropertiesRepository();
        return propertiesRepository;
    }

    /**
     * Find vacant units.
     *
     * @return
     */
    public ArrayList<IProperty> findVacant() {
        return this.datastore.stream().filter(p -> p.isVacant()).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Find rented units.
     *
     * @return
     */
    public ArrayList<IProperty> findRented() {
        return this.datastore.stream().filter(p -> !p.isVacant()).collect(Collectors.toCollection(ArrayList::new));
    }

}
