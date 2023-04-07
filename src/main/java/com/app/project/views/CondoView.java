package com.app.project.views;

import com.app.project.controller.CondoController;
import com.app.project.entity.Condo;
import com.app.project.interfaces.IProperty;

import java.util.Scanner;

public class CondoView extends PropertyView {
    private Condo model;
    private CondoController controller;
    private Scanner scanner;

    public CondoView(Scanner scanner) {
        super(scanner);
        this.scanner = scanner;
    }

    public void setController(CondoController controller) {
        this.controller = controller;
    }

    public void add() {
        IProperty property = this.generatePropertyInformation("condo");
        //add apartment specific information
        Condo condo = (Condo) property;
        System.out.println("Enter Street Number :");
        String streetNumber = scanner.nextLine();
        condo.setStreetNumber(streetNumber);
        System.out.println("Enter Unit Number :");
        String unitNumber = scanner.nextLine();
        condo.setUnitNumber(unitNumber);
        this.controller.add(condo);
    }
}
