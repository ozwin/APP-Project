package com.app.project.entity;

public class Condo extends Property {
    String streetNumber;
    String unitNumber;
    public Condo(String streetNumber,String unitNumber,Address address){
        super(address);
        this.streetNumber=streetNumber;
        this.unitNumber=unitNumber;
    }
    public Condo(Address address){
        super(address);
    }
    public void setStreetNumber(String streetNumber){
        this.streetNumber=streetNumber;
    }
    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public String toString(){
        StringBuilder builder=new StringBuilder();
        builder.append("Property ID:"+getPropertyId()+"\n");
        builder.append("Unit Number: "+getUnitNumber()+"\n");
        builder.append("Street Number: "+getStreetNumber()+"\n");
        builder.append(address.toString());
        return builder.toString();
    }
}
