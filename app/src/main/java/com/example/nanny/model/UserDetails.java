package com.example.nanny.model;

import android.net.Uri;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class UserDetails {

    private String name, city, description, age, address;
    private String picture;


    public UserDetails() {

    }
    public UserDetails(String name, String city, String age, String picture) {
        this.name = name;
        this.city = city;
        this.age = age;
        this.picture = picture;
    }


    public UserDetails(String name, String city, String description, String age, String address) {
        this.name = name;
        this.city = city;
        this.description = description;
        this.age = age;
        this.address = address;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
        public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Exclude
    public Map<String, Object> toMap() {
        Map<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("city", city);
        result.put("description", description);
        result.put("age", age);
        result.put("address", address);
        return result;
    }
}
