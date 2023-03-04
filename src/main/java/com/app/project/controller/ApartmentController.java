package com.app.project.controller;

import com.app.project.entity.Apartment;
import com.app.project.views.ApartmentView;

public class ApartmentController extends RentalPropertyController {
    private ApartmentView view;

    public ApartmentController(ApartmentView apartmentView) {
        super(apartmentView);
        this.view = apartmentView;
        this.view.setController(this);
    }

    public void displayProperty(Apartment apartment) {
        view.displayProperty(apartment);
    }
}
