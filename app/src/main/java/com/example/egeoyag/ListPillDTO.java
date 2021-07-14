package com.example.egeoyag;

import android.graphics.Bitmap;

public class ListPillDTO {

    private String l_p_img;
    private String l_p_name;
    private String l_p_company;
    private String l_p_eff1;
    private String l_p_num;

    public String getL_p_num() {
        return l_p_num;
    }

    public void setL_p_num(String l_p_num) {
        this.l_p_num = l_p_num;
    }

    public String getL_p_img() {
        return l_p_img;
    }

    public void setL_p_img(String l_p_img) {
        this.l_p_img = l_p_img;
    }

    public String getL_p_name() {
        return l_p_name;
    }

    public void setL_p_name(String l_p_name) {
        this.l_p_name = l_p_name;
    }

    public String getL_p_company() {
        return l_p_company;
    }

    public void setL_p_company(String l_p_company) {
        this.l_p_company = l_p_company;
    }

    public String getL_p_eff1() {
        return l_p_eff1;
    }

    public void setL_p_eff1(String l_p_eff1) {
        this.l_p_eff1 = l_p_eff1;
    }

    public ListPillDTO(String l_p_img, String l_p_name, String l_p_company, String l_p_eff1, String l_p_num) {
        this.l_p_img = l_p_img;
        this.l_p_name = l_p_name;
        this.l_p_company = l_p_company;
        this.l_p_eff1 = l_p_eff1;
        this.l_p_num = l_p_num;
    }
}
