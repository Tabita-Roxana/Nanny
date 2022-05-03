package com.example.nanny.model;


import java.util.ArrayList;


public class User {

    private String email, password, type;
    private UserDetails userDetails;
    private ArrayList<Child> children;


    public User() {
    }

    public User(String email, String password, String type) {
        this.email = email;
        this.password = password;
        this.type = type;
    }
    public User( String type,UserDetails userDetails)
    {
        this.type = type;
        this.userDetails =  userDetails;
    }


    public User(String email, String password, String type, UserDetails userDetails) {
        this.email = email;
        this.password = password;
        this.type = type;
        this.userDetails = userDetails;
        children = new ArrayList<>();

    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Child> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Child> children) {
        this.children = children;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
}
