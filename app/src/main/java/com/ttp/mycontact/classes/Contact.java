package com.ttp.mycontact.classes;

public class Contact {


    int id;
    private String name;
    private String phoneNumber;




    public Contact(int id, String name, String phoneNumber) {

        this.id=id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
