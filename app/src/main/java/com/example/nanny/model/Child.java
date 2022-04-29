package com.example.nanny.model;

public class Child {
    private String name, preference;
    private int age;

    public Child(String name, String preference, int age) {
        this.name = name;
        this.preference = preference;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getPreference() {
        return preference;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
