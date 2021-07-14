package com.example.egeoyag;

import android.graphics.Bitmap;

public class Bitmap_pillExDTO {

    private pillExDTO dto;
    private Bitmap bitmap;

    public Bitmap_pillExDTO(pillExDTO dto, Bitmap bitmap) {
        this.dto = dto;
        this.bitmap = bitmap;
    }

    public pillExDTO getDto() {
        return dto;
    }

    public void setDto(pillExDTO dto) {
        this.dto = dto;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
