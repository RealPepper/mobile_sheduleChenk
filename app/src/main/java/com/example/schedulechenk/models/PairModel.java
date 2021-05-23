package com.example.schedulechenk.models;

public class PairModel {
    private String pair;
    private String startTime;
    private String endTime;
    private String isCancel;
    private String educator;
    private String discipline;
    private String cabinet;

    public String getPair() {
        return pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(String isCancel) {
        this.isCancel = isCancel;
    }

    public String getEducator() {
        return educator;
    }

    public void setEducator(String educator) {
        this.educator = educator;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getCabinet() {
        return cabinet;
    }

    public void setCabinet(String cabinet) {
        this.cabinet = cabinet;
    }

    @Override
    public boolean equals(Object obj){ //РЕАЛИЗУЕМ МЕТОД СРАВНЕНИЯ
        /*if(this == obj) return true;

        if(!(obj instanceof PairModel))
            return false;

        PairModel objPairModel = (PairModel) obj;
        if(this.getPair() == objPairModel.getPair() &&
           this.getStartTime() == objPairModel.getStartTime() &&
           this.getEndTime() == objPairModel.getEndTime() &&
           this.getIsCancel() == objPairModel.getIsCancel() &&
           this.getEducator() == objPairModel.getEducator() &&
           this.getDiscipline() == objPairModel.getDiscipline() &&
           this.getCabinet() == objPairModel.getCabinet())
            return true;
        else
            return false;*/





        if(obj == this) return true; //ссылки идентичные, сравнение не требуется
        PairModel pairModel = (PairModel) obj; //я сделаю без сравнения типов
        return pairModel.pair.equals(pair)  &&
                pairModel.startTime.equals(startTime)  &&
                pairModel.endTime.equals(endTime)  &&
                pairModel.isCancel.equals(isCancel) &&
                pairModel.educator.equals(educator) &&
                pairModel.discipline.equals(discipline) &&
                pairModel.cabinet.equals(cabinet);
    }

}
