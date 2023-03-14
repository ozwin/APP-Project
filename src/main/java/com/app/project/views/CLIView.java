package com.app.project.views;

import com.app.project.controller.LeaseController;
import com.app.project.controller.TenantController;
import com.app.project.interfaces.IPropertyController;
import com.app.project.util.ControllerFactory;
import com.app.project.util.ScannerSingleton;

import java.security.InvalidKeyException;
import java.util.Scanner;
import java.util.UUID;

public class CLIView {
    private final TenantController tenantController = new TenantController(new TenantView(ScannerSingleton.getInstance().scanner));
    private IPropertyController controller = ControllerFactory.getController("");
    private LeaseController leaseController = new LeaseController(new LeaseView(ScannerSingleton.getInstance().scanner));

    public void run() {
        while (true) {
            try {
                System.out.println("Enter your choice");
                //inject this dependency instead of creation
                Scanner scanner = ScannerSingleton.getInstance().scanner;
                System.out.println("1. Add Property \n2. Add a tenant \n3. Rent a Unit \n4.Display Properties \n5. display tenants \n6. display vacant units \n7.display rented units \n8. Display All Leases \n9. Vacate A unit  \n10.Pay Rent \n11.Display Properties with pending rent \n12. Exit");
                int input = Integer.parseInt(scanner.nextLine().trim());
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
                        //add a tenant to a property
                            tenantController.addTenantView();
                    case 3 -> {
                        System.out.println("Enter the property ID");
                        UUID propertyID = UUID.fromString(scanner.nextLine().trim());
                        System.out.println("Are you a in a waiting list for this property? Enter Y or N");
                        String choice = scanner.nextLine().trim();
                        if (choice.equals("Y")) {
                            System.out.println("Enter your tenant ID: ");
                            UUID userID = UUID.fromString(scanner.nextLine().trim());
                            controller.moveTenants(propertyID, userID);
                        } else if (choice.equals("N")) {
                            tenantController.addTenantToProperty(propertyID);
                        } else {
                            throw new InvalidKeyException("Please enter a valid choice");
                        }
                        leaseController.addLeaseView(propertyID);

                    }
                    case 4 ->
                        //display all properties
                            controller.displayAll();
                    case 5 -> tenantController.displayAllTenants();
                    case 6 -> controller.displayVacantUnits();
                    case 7 -> controller.displayRentedUnits();
                    case 8 -> {
                        // display all leases
                        leaseController.displayAllLeases();
                    }
                    case 9 -> {
                        // vacate a property
                        System.out.println("Enter the property ID");
                        String propertyID = scanner.nextLine();
                        controller.removeTenants(UUID.fromString(propertyID));
                    }
                    case 12 -> {
                        System.out.println("Exiting Application");
                        System.exit(0);
                    }
                    case 10 -> {
                        System.out.println("Enter the property Id for which you want to pay rent");
                        UUID propertyId = UUID.fromString(scanner.nextLine().trim());
                        System.out.println("Enter the amount to be paid");
                        double rent = Double.parseDouble(scanner.nextLine());
                        leaseController.recordPayment(propertyId, rent);
                    }
                    case 11 -> {
                        System.out.println("Properties that are yet to pay");
                        leaseController.findPropertiesWithPendingPayment();
                    }
                }
            } catch (Exception ex) {
                System.out.println("Sorry there was some issue with your input or something terrible happened, please retry");
            }
        }
    }


}
