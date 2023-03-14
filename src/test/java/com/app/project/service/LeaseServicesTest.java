package com.app.project.service;

import com.app.project.entity.*;
import com.app.project.interfaces.IProperty;
import com.app.project.repository.LeaseRepository;
import com.app.project.repository.PropertiesRepository;
import com.app.project.repository.TenantRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
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
        ArrayList<Lease> leases=new ArrayList<>();
        leases.add(lease);
        apartment.setLeaseId(lease.getLeaseID());
        ArrayList<IProperty> properties=new ArrayList<>();
        properties.add(apartment);
        when(tenantRepository.findByKey(any())).thenReturn(tenant);
        when(propertiesRepository.findByKey(any())).thenReturn(apartment);
        when(tenantRepository.findByKey(any())).thenReturn(tenant);
        when(leaseRepository.findLease(any())).thenReturn(lease);
        when(leaseRepository.getLeases()).thenReturn(leases);
        when(propertiesRepository.findMany(any())).thenReturn(properties);
        when(propertiesRepository.getAll()).thenReturn(properties);
        doNothing().when(propertiesRepository).upsert(any());
        doNothing().when(leaseRepository).upsert(any());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getTenants() {
        List<UUID> tenants = leaseServices.getTenants(propertyServices.getAll().get(0).getPropertyId());
        Tenant tenant = new Tenant("Srikar", "Akella", new Contact("ABC@gmail.com", "9120344"), UUID.randomUUID());
        tenants.add(tenant.getUserID());
        doNothing().when(tenantRepository).add(tenant);
        assertEquals(tenants, leaseServices.getTenants(propertyServices.getAll().get(0).getPropertyId()));
    }

    @Test
    void getTenantNames() {
        List<String> tenants = new ArrayList<>();
        Tenant tenant = new Tenant("Srikar", "Akella", new Contact("ABC@gmail.com", "9120344"), UUID.randomUUID());
        tenants.add(tenant.fullName());
        doNothing().when(tenantRepository).add(tenant);
        assertEquals(tenants, leaseServices.getTenantNames(propertyServices.getAll().get(0).getPropertyId()));
    }

    @Test
    void addLease() {
        List<UUID> tenants = new ArrayList<>();
        UUID tenant = UUID.randomUUID();
        tenants.add(tenant);
        Lease lease = new Lease(tenants, propertiesRepository.getAll().get(0).getPropertyId());
        lease.setLeaseDuration(10);
        lease.setAgreedMonthlyRent(2000);
        doNothing().when(leaseRepository).addLease(lease);
        UUID tenant2 = tenantServices.getTenant(tenant).getUserID();
        assertEquals(tenant, tenant2);
    }

    @Test
    void removeLease() {
    }

    @Test
    void getAllLeases() {
        ArrayList<Lease> leases = new ArrayList<>();
        List<UUID> tenants = new ArrayList<>();
        UUID tenant = UUID.randomUUID();
        tenants.add(tenant);
        Lease lease = new Lease(tenants, propertiesRepository.getAll().get(0).getPropertyId());
        lease.setLeaseDuration(10);
        lease.setAgreedMonthlyRent(2000);
        leases.add(lease);
        doNothing().when(leaseRepository).addLease(lease);
        assertEquals(leases, leaseRepository.getLeases());
    }

    @Test
    void recordPaymentSucessTest()  {
        Assertions.assertThrows(Exception.class,()->leaseServices.recordPayment(UUID.randomUUID(),2000));
    }

    @Test
    void getPropertiesWithPendingRentSucessTest() {
        ArrayList<IProperty> properties=propertyServices.getAll();
        ArrayList<IProperty> output=leaseServices.getPropertiesWithPendingRent();
        assertEquals(properties,output);

    }
}