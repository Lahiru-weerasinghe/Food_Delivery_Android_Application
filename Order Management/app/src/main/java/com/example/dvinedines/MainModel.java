package com.example.dvinedines;

public class MainModel {

    String menucode,name,email,phone,address,no_of_oders,date,time;

    MainModel()
    {


    }

    public MainModel(String menucode, String name, String email, String phone, String address, String no_of_oders, String date, String time) {
        this.menucode = menucode;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.no_of_oders = no_of_oders;
        this.date = date;
        this.time = time;
    }

    public String getMenucode() {
        return menucode;
    }

    public void setMenucode(String menucode) {
        this.menucode = menucode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNo_of_oders() {
        return no_of_oders;
    }

    public void setNo_of_oders(String no_of_oders) {
        this.no_of_oders = no_of_oders;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
