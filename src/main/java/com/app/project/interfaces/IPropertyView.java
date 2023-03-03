package com.app.project.interfaces;

import com.app.project.entity.Property;

import java.util.ArrayList;

public interface IPropertyView {
    void displayProperty(Property property);
    void displayProperties(ArrayList<Property> properties);
    void add();
}
