package com.app.project.service;

import com.app.project.entity.Address;
import com.app.project.entity.Apartment;
import com.app.project.entity.Condo;
import com.app.project.entity.PrivateHouse;
import com.app.project.interfaces.IProperty;
import com.app.project.repository.PropertiesRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class PropertyServicesTest {
    private static PropertiesRepository repository;
    private static PropertyServices propertyServices;
    @BeforeAll
    static void initialize(){
        repository= Mockito.mock(PropertiesRepository.class);
        propertyServices=new PropertyServices(repository);
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
        Apartment apartment=new Apartment(2,2,200,new Address("St Catherine","Montreal","H3H1K4"));
        UUID propertyID=apartment.getPropertyId();
        propertyServices.add(apartment);
        when(repository.insert(apartment)).thenReturn(true);
        when(repository.findByKey(propertyID)).thenReturn(apartment);
        IProperty property=propertyServices.getByKey(propertyID);
        assertEquals(apartment,property);
    }

    @Test
    void getByKeyTestSuccess() {
        Apartment apartment=new Apartment(2,2,200,new Address("St Catherine","Montreal","H3H1K4"));
        propertyServices.add(apartment);
        UUID propertyID=apartment.getPropertyId();
        IProperty property=propertyServices.getByKey(propertyID);
        assertEquals(apartment,property);
    }

    @Test
    void getAllTestSuccess() {
        Apartment apartment=new Apartment(2,2,200,new Address("St Catherine","Montreal","H3H1K4"));
        Condo condo=new Condo("23","2",new Address("St Catherine","Montreal","H3H1K4"));
        PrivateHouse house=new PrivateHouse("54",new Address("St Catherine","Montreal","H3H1K4"));
        ArrayList<IProperty> properties=propertyServices.getAll();
        propertyServices.add(apartment);
        propertyServices.add(condo);
        propertyServices.add(house);
        properties.add(apartment);
        properties.add(condo);
        properties.add(house);
        ArrayList<IProperty> rentalProperties=propertyServices.getAll();
        assertEquals(properties,rentalProperties);
    }

    @Test
    void findVacantTestSuccess() {
        Apartment apartment=new Apartment(2,2,200,new Address("St Catherine","Montreal","H3H1K4"));
        Condo condo=new Condo("23","2",new Address("St Catherine","Montreal","H3H1K4"));

    }

    @Test
    void findRentedSuccess() {
    }

    @Test
    void assignATenantSuccess() {
    }
}