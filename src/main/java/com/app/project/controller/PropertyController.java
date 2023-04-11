package com.app.project.controller;

import com.app.project.interfaces.IProperty;
import com.app.project.interfaces.IPropertyController;
import com.app.project.interfaces.IPropertyView;
import com.app.project.repository.PropertiesRepository;
import com.app.project.service.LeaseServices;
import com.app.project.service.PropertyServices;

import java.util.ArrayList;
import java.util.UUID;

/**
 * This class consists implementation of a General Property class which implements IPropertyController.
 */
public class PropertyController implements IPropertyController {
    private PropertyServices propertyServices;
    private IPropertyView view;
    private LeaseServices leaseServices;

    public PropertyController(IPropertyView view) {
        this.view = view;
        this.propertyServices = new PropertyServices(PropertiesRepository.getInstance());
        this.leaseServices = new LeaseServices();
    }

    public PropertyController() {
    }

    /**
     * Adds a property into the system
     *
     * @param property
     */
    public void add(IProperty property) {
        propertyServices.add(property);
    }

    /**
     * Displays the view to add the property details.
     */
    public void addPropertyView() {
        view.add();
    }

    /**
     * Display the property.
     *
     * @param property
     */
    @Override
    public void displayProperty(IProperty property) {
        view.displayProperty(property);
    }

    /**
     * Display All the properties.
     */
    public void displayAll() {
        ArrayList<IProperty> properties = propertyServices.getAll();
        for (IProperty property : properties
        ) {
//            System.out.println(property.getClass());
//            Should we print individual glass based on their respective views?
            view.displayProperty(property);
        }
    }

    /**
     * Display all the vacant units.
     */
    @Override
    public void displayVacantUnits() {
        ArrayList<IProperty> properties = this.propertyServices.findVacant();
        view.displayProperties(properties);
    }

    /**
     * Display all rented units
     */
    @Override
    public void displayRentedUnits() {
        ArrayList<IProperty> properties = this.propertyServices.findRented();
        view.displayProperties(properties);
    }

    /**
     * Move the tenants into a property when they are ready to rent a unit.
     *
     * @param propertyID
     * @param userID
     */
    public void moveTenants(UUID propertyID, UUID userID) throws Exception {
        this.propertyServices.moveTenantToProperty(propertyID, userID);
    }

    /**
     * Remove a tenants from a property when they vacate.
     *
     * @param propertyID
     */
    public void removeTenants(UUID propertyID) {
        this.propertyServices.removeTenants(propertyID);
        this.leaseServices.removeLease(propertyID);
    }

}
