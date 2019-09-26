package com.example.arpart1.Models;

import android.net.Uri;

public class ArProduct {


    private Uri uri;
    private String text;
    private int rawModel;

    public Uri getUri() {
        return uri;
    }

    public int getRawModel() {
        return rawModel;
    }

    public void setRawModel(int rawModel) {
        this.rawModel = rawModel;
    }

    public String getText() {
        return text;
    }

    public enum ArProductType {
        THREED_MODEL,
        IMAGE_MODEL,
        TEXT_MODEL
    }

    int productId;
    private ArProductType arProductType;


    public ArProduct(int productId, ArProductType arProductType) {
        this.productId = productId;
        this.arProductType = arProductType;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public ArProductType getArProductType() {
        return arProductType;
    }

    public void setArProductType(ArProductType arProductType) {
        this.arProductType = arProductType;
    }

    public int getNewIdForModel() {
        return 0;
    }

    public void setUri(Uri uri) {

        this.uri = uri;
    }

    public void setText(String text) {


        this.text = text;
    }


}
