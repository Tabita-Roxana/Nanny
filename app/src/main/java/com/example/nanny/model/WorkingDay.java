package com.example.nanny.model;

public class WorkingDay {
    private boolean morning, noon, afternoon;
    private String shortcut;

    public WorkingDay() {

    }

    public WorkingDay(boolean morning, boolean noon, boolean afternoon, String shortcut) {
        this.morning = morning;
        this.noon = noon;
        this.afternoon = afternoon;
        this.shortcut = shortcut;
    }

    public boolean isMorning() {
        return morning;
    }

    public void setMorning(boolean morning) {
        this.morning = morning;
    }

    public boolean isNoon() {
        return noon;
    }

    public void setNoon(boolean noon) {
        this.noon = noon;
    }

    public boolean isAfternoon() {
        return afternoon;
    }

    public void setAfternoon(boolean afternoon) {
        this.afternoon = afternoon;
    }

    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

    @Override
    public String toString() {
        return "WorkingDay{" +
                "morning=" + morning +
                ", noon=" + noon +
                ", afternoon=" + afternoon +
                ", shortcut='" + shortcut + '\'' +
                '}';
    }
}
