package com.example.arpart1.Models;

public class ImageDetailModel {
     int modelImage;
    int modelLocation;

    public int getModel_location() {
        return modelLocation;
    }

    public void setModel_location(int model_location) {
        this.modelLocation = model_location;
    }



    public ImageDetailModel(int android_image_url,int model_location) {
        this.modelImage = android_image_url;
        this.modelLocation=model_location;
    }

    public int getAndroid_image_url() {
        return modelImage;
    }

    public void setAndroid_image_url(int android_image_url) {
        this.modelImage = android_image_url;
    }
}
