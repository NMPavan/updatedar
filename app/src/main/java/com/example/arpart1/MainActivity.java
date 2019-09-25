package com.example.arpart1;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.arpart1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

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

        });
        binding.ClTextModel.setOnClickListener(view -> {

        });

    }
}
