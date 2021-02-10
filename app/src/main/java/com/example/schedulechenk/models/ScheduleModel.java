package com.example.schedulechenk.models;

import java.util.List;

public class ScheduleModel {
    private String Week;
    private String Day;
    private List<PairModel> pairModels;

    public String getWeek() {
        return Week;
    }

    public void setWeek(String week) {
        Week = week;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public List<PairModel> getPairModels() {
        return pairModels;
    }

    public void setPairModels(List<PairModel> pairModels) {
        this.pairModels = pairModels;
    }
}
