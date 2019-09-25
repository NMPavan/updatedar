package com.example.arpart1;

import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.arpart1.databinding.ActivityMainBinding;
import com.google.ar.sceneform.ux.ArFragment;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ArFragment arFragment;
    int arFragmentId = R.id.ux_ar_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setListeners();
        setArFragment();

    }

    private void setArFragment() {
        if (findViewById(arFragmentId) != null) {
            arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(arFragmentId);
        }
//
//        if(arFragment!=null)
//            ar
    }

    private void setListeners() {

        binding.Cl3dModel.setOnClickListener(view -> {

        });
        binding.ClImageModel.setOnClickListener(view -> {

        });
        binding.ClTextModel.setOnClickListener(view -> {

        });

    }
}
