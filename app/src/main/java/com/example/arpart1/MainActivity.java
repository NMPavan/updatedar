package com.example.arpart1;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.arpart1.AlerDialogs.ImageAlertDialog;
import com.example.arpart1.AlerDialogs.TextAlertDialog;
import com.example.arpart1.Utils.StaticData;
import com.example.arpart1.databinding.ActivityMainBinding;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ImageAlertDialog imageAlertDialog;
    TextAlertDialog textAlertDialog;

    ArFragment arFragment;
    int arFragmentId = R.id.ux_ar_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        imageAlertDialog = new ImageAlertDialog(this);
        textAlertDialog = new TextAlertDialog(this);
        setListeners();
        setArFragment();

    }
    private void setArFragment() {
        if (findViewById(arFragmentId) != null) {
            arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(arFragmentId);
        }

        if(arFragment!=null)
            arFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
                @Override
                public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
                    
                }
            });
    }

    private void setListeners() {

        binding.Cl3dModel.setOnClickListener(view -> {

        });
        binding.ClImageModel.setOnClickListener(view -> {

            imageAlertDialog.createAlertDialog();

        });
        binding.ClTextModel.setOnClickListener(view -> {
            textAlertDialog.createAlertDialog();
        });



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode== ImageAlertDialog.PICK_IMAGE && resultCode==RESULT_OK && data!=null)
        {
            imageAlertDialog.handleIntentResponse(data);
            StaticData.showSnackBar(binding.root,"User Selected the image");
        }
        if (requestCode==ImageAlertDialog.PICK_IMAGE && resultCode==RESULT_CANCELED)
        {
            StaticData.showSnackBar(binding.root,"User cancelled the image");
        }

    }
}
