package com.example.arpart1.AlerDialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.example.arpart1.Adapter.ImageAdapter;
import com.example.arpart1.Adapter.ImageModel;
import com.example.arpart1.Models.ImageDetailModel;
import com.example.arpart1.Models.Images;
import com.example.arpart1.R;
import com.example.arpart1.databinding.ModelDisplayBinding;

import java.util.ArrayList;

public class ThreeDModelAlertDialog {
    Context context;
    ImageModel imageModel;
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


        final AlertDialog dialog = builder.create();

        setRecycler();

        setListener();


        dialog.show();
    }

    private void setListener() {



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
        binding.ModelShow.setLayoutManager(new GridLayoutManager(context, 2));

        modelImage.clear();
        addData();


        imageModel = new ImageModel(modelImage, context);

        binding.ModelShow.setAdapter(imageModel);
    }

    private void addData() {
        modelImage.add(new ImageDetailModel(R.drawable.chair_thumb));
        modelImage.add(new ImageDetailModel(R.drawable.images));
        modelImage.add(new ImageDetailModel(R.drawable.fox));

    }


}
