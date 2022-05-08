package com.example.divinedines;

public class MainModel {
    String name,email,comment,fdurl;
    MainModel()
    {

    }

    public MainModel(String name, String email, String comment, String fdurl) {
        this.name = name;
        this.email = email;
        this.comment = comment;
        this.fdurl = fdurl;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFdurl() {
        return fdurl;
    }

    public void setFdurl(String fdurl) {
        this.fdurl = fdurl;
    }
}
