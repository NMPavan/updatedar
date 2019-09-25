package com.example.arpart1.Models;

import android.graphics.Bitmap;
import android.media.Image;

public class Images
{
    int bitmap;
    String name;

    public Images(){}

    public Images(int bitmap, String name) {
        this.bitmap = bitmap;
        this.name = name;
    }

    public int getBitmap() {
        return bitmap;
    }

    public void setBitmap(int bitmap) {
        this.bitmap = bitmap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
