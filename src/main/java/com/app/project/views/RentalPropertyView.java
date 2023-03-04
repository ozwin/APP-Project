package com.app.project.views;

import com.app.project.controller.RentalPropertyController;
import com.app.project.entity.Address;
import com.app.project.entity.Property;
import com.app.project.interfaces.IPropertyView;
import com.app.project.util.RentalPropertyFactory;

import java.util.ArrayList;
import java.util.Scanner;

public class RentalPropertyView implements IPropertyView {
    private RentalPropertyController controller;
    private Scanner scanner;

    public RentalPropertyView(Scanner scanner) {
        this.scanner = scanner;
    }

    private static Address getAddress(Scanner scanner) {
        System.out.println("Enter Street Name");
        String streetName = scanner.nextLine();
        System.out.println("Enter City");
        String city = scanner.nextLine();
        System.out.println("Enter Postal Code");
        String postalCode = scanner.nextLine();
        return new Address(streetName, city, postalCode);
    }

    public Property generatePropertyInformation(String type) {
        try {
            Address propertyAddress = getAddress(scanner);
            Property property = new RentalPropertyFactory().getPropertyObject(type, propertyAddress);
            return property;
        } catch (Exception ex) {
            System.out.println("Some error happened");
        }
        return null;
    }

    public void displayProperties(ArrayList<Property> properties) {
        for (Property property : properties
        ) {
            this.displayProperty(property);
        }
    }

    public void displayProperty(Property property) {
        System.out.println("********************************");
        System.out.println("Apartment Details");
        System.out.println(property);
        System.out.println("********************************");
    }

    @Override
    public void add() {

    }

}
