package com.example.schedulechenk.models;

import java.util.List;

public class ScheduleModel {
    private String week;
    private String day;
    private List<PairModel> pairModels;

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        week = week;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        day = day;
    }

    public List<PairModel> getPairModels() {
        return pairModels;
    }

    public void setPairModels(List<PairModel> pairModels) {
        this.pairModels = pairModels;
    }
}
