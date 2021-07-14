package com.example.egeoyag;

import android.graphics.Bitmap;

public class Bitmap_ListPillDTO {

    private ListPillDTO dto;
    private Bitmap bitmap;

    public Bitmap_ListPillDTO(ListPillDTO dto, Bitmap bitmap) {
        this.dto = dto;
        this.bitmap = bitmap;
    }

    public ListPillDTO getDto() {
        return dto;
    }

    public void setDto(ListPillDTO dto) {
        this.dto = dto;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
