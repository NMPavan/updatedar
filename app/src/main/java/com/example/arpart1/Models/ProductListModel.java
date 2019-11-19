package com.example.arpart1.Models;

public class ProductListModel {
    int bitMap;
    String ProductName, ProductCost;

    public ProductListModel(int bitMap, String productName, String productCost) {
        this.bitMap = bitMap;
        ProductName = productName;
        ProductCost = productCost;
    }

    public int getBitMap() {
        return bitMap;
    }

    public void setBitMap(int bitMap) {
        this.bitMap = bitMap;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductCost() {
        return ProductCost;
    }

    public void setProductCost(String productCost) {
        ProductCost = productCost;
    }
}

