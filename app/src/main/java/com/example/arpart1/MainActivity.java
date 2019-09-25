package com.example.arpart1;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.arpart1.AlerDialogs.ImageAlertDialog;
import com.example.arpart1.Utils.StaticData;
import com.example.arpart1.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ImageAlertDialog imageAlertDialog=new ImageAlertDialog(getApplicationContext());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setListeners();


    }

    private void setListeners() {

        binding.Cl3dModel.setOnClickListener(view -> {

        });
        binding.ClImageModel.setOnClickListener(view -> {

            imageAlertDialog.createAlertDialog();

        });
        binding.ClTextModel.setOnClickListener(view -> {

        });



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode== ImageAlertDialog.PICK_IMAGE && resultCode==RESULT_OK && data!=null)
        {
            imageAlertDialog.handleIntentResponse(data);
        }
        if (requestCode==ImageAlertDialog.PICK_IMAGE && resultCode==RESULT_CANCELED)
        {
            StaticData.showSnackBar(binding.root,"User cancelled the image");
        }

    }
}
