package com.app.project.views;

import com.app.project.controller.TenantController;
import com.app.project.entity.Contact;
import com.app.project.entity.Tenant;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class TenantView {
    private final Scanner scanner;
    private TenantController controller;

    public TenantView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void setController(TenantController controller) {
        this.controller = controller;
    }

    public void add() {
        System.out.println("Enter Apartment/Condo/Private House Id:");
        UUID propertyID = UUID.fromString(scanner.nextLine().trim());
//      are we supposed to consider the case where user is already in the system as a prospective client
        Tenant tenant = generateTenant(propertyID);
        this.controller.addTenant(tenant);
    }

    public void addToProperty(UUID propertyID) {
        Tenant tenant = generateTenant(propertyID);
        this.controller.addAndRent(tenant);
    }

    public void displayAll(ArrayList<Tenant> tenants) {
        for (Tenant tenant : tenants
        ) {
            System.out.println(tenant);
        }
    }

    private Tenant generateTenant(UUID propertyID) {
        System.out.println("Enter first name");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name");
        String lastName = scanner.nextLine();
        System.out.println("Enter phone number");
        String phoneNumber = scanner.nextLine();
        System.out.println("Enter email");
        String email = scanner.nextLine();
        Contact contact = new Contact(email, phoneNumber);
        return new Tenant(firstName, lastName, contact, propertyID);
    }
}
