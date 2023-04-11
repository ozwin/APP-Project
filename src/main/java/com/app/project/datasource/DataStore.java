package com.app.project.datasource;

import com.app.project.entity.*;
import com.app.project.repository.PropertiesRepository;
import com.app.project.repository.TenantRepository;
import com.app.project.service.LeaseServices;
import com.app.project.service.PropertyServices;
import com.app.project.service.TenantServices;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Basic class for intialisation of the Data.
 */
public class DataStore {
    public static final PropertiesRepository propertiesRepository = PropertiesRepository.getInstance();
    public static final TenantServices tenantServices = new TenantServices(TenantRepository.getInstance(), new PropertyServices(PropertiesRepository.getInstance()));
    public static  final LeaseServices leaseServices=new LeaseServices();
    public static void initializeDataBase() {
        try {
            propertiesRepository.insert(new Apartment(2, 2, 2000, new Address("St Cathrine", "Montreal", "H3H1K4")));
            propertiesRepository.insert(new Condo("3rd Main", "23", new Address("St Victoria", "Montreal", "H3H1K6")));
            propertiesRepository.insert(new Condo("4th Main", "3", new Address("St Vincent", "Montreal", "H3H1K6")));

            //        propertiesRepository.insert(new PrivateHouse("5th Main", new Address("Mount Royal", "Montreal", "H3H1K4")));
//        propertiesRepository.insert(new Apartment(3, 4, 3000, new Address("Something", "Ottawa", "H3H1K4")));
//        ArrayList<IProperty> properties = propertiesRepository.findVacant();
            UUID propertyId1=propertiesRepository.findVacant().get(0).getID();
            Tenant tenant1=new Tenant("Ozwin", "Lobo", new Contact("xyz@gmail.com", "990909093"), propertyId1);
            tenantServices.addAndRent(tenant1);
            UUID propertyId2=propertiesRepository.findVacant().get(0).getID();
            Tenant tenant2=new Tenant("Jack", "Sparrow", new Contact("yy@gmail.com", "670909093"), propertyId2);
            tenantServices.addAndRent(tenant2);
            Lease lease1=new Lease(new ArrayList<UUID>(){{add(tenant1.getID());}},propertyId1);
            lease1.setLeaseDuration(24);
            lease1.setAgreedMonthlyRent(1000);
            lease1.setTenantNames(new ArrayList<String>(){{add(tenant1.fullName());}});
            leaseServices.addLease(lease1);

            Lease lease2=new Lease(new ArrayList<UUID>(){{add(tenant2.getID());}},propertyId2);
            lease2.setLeaseDuration(12);
            lease2.setAgreedMonthlyRent(800);
            lease2.setTenantNames(new ArrayList<String>(){{add(tenant2.fullName());}});
            leaseServices.addLease(lease2);
        }catch (Exception ex){
            System.out.println("Let it go!");
        }
    }


}
