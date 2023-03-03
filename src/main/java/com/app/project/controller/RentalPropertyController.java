package com.app.project.controller;

import com.app.project.entity.Property;
import com.app.project.interfaces.IPropertyController;
import com.app.project.interfaces.IPropertyView;
import com.app.project.service.PropertyServices;

import java.util.ArrayList;

public  class RentalPropertyController implements IPropertyController {
    private PropertyServices propertyServices;
    private IPropertyView view;

    public RentalPropertyController(IPropertyView view) {
        this.view = view;
        this.propertyServices=new PropertyServices();
    }

    public void add(Property property) {
        propertyServices.add(property);
    }

    public void addPropertyView() {
        view.add();
    }
    public void displayAll() {
        ArrayList<Property> properties = propertyServices.getAll();
        for (Property property : properties
        ) {
//            System.out.println(property.getClass());
//            Should we print inducividual glass based on their respective views?
            view.displayProperty(property);
        }
    }
    @Override
    public void displayVacantUnits() {
        ArrayList<Property> properties=this.propertyServices.findVacant();
        view.displayProperties(properties);
    }

    @Override
    public void displayRentedUnits() {
        ArrayList<Property> properties=this.propertyServices.findRented();
        view.displayProperties(properties);
    }
    @Override
    public void displayProperty(Property property) {
        view.displayProperty(property);

    }

}
