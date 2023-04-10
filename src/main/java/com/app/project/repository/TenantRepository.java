package com.app.project.repository;

import com.app.project.entity.Tenant;
import com.app.project.service.NotificationServices;

import java.util.UUID;

/**
 * The repository to store Tenants in the database.
 */
public class TenantRepository extends Repository<Tenant, UUID> {
    private static final NotificationServices notificationServices = new NotificationServices();
    private static TenantRepository tenantRepository;

    public TenantRepository() {
        super();
    }

    public static synchronized TenantRepository getInstance() {
        if (tenantRepository == null)
            tenantRepository = new TenantRepository();
        return tenantRepository;
    }
}
