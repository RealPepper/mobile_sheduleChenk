package com.example.schedulechenk.models;

import java.util.List;

public class ScheduleModel{
    private String week;
    private String day;
    private List<PairModel> pairModels;

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<PairModel> getPairModels() {
        return pairModels;
    }

    public void setPairModels(List<PairModel> pairModels) {
        this.pairModels = pairModels;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == this)return true;
        ScheduleModel scheduleModel = (ScheduleModel) obj;
        return scheduleModel.week == week &&
               scheduleModel.day == day &&
               scheduleModel.pairModels == pairModels;
    }
}
