package com.app.project.util;

import com.app.project.controller.ApartmentController;
import com.app.project.controller.CondoController;
import com.app.project.controller.PrivateHouseController;
import com.app.project.controller.RentalPropertyController;
import com.app.project.interfaces.IPropertyController;
import com.app.project.views.ApartmentView;
import com.app.project.views.CondoView;
import com.app.project.views.PrivateHouseView;
import com.app.project.views.RentalPropertyView;

import java.util.Locale;

public class ControllerFactory {
    public static IPropertyController getController(String type) {
        switch (type.trim().toUpperCase(Locale.ROOT)) {
            case "APARTMENT":
                return new ApartmentController(new ApartmentView(ScannerSingleton.getInstance().scanner));
            case "CONDO":
                return new CondoController(new CondoView(ScannerSingleton.getInstance().scanner));
            case "HOUSE":
                return new PrivateHouseController(new PrivateHouseView(ScannerSingleton.getInstance().scanner));
            default:
                return new RentalPropertyController(new RentalPropertyView(ScannerSingleton.getInstance().scanner));
        }
    }
}
