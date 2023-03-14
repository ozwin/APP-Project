package com.app.project.service;

import com.app.project.entity.*;
import com.app.project.interfaces.IProperty;
import com.app.project.repository.PropertiesRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PropertyServicesTest {
    private static PropertiesRepository repository;
    private static PropertyServices propertyServices;

    @BeforeAll
    static void initialize() {
        repository = Mockito.mock(PropertiesRepository.class);
        propertyServices = new PropertyServices(repository);
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void givenValueExistsInCache_whenGetProduct_thenDAOIsNotCalled() {
    }


    @AfterEach
    void tearDown() {
    }

    @Test
    void addTestSuccess() {
        Apartment apartment = new Apartment(2, 2, 200, new Address("St Catherine", "Montreal", "H3H1K4"));
        UUID propertyID = apartment.getPropertyId();
        when(repository.insert(apartment)).thenReturn(true);
        when(repository.findByKey(propertyID)).thenReturn(apartment);
        propertyServices.add(apartment);
        IProperty property = propertyServices.getByKey(propertyID);
        assertEquals(apartment, property);
    }

    @Test
    void getByKeyTestSuccess() {
        Apartment apartment = new Apartment(2, 2, 200, new Address("St Catherine", "Montreal", "H3H1K4"));
        UUID propertyID = apartment.getPropertyId();
        when(repository.insert(apartment)).thenReturn(true);
        when(repository.findByKey(propertyID)).thenReturn(apartment);
        propertyServices.add(apartment);
        IProperty property = propertyServices.getByKey(propertyID);
        assertEquals(apartment, property);
    }

    @Test
    void getAllTestSuccess() {
        Apartment apartment = new Apartment(2, 2, 200, new Address("St Catherine", "Montreal", "H3H1K4"));
        Condo condo = new Condo("23", "2", new Address("St Catherine", "Montreal", "H3H1K4"));
        PrivateHouse house = new PrivateHouse("54", new Address("St Catherine", "Montreal", "H3H1K4"));
        ArrayList<IProperty> properties = propertyServices.getAll();
        when(repository.insert(any())).thenReturn(true);
        propertyServices.add(apartment);
        propertyServices.add(condo);
        propertyServices.add(house);
        properties.add(apartment);
        properties.add(condo);
        properties.add(house);
        when(repository.getAll()).thenReturn(properties);
        ArrayList<IProperty> rentalProperties = propertyServices.getAll();
        assertEquals(properties, rentalProperties);
    }

    @Test
    void findVacantTestSuccess() {
        Apartment apartment = new Apartment(2, 2, 200, new Address("St Catherine", "Montreal", "H3H1K4"));
        Condo condo = new Condo("23", "2", new Address("St Catherine", "Montreal", "H3H1K4"));
        doNothing().when(repository).upsert(apartment);
        ArrayList<IProperty> vacantProperties = new ArrayList<IProperty>();
        vacantProperties.add(condo);
        when(repository.findByKey(apartment.getPropertyId())).thenReturn(apartment);
        when(repository.findVacant()).thenReturn(vacantProperties);
        propertyServices.assignATenant(new Tenant("Ozwin", "Lobo", new Contact("xxx@gmail.com", "90807333"), apartment.getPropertyId()));
        ArrayList<IProperty> properties = propertyServices.findVacant();
        assertEquals(properties, vacantProperties);
    }

    @Test
    void findRentedSuccess() {
        Apartment apartment = new Apartment(2, 2, 200, new Address("St Catherine", "Montreal", "H3H1K4"));
        Condo condo = new Condo("23", "2", new Address("St Catherine", "Montreal", "H3H1K4"));
        doAnswer((i) -> {
            return null;
        }).when(repository).upsert(apartment);
        ArrayList<IProperty> rentedProperties = new ArrayList<IProperty>();
        rentedProperties.add(apartment);
        when(repository.findByKey(apartment.getPropertyId())).thenReturn(apartment);
        when(repository.findRented()).thenReturn(rentedProperties);
        propertyServices.assignATenant(new Tenant("Ozwin", "Lobo", new Contact("xxx@gmail.com", "90807333"), apartment.getPropertyId()));
        ArrayList<IProperty> properties = propertyServices.findRented();
        assertEquals(properties, rentedProperties);
    }

    @Test
    void assignATenantSuccess() {
    }
}