package com.example.arpart1.Utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
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
    public static int objectCount=0;
    public  static  String selectedStringForModel;
    public static ArProduct arProductToPlace=null;

    public static void showSnackBar(View context, String data) {
        Snackbar.make(context, data, Snackbar.LENGTH_SHORT).show();
    } public static void showToast(Context context, String data) {
        Toast toast =
                Toast.makeText(context, data, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();    }
}
