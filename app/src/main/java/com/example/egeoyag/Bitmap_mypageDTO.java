package com.example.egeoyag;

import android.graphics.Bitmap;

public class Bitmap_mypageDTO {

    private mypagedto dto;
    private Bitmap bitmap;

    public Bitmap_mypageDTO(mypagedto dto, Bitmap bitmap) {
        this.dto = dto;
        this.bitmap = bitmap;
    }

    public mypagedto getDto() {
        return dto;
    }

    public void setDto(mypagedto dto) {
        this.dto = dto;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
