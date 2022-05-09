package com.example.madfinal;


//Data model
public class MainModel {


    String fname,mobile,email,menu,date;

    MainModel(){


    }

    public MainModel(String fname, String mobile, String email, String menu, String date) {
        this.fname = fname;
        this.mobile = mobile;
        this.email = email;
        this.menu = menu;
        this.date = date;
    }

    public String getFname() {

        return fname;
    }

    public void setFname(String fname) {

        this.fname = fname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
