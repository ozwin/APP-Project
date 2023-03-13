package com.app.project.datasource;

import com.app.project.entity.*;
import com.app.project.interfaces.IProperty;
import com.app.project.repository.PropertiesRepository;
import com.app.project.repository.TenantRepository;
import com.app.project.service.PropertyServices;
import com.app.project.service.TenantServices;

import java.util.ArrayList;

public class DataStore {
    public static final PropertiesRepository propertiesRepository = PropertiesRepository.getInstance();
    public static final TenantServices tenantServices = new TenantServices(TenantRepository.getInstance(),new PropertyServices(PropertiesRepository.getInstance()));


    public static void initializeDataBase() {
        propertiesRepository.insert(new Apartment(2, 2, 2000, new Address("Rented", "Montreal", "H3H1K4")));
        propertiesRepository.insert(new Condo("3rd Main", "23", new Address("Rented", "Montreal", "H3H1K6")));
        propertiesRepository.insert(new PrivateHouse("5th Main", new Address("vacant", "Montreal", "H3H1K4")));
        propertiesRepository.insert(new Apartment(3, 4, 3000, new Address("vacant", "Ottawa", "H3H1K4")));
        ArrayList<IProperty> properties = propertiesRepository.findVacant();
        tenantServices.add(new Tenant("Ozwin", "Lobo", new Contact("xyz@gmail.com", "990909093"), properties.get(0).getPropertyId()));
        tenantServices.add(new Tenant("Jack", "Sparrow", new Contact("yy@gmail.com", "670909093"), properties.get(1).getPropertyId()));
    }


}
