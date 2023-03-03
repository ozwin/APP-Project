package com.app.project.views;

import com.app.project.controller.TenantController;
import com.app.project.interfaces.IPropertyController;
import com.app.project.util.ControllerFactory;
import com.app.project.util.ScannerSingleton;

import java.util.Scanner;

public class CLIView {
    private IPropertyController controller=ControllerFactory.getController("");
    private TenantController tenantController = new TenantController(new TenantView(ScannerSingleton.getInstance().scanner));

    public void run() {
        while (true) {
            try {
                System.out.println("Enter your choice");
                //inject this dependency instead of creation
                Scanner scanner = ScannerSingleton.getInstance().scanner;
                System.out.println("1. Add Property 2.Show Properties 3. Add a tenant 4. display all tenants 5. display vacant 6.display rented");
                int input = Integer.parseInt(scanner.nextLine());
                switch (input) {
//                use enums here
                    case 1:
                        //add a property
                        System.out.println("Choose a type of property");
                        String propertyType = scanner.nextLine();
                        controller = ControllerFactory.getController(propertyType);
                        controller.addPropertyView();
                        break;
                    case 2:
                        //display all properties
                        controller.displayAll();
                        break;
                    case 3:
                        //add a tenant to a property
                        tenantController.addTenantView();
                        break;
                    case 4:
                        tenantController.displayAllTenants();
                        break;
                    case 5:
                        controller.displayVacantUnits();
                        break;
                    case 6:
                        controller.displayRentedUnits();
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Sorry there was some issue with your input or something terrible happened, please retry");
            }
        }
    }


}
