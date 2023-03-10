package com.app.project.interfaces;

import com.app.project.entity.Tenant;

import java.util.UUID;

public interface IProperty {
    UUID getPropertyId();
    boolean isVacant();
    void addTenantToProperty(Tenant tenant);
}
