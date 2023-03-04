package com.app.project.interfaces;

import com.app.project.entity.Property;

public interface IPropertyController {
    void displayProperty(Property property);

    void displayAll();

    void displayVacantUnits();

    void displayRentedUnits();

    void addPropertyView();

    void add(Property property);
}
