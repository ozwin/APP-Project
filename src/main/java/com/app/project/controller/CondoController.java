package com.app.project.controller;

import com.app.project.entity.Condo;
import com.app.project.views.CondoView;

/**
 * This class consists implementation of Condo properties.
 */
public class CondoController extends PropertyController {
    CondoView view;

    public CondoController(CondoView view) {
        super(view);
        this.view = view;
        this.view.setController(this);
    }

    public void displayProperty(Condo apartment) {
        this.view.displayProperty(apartment);
    }


}
