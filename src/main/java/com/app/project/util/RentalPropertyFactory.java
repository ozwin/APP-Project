package com.app.project.util;

import com.app.project.entity.*;

import java.util.Locale;

public class RentalPropertyFactory {
    public Property getPropertyObject(String type, Address address) throws Exception {

        switch (type.trim().toUpperCase(Locale.ROOT)) {
            case "APARTMENT":
                return new Apartment(address);
            case "CONDO":
                return new Condo(address);
            case "HOUSE":
                return new PrivateHouse(address);
            default:
                throw new Exception("PLease specify a property type");
        }

//        case "PRIVATE":
//            return new PrivateHouse<T>();
//        case "CONDO":
//        return new Condo<T>();

    }

}
