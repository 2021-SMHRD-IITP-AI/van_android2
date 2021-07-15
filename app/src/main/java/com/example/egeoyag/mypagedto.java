package com.example.egeoyag;

public class mypagedto {

    private String img2;
    private String name2;
    private String symptom2;
    private String ymd2;

    public mypagedto(String img2, String name2, String symptom2, String ymd2) {
        this.img2 = img2;
        this.name2 = name2;
        this.symptom2 = symptom2;
        this.ymd2 = ymd2;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getSymptom2() {
        return symptom2;
    }

    public void setSymptom2(String symptom2) {
        this.symptom2 = symptom2;
    }

    public String getYmd2() {
        return ymd2;
    }

    public void setYmd2(String ymd2) {
        this.ymd2 = ymd2;
    }
}
