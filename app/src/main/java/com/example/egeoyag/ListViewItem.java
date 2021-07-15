package com.example.egeoyag;

public class ListViewItem {

    private String img;
    private String name;
    private String symptom1;
    private String ymd;

    public ListViewItem(String img, String name, String symptom1, String ymd) {
        this.img = img;
        this.name = name;
        this.symptom1 = symptom1;
        this.ymd = ymd;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymptom1() {
        return symptom1;
    }

    public void setSymptom1(String symptom1) {
        this.symptom1 = symptom1;
    }


    public String getYmd() {
        return ymd;
    }

    public void setYmd(String ymd) {
        this.ymd = ymd;
    }
}
