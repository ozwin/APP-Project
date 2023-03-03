package com.app.project.controller;

import com.app.project.entity.Condo;
import com.app.project.views.PrivateHouseView;

public class PrivateHouseController extends RentalPropertyController {
    PrivateHouseView view;

    public PrivateHouseController(PrivateHouseView view) {
        super(view);
        this.view = view;
        this.view.setController(this);
    }

    public void displayProperty(Condo apartment) {
        this.view.displayProperty(apartment);
    }
}
