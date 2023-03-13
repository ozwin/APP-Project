package com.app.project.controller;

import com.app.project.interfaces.IProperty;
import com.app.project.interfaces.IPropertyController;
import com.app.project.interfaces.IPropertyView;
import com.app.project.repository.PropertiesRepository;
import com.app.project.service.PropertyServices;

import java.util.ArrayList;
import java.util.UUID;

public  class PropertyController implements IPropertyController {
    private PropertyServices propertyServices;
    private IPropertyView view;

    public PropertyController(IPropertyView view) {
        this.view = view;
        this.propertyServices = new PropertyServices(PropertiesRepository.getInstance());
    }

    public void add(IProperty property) {
        propertyServices.add(property);
    }

    public void addPropertyView() {
        view.add();
    }

    @Override
    public void displayProperty(IProperty property) {
        view.displayProperty(property);
    }

    public void displayAll() {
        ArrayList<IProperty> properties = propertyServices.getAll();
        view.displayProperties(properties);
    }

    @Override
    public void displayVacantUnits() {
        ArrayList<IProperty> properties = this.propertyServices.findVacant();
        view.displayProperties(properties);
    }

    @Override
    public void displayRentedUnits() {
        ArrayList<IProperty> properties = this.propertyServices.findRented();
        view.displayProperties(properties);
    }

    public void moveTenants(UUID propertyID){
        this.propertyServices.moveTenantToProperty(propertyID);
    }

    public void removeTenants(UUID propertyID){
        this.propertyServices.removeTenants(propertyID);
    }

}
