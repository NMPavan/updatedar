package com.example.arpart1.AlerDialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.example.arpart1.Adapter.ThreeModelAdapter;
import com.example.arpart1.Models.ImageDetailModel;
import com.example.arpart1.R;
import com.example.arpart1.databinding.ModelDisplayBinding;

import java.util.ArrayList;

public class ThreeDModelAlertDialog {
    Context context;
    ThreeModelAdapter threeModelAdapter;
    AlertDialog dialog;


    ModelDisplayBinding binding;
    ArrayList<ImageDetailModel> modelImage = new ArrayList<>();


    public ThreeDModelAlertDialog(Context context) {
        this.context = context;
    }

    public void createAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.model_display, null, false);
        builder.setView(binding.getRoot());


       dialog = builder.create();

        setRecycler();

        setListener();


        dialog.show();
    }

    private void setListener() {

        threeModelAdapter.setOnItemClickListener(new ThreeModelAdapter.OnItemClickListener() {
            @Override
            public void OnClick(int pos, int image) {

            }
        });



        binding.cancelSelectedImagemodel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        binding.okSelectedImagemodel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }

    private void setRecycler() {
        binding.ModelShow.setHasFixedSize(true);
        binding.ModelShow.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));


        modelImage.clear();
        addData();


        threeModelAdapter = new ThreeModelAdapter(modelImage, context);

        binding.ModelShow.setAdapter(threeModelAdapter);
    }

    private void addData() {
        modelImage.add(new ImageDetailModel(R.drawable.chair_thumb,R.raw.chair));
        modelImage.add(new ImageDetailModel(R.drawable.images,R.raw.andy));
        modelImage.add(new ImageDetailModel(R.drawable.fox,R.raw.table));

    }


}
