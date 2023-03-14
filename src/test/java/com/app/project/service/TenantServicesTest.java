package com.app.project.service;

import com.app.project.entity.Address;
import com.app.project.entity.Apartment;
import com.app.project.entity.Contact;
import com.app.project.entity.Tenant;
import com.app.project.repository.PropertiesRepository;
import com.app.project.repository.TenantRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class TenantServicesTest {
    private static TenantRepository repository;
    private static PropertyServices propertyServices;
    private static PropertiesRepository propertiesRepository;
    private static TenantServices tenantServices;

    @BeforeAll
    static void initialize() {
        propertiesRepository = Mockito.mock(PropertiesRepository.class);
        propertyServices = new PropertyServices(propertiesRepository);
        repository = Mockito.mock(TenantRepository.class);
        tenantServices = new TenantServices(repository, propertyServices);
    }

    @BeforeEach
    void setUp() {
        Apartment apartment = new Apartment(2, 2, 200, new Address("St Catherine", "Montreal", "H3H1K4"));
        when(propertiesRepository.findByKey(any())).thenReturn(apartment);
        doNothing().when(propertiesRepository).upsert(any());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findMany() {
    }

    @Test
    void addTenantSuccess() {
        Tenant tenant = new Tenant("Ozwin", "Lobo", new Contact("XXX@xx.com", "98989"), UUID.randomUUID());
        doNothing().when(repository).add(tenant);
        when(repository.findByKey(any())).thenReturn(tenant);
        tenantServices.add(tenant);
        Tenant tenant2 = tenantServices.get(tenant.getUserID());
        assertEquals(tenant, tenant2);
    }

    @Test
    void getAllSuccessTest() {
        ArrayList<Tenant> tenants = new ArrayList<Tenant>();
        tenants.add(new Tenant("Ozwin", "Lobo", new Contact("XXX@xx.com", "98989"), UUID.randomUUID()));
        tenants.add(new Tenant("Ozwin", "Lobo", new Contact("XXX@xx.com", "98989"), UUID.randomUUID()));
        when(repository.getAll()).thenReturn(tenants);
        ArrayList<Tenant> tenants1 = tenantServices.getAll();
        assertEquals(tenants1, tenants);
    }

    @Test
    void tenantToStringTest() {
        Tenant tenant = new Tenant("Ozwin", "Lobo", new Contact("12345", "12345"), UUID.fromString("6afdf2ec-5155-4237-befd-86dc01a73a66"));
        System.out.println(tenant);
    }

}