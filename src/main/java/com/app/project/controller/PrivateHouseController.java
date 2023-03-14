package com.app.project.controller;

import com.app.project.entity.Condo;
import com.app.project.views.PrivateHouseView;
/**
 * This class consists implementation of Private House property.
 */
public class PrivateHouseController extends PropertyController {
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
