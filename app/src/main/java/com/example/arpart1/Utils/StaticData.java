package com.example.arpart1.Utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.example.arpart1.Models.AddedObject;

import java.util.ArrayList;

public class StaticData {
    public static ArrayList<AddedObject> addedObjects=new ArrayList<AddedObject>();

    public static void showSnackBar(View context, String data)
    {
        Snackbar.make(context,data,Snackbar.LENGTH_SHORT).show();
    }
}
