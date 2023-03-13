package com.app.project.interfaces;

import com.app.project.entity.Property;

import java.util.UUID;

public interface IPropertyController {
    void displayProperty(IProperty property);

    void displayAll();

    void displayVacantUnits();

    void displayRentedUnits();

    void addPropertyView();

    void add(IProperty property);
    void moveTenants(UUID propertyID);

    void removeTenants(UUID propertyID);
}
