package com.app.project.entity;

public class Contact {
    String phoneNumber;
    String email;
    String fax;
    /***
     * Constructor to initialise Contact object
     * @param email
     * @param phoneNumber
     */
    public Contact(String email,String fax,String phoneNumber){
        this.email=email;
        this.fax=fax;
        this.phoneNumber=phoneNumber;
    }

    /***
     * Since fax is not common, you can use this constructor
     * @param email
     * @param phoneNumber
     */
    public Contact(String email,String phoneNumber){
        this.email=email;
        this.phoneNumber=phoneNumber;
    }
    public String toString(){
        return String.format("Phone: %s\n Email: %s\n",this.phoneNumber,this.email);
    }

}
