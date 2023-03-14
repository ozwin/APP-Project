package com.app.project.service;

import com.app.project.entity.*;
import com.app.project.repository.LeaseRepository;
import com.app.project.repository.PropertiesRepository;
import com.app.project.repository.TenantRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class LeaseServicesTest {

    private static TenantRepository tenantRepository;
    private static PropertyServices propertyServices;
    private static PropertiesRepository propertiesRepository;
    private static TenantServices tenantServices;
    private  static LeaseServices leaseServices;
    private  static LeaseRepository leaseRepository;

    @BeforeAll
    static void initialize() {
        propertiesRepository = Mockito.mock(PropertiesRepository.class);
        propertyServices = new PropertyServices(propertiesRepository);
        tenantRepository = Mockito.mock(TenantRepository.class);
        tenantServices = new TenantServices(tenantRepository, propertyServices);
        leaseRepository= Mockito.mock(LeaseRepository.class);
        leaseServices=new LeaseServices(leaseRepository,propertiesRepository,tenantRepository);
    }
    @BeforeEach
    void setUp() {
        Apartment apartment = new Apartment(2, 2, 200, new Address("St Catherine", "Montreal", "H3H1K4"));
        Tenant tenant = new Tenant("Ozwin", "Lobo", new Contact("XXX@xx.com", "98989"), UUID.randomUUID());
        doNothing().when(tenantRepository).add(tenant);
        ArrayList<UUID> userIds=new ArrayList<UUID>();
        userIds.add(tenant.getUserID());
        Lease lease=new Lease(userIds,apartment.getPropertyId());
        lease.setLeaseDuration(12);
        lease.setAgreedMonthlyRent(2000);
        apartment.setLeaseId(lease.getLeaseID());
        when(tenantRepository.findByKey(any())).thenReturn(tenant);
        when(propertiesRepository.findByKey(any())).thenReturn(apartment);
        when(tenantRepository.findByKey(any())).thenReturn(tenant);
        doNothing().when(propertiesRepository).upsert(any());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getTenants() {
    }

    @Test
    void getTenantNames() {
    }

    @Test
    void addLease() {
    }

    @Test
    void removeLease() {
    }

    @Test
    void getAllLeases() {
    }

    @Test
    void recordPayment() {

    }

    @Test
    void getPropertiesWithPendingRent() {
    }
}