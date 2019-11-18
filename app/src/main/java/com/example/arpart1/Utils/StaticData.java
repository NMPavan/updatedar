package com.example.arpart1.Utils;

import android.content.Context;

import com.example.arpart1.Models.Product;
import com.example.arpart1.R;
import com.google.android.material.snackbar.Snackbar;

import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.example.arpart1.Models.AddedObject;
import com.example.arpart1.Models.ArProduct;

import java.util.ArrayList;

public class StaticData {
    //not used in main application
    public static ArrayList<AddedObject> addedObjects = new ArrayList<AddedObject>();
    public static ArrayList<ArProduct> placedObjects = new ArrayList<ArProduct>();
    public static ArrayList<Product> products = new ArrayList<Product>();
    public static int objectCount = 0;
    public static String selectedStringForModel;
    public static ArProduct arProductToPlace = null;

    public static void showSnackBar(View context, String data) {
        Snackbar.make(context, data, Snackbar.LENGTH_SHORT).show();
    }

    public static void showToast(Context context, String data) {
        Toast toast =
                Toast.makeText(context, data, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void loadDummyProducts() {

        int chairModel = R.raw.chair;
        int tableModel = R.raw.table;
        int andyModel = R.raw.andy;

        products.add(new Product("Push Back Chair", "", chairModel, 2000, R.drawable.chair, Product.productType.Furniture));
        products.add(new Product("Push Back Chair", "", chairModel, 9000, R.drawable.chair, Product.productType.Furniture));
        products.add(new Product("Push Back Chair", "", chairModel, 900, R.drawable.chair, Product.productType.Furniture));
        products.add(new Product("Push Back Chair", "", tableModel, 200, R.drawable.chair, Product.productType.Furniture));
        products.add(new Product("Push Back Chair", "", chairModel, 2000, R.drawable.chair, Product.productType.Furniture));
        products.add(new Product("Push Back Chair", "", chairModel, 2080, R.drawable.table, Product.productType.Furniture));
        products.add(new Product("Push Back Chair", "", tableModel, 4000, R.drawable.chair, Product.productType.Furniture));
        products.add(new Product("Push Back Chair", "", chairModel, 1000, R.drawable.chair, Product.productType.Furniture));
        products.add(new Product("Push Back Chair", "", chairModel, 900, R.drawable.chair, Product.productType.Furniture));


        products.add(new Product("Laptop 15 inch", "", andyModel, 572, R.drawable.andy, Product.productType.Electronics));
        products.add(new Product("Laptop 15 inch", "", andyModel, 522, R.drawable.andy, Product.productType.Electronics));
        products.add(new Product("Laptop 15 inch", "", andyModel, 522, R.drawable.andy, Product.productType.Electronics));
        products.add(new Product("Laptop 15 inch", "", andyModel, 582, R.drawable.andy, Product.productType.Electronics));
        products.add(new Product("Laptop 15 inch", "", andyModel, 722, R.drawable.andy, Product.productType.Electronics));
        products.add(new Product("Laptop 15 inch", "", andyModel, 522, R.drawable.andy, Product.productType.Electronics));
        products.add(new Product("Laptop 15 inch", "", andyModel, 22, R.drawable.andy, Product.productType.Electronics));

        products.add(new Product("Check shirt", "", andyModel, 572, R.drawable.table, Product.productType.Fashion));
        products.add(new Product("Check shirt", "", andyModel, 522, R.drawable.andy, Product.productType.Fashion));
        products.add(new Product("Check shirt", "", andyModel, 522, R.drawable.andy, Product.productType.Fashion));
        products.add(new Product("Check shirt", "", andyModel, 582, R.drawable.table, Product.productType.Fashion));
        products.add(new Product("Check shirt", "", andyModel, 722, R.drawable.andy, Product.productType.Fashion));
        products.add(new Product("Check shirt", "", andyModel, 722, R.drawable.andy, Product.productType.Fashion));
        products.add(new Product("Check shirt", "", andyModel, 722, R.drawable.table, Product.productType.Fashion));
        products.add(new Product("Check shirt", "", andyModel, 522, R.drawable.andy, Product.productType.Fashion));
        products.add(new Product("Check shirt", "", andyModel, 22, R.drawable.andy, Product.productType.Fashion));

    }
}
