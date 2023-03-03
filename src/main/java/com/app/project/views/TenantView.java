package com.app.project.views;

import com.app.project.controller.TenantController;
import com.app.project.entity.Address;
import com.app.project.entity.Contact;
import com.app.project.entity.Tenant;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class TenantView {
    private TenantController controller;
    private Scanner scanner;

    public TenantView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void setController(TenantController controller) {
        this.controller = controller;
    }
    public void add(){
        System.out.println("Enter Apartment/Condo/Private House Id:");
        UUID propertyID = UUID.fromString(scanner.nextLine().trim());
//      are we supposed to consider the case where user is already in the system as a prospective client
//        System.out.println("Enter the number of tenants");
        System.out.println("Enter first name");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name");
        String lastName = scanner.nextLine();
        System.out.println("Enter phone number");
        String phoneNumber = scanner.nextLine();
        System.out.println("Enter email");
        String email = scanner.nextLine();
        Contact contact=new Contact(email,phoneNumber);
        Tenant tenant=new Tenant(firstName,lastName,contact,propertyID);
        this.controller.addTenant(tenant);
    }
    public  void displayAll(ArrayList<Tenant> tenants){
        for (Tenant tenant:tenants
             ) {
            System.out.println(tenant);
        }
    }
}
