package com.app.project.interfaces;

import com.app.project.entity.Tenant;

import java.util.List;
import java.util.UUID;

public interface IProperty extends IEntity<UUID>{
    UUID getPropertyId();

    boolean isVacant();

    void addTenantToProperty(Tenant tenant);

    void moveTenant(UUID userID) throws Exception;

    List<UUID> getTenants();

    void removeTenants();
}
