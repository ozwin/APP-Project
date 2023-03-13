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
                System.out.println("1. Add Property 2. Add a tenant 3. Rent a Unit 4.Display Properties 5. display all tenants 6. display vacant 7.display rented 8. Display All Leases 9. Vacate A unit 10. Exit");
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
                        //add a tenant to a property
                            tenantController.addTenantView();
                    case 3 ->{
                        System.out.println("Are you a in a waiting list for any of the properties: y or n");
                        String in = scanner.nextLine();
                        System.out.println("Enter the property ID");
                        String propertyID = scanner.next();
                        if(in.equals("y")){
                            leaseController.setController();
                            System.out.println("Enter your user ID: ");
                            String userID = scanner.next();
                            controller.moveTenants(UUID.fromString(propertyID), UUID.fromString(userID));
                        }else{
                            tenantController.addTenantView();
                            tenantController.addTenantToProperty(UUID.fromString(propertyID), controller);
                        }
                        leaseController.setController();
                        leaseController.addLeaseView(UUID.fromString(propertyID));

                    }
                    case 4 ->
                        //display all properties
                            controller.displayAll();
                    case 5 -> tenantController.displayAllTenants();
                    case 6 -> controller.displayVacantUnits();
                    case 7 -> controller.displayRentedUnits();
                    case 8->{
                        // display all leases
                        leaseController.displayAllLeases();
                    }
                    case 9 ->{
                        // vacate a property
                        System.out.println("Enter the property ID");
                        String propertyID = scanner.nextLine();
                        controller.removeTenants(UUID.fromString(propertyID));
                    }
                    case 10 -> {
                        System.out.println("Exiting Application");
                        System.exit(0);
                    }
                }
            } catch (Exception ex) {
                System.out.println("Sorry there was some issue with your input or something terrible happened, please retry" );
            }
        }
    }


}
