package com.app.project.views;

import com.app.project.controller.LeaseController;
import com.app.project.controller.TenantController;
import com.app.project.entity.Lease;
import com.app.project.interfaces.IPropertyController;
import com.app.project.util.ControllerFactory;
import com.app.project.util.ScannerSingleton;

import java.util.Scanner;
import java.util.UUID;

public class CLIView {
    private IPropertyController controller= ControllerFactory.getController("");
    private LeaseController leaseController = new LeaseController(new LeaseView(ScannerSingleton.getInstance().scanner));
    private final TenantController tenantController = new TenantController(new TenantView(ScannerSingleton.getInstance().scanner));

    public void run() {
        while (true) {
            try {
                System.out.println("Enter your choice");
                //inject this dependency instead of creation
                Scanner scanner = ScannerSingleton.getInstance().scanner;
                System.out.println("1. Add Property 2.Show Properties 3. Add a tenant 4. display all tenants 5. display vacant 6.display rented 7. Exit");
                int input = Integer.parseInt(scanner.nextLine());
                switch (input) {
//                use enums here
                    case 1 -> {
                        //add a property
                        System.out.println("Choose a type of property");
                        String propertyType = scanner.nextLine();
                        controller = ControllerFactory.getController(propertyType);
                        controller.addPropertyView();
                    }
                    case 2 ->
                        //display all properties
                            controller.displayAll();
                    case 3 ->
                        //add a tenant to a property
                            tenantController.addTenantView();
                    case 4 -> tenantController.displayAllTenants();
                    case 5 -> controller.displayVacantUnits();
                    case 6 -> controller.displayRentedUnits();
                    case 7 ->{
                        // enter the ID of the unit to rent it to the future tenants.
                        leaseController.setController();
                        System.out.println("Enter the property ID");
                        String propertyID = scanner.nextLine();
                        controller.moveTenants(UUID.fromString(propertyID));
                        leaseController.addLeaseView(UUID.fromString(propertyID));

                    }
                    case 8->{
                        // display all leases
                        leaseController.displayAllLeases();
                    }
                    case 9 ->{
                        // vacate a property
                        System.out.println("Enter the property ID");
                        String propertyID = scanner.nextLine();

                    }
                    case 10 -> {
                        System.out.println("Exiting Application");
                        System.exit(0);
                    }
                }
            } catch (Exception ex) {
                System.out.println("Sorry there was some issue with your input or something terrible happened, please retry");
            }
        }
    }


}
