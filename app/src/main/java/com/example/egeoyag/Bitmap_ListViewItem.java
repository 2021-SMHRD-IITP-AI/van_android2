package com.example.egeoyag;

import android.graphics.Bitmap;

public class Bitmap_ListViewItem {

    private ListViewItem dto;
    private Bitmap bitmap;

    public Bitmap_ListViewItem(ListViewItem dto, Bitmap bitmap){
        this.dto = dto;
        this.bitmap = bitmap;
    }

    public ListViewItem getDto() {
        return dto;
    }

    public void setDto(ListViewItem dto) {
        this.dto = dto;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
