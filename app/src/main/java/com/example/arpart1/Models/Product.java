package com.example.arpart1.Models;


import java.io.Serializable;

public class Product implements Serializable {

    public enum productType {

        Furniture, Electronics, Fashion
    }

    String productName,
            productDescription, productDescriptionDummy = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent in tellus sed sem iaculis bibendum. ";
    int productModel, productPrice, productImage;
    productType productType;
    int bitMap;

    public String getProductDescriptionDummy() {
        return productDescriptionDummy;
    }

    public void setProductDescriptionDummy(String productDescriptionDummy) {
        this.productDescriptionDummy = productDescriptionDummy;
    }

    public int getBitMap() {
        return bitMap;
    }

    public void setBitMap(int bitMap) {
        this.bitMap = bitMap;
    }

    public Product(String productName, String productDescription, int productModel, int productPrice, int productImage, Product.productType productType) {
        this.productName = productName;
        this.productDescription = productDescriptionDummy;
        this.productModel = productModel;
        this.productPrice = productPrice;
        this.productImage = productImage;
        this.productType = productType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getProductModel() {
        return productModel;
    }

    public void setProductModel(int productModel) {
        this.productModel = productModel;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public Product.productType getProductType() {
        return productType;
    }

    public void setProductType(Product.productType productType) {
        this.productType = productType;
    }
}
