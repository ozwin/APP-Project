package com.app.project.controller;

import com.app.project.entity.Apartment;
import com.app.project.views.ApartmentView;

/**
 * This class consists implementation of Apartment properties.
 */
public class ApartmentController extends PropertyController {
    private ApartmentView view;

    /**
     * Constructor for Apartment Controller
     * @param apartmentView
     */
    public ApartmentController(ApartmentView apartmentView) {
        super(apartmentView);
        this.view = apartmentView;
        this.view.setController(this);
    }

    /**
     * Display the Apartment Property
     * @param apartment
     */
    public void displayProperty(Apartment apartment) {
        view.displayProperty(apartment);
    }
}
