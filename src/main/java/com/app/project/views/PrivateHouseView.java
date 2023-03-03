package com.app.project.views;

import com.app.project.controller.PrivateHouseController;
import com.app.project.entity.Condo;
import com.app.project.entity.PrivateHouse;
import com.app.project.entity.Property;

import java.util.Scanner;

public class PrivateHouseView extends RentalPropertyView {
    //    private PrivateHouse model;
//    private PrivateHouseController controller;
    private Scanner scanner;
    private PrivateHouseController controller;


    public PrivateHouseView(Scanner scanner) {
        super(scanner);
        this.scanner = scanner;
    }
    public void setController(PrivateHouseController controller) {
        this.controller = controller;
    }

    @Override
    public void add() {
        Property property = this.generatePropertyInformation("private");
        //add apartment specific information
        PrivateHouse condo = (PrivateHouse) property;
        System.out.println("Enter Street Number :");
        String streetNumber = scanner.nextLine();
        condo.setStreetNumber(streetNumber);
        this.controller.add(condo);
    }
}
