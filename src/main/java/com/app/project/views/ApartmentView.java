package com.app.project.views;

import com.app.project.controller.ApartmentController;
import com.app.project.entity.Address;
import com.app.project.entity.Apartment;
import com.app.project.entity.Property;
import com.app.project.util.Helper;
import com.app.project.util.RentalPropertyFactory;
import com.app.project.util.ScannerSingleton;

import java.util.Scanner;

public class ApartmentView extends RentalPropertyView{
    private ApartmentController controller;
    private Scanner scanner;
    public ApartmentView(Scanner scanner){
        super(scanner);
        this.scanner=scanner;
    }
    public void setController(ApartmentController controller){
        this.controller=controller;
    }
    public void displayProperty(Apartment apartment){
        System.out.println("********************************");
        System.out.println("Apartment Details");
        System.out.println(apartment.getAddress());
        System.out.println("Number of Bedrooms: "+apartment.getNumberOfBedrooms());
        System.out.println("Number of Bathrooms: "+apartment.getNumberOfBathrooms());
        System.out.println("Square foot area: "+apartment.getSquareFoot());
        System.out.println("********************************");
    }

    public void add(){
        Property property=this.generatePropertyInformation("apartment");
        //add apartment specific information
        Apartment apartment=(Apartment) property;
        System.out.println("Enter Number of Bedrooms");
        int numberOfBedrooms=Integer.parseInt(scanner.nextLine());
        System.out.println("Enter Number of Bathrooms");
        int numberOfBathrooms=Integer.parseInt(scanner.nextLine());
        System.out.println("Enter square foot are of the apartment");
        float squareFootArea=Float.parseFloat(scanner.nextLine());
        apartment.setNumberOfBathrooms(numberOfBathrooms);
        apartment.setNumberOfBedrooms(numberOfBedrooms);
        apartment.setSquareFoot(squareFootArea);
        this.controller.add(apartment);
    }

}
