package com.example.arpart1.Models;

import android.net.Uri;

import com.example.arpart1.R;
import com.example.arpart1.Renderable.ModelRenderable3D;
import com.example.arpart1.Renderable.ViewRenderableImage;
import com.example.arpart1.Renderable.ViewRenderableText;
import com.example.arpart1.Utils.StaticData;

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

    public Boolean getIfModelIsTable() {
        if (this.arProductType == ArProductType.THREED_MODEL && this.getRawModel() == R.raw.table)
            return true;
        else
            return false;

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

    public double getThresholdDistance() {
        float thresholdDistance = 0.5f;

        switch (this.arProductType) {
            case IMAGE_MODEL:
                thresholdDistance = 1.25f;

                break;
            case THREED_MODEL:
                thresholdDistance = 1.5f;
                break;
            case TEXT_MODEL:

                break;
        }

        return thresholdDistance;
    }


}
