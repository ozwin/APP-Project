package com.app.project.util;

import com.app.project.entity.*;
import com.app.project.interfaces.IProperty;

import java.util.Locale;

public class RentalPropertyFactory {
    public static IProperty getPropertyObject(String type, Address address) {

        switch (type.trim().toUpperCase(Locale.ROOT)) {
            case "APARTMENT":
                return new Apartment(address);
            case "CONDO":
                return new Condo(address);
            case "HOUSE":
            case "PRIVATE HOUSE":
                return new PrivateHouse(address);
            default:
                return new Apartment(address);
        }

//        case "PRIVATE":
//            return new PrivateHouse<T>();
//        case "CONDO":
//        return new Condo<T>();

    }

}
