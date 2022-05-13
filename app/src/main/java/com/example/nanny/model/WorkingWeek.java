package com.example.nanny.model;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class WorkingWeek {

    private ArrayList<WorkingDay> days;

    public WorkingWeek(ArrayList<WorkingDay> days) {
        this.days = days;
    }
    public WorkingWeek() {
       days = new ArrayList<>();
    }

    public WorkingDay getDayByIndex(int index){
        return days.get(index);
    }

    public int getDaysCount(){
        return days.size();
    }

    public ArrayList<WorkingDay> getDays() {
        return days;
    }

    public void addDay(WorkingDay day){
        days.add(day);
    }

    @NonNull
    @Override
    public String toString() {
        return "WorkingWeek{" +
                "days=" + days +
                '}';
    }
}
