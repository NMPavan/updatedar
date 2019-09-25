package com.example.arpart1.AlerDialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.Toast;

import com.example.arpart1.Adapter.ImageAdapter;
import com.example.arpart1.MainActivity;
import com.example.arpart1.Models.Images;
import com.example.arpart1.R;


import com.example.arpart1.databinding.ImageAlertDialogBinding;

import java.io.IOException;
import java.util.ArrayList;


public class ImageAlertDialog {
    Context context;
    public  static  int PICK_IMAGE=1;
    ImageAlertDialogBinding binding;

    ImageAdapter imageAdapter;
    Bitmap imageBitmap;
    ArrayList<Images> dataList=new ArrayList<>();

    public ImageAlertDialog(Context context) {
        this.context = context;
    }

    public void createAlertDialog()
    {



        AlertDialog.Builder builder= null;
        try {
            builder = new AlertDialog.Builder(context);
        } catch (Exception e) {
            e.printStackTrace();

            Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
        binding=DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.image_alert_dialog,null,false);

        builder.setView(binding.getRoot());

        binding.selctImageTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImageFromGallery();
            }
        });

        binding.selectImageRecycler.setHasFixedSize(true);
        binding.selectImageRecycler.setLayoutManager(new GridLayoutManager(context,2));

        dataList.clear();

        addData();

        imageAdapter=new ImageAdapter(dataList,context);

        binding.selectImageRecycler.setAdapter(imageAdapter);

        final AlertDialog dialog=builder.create();

        binding.cancelSelectedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        binding.okSelectedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        dialog.show();




    }



    private void addData()
    {
        dataList.add(new Images(R.drawable.chair_thumb,"Chair"));
        dataList.add(new Images(R.drawable.images,"Table"));
        dataList.add(new Images(R.drawable.fox,"Fox"));
    }

    public void handleIntentResponse(Intent data)
    {
        if (data.getExtras()!=null)
        {
            Bitmap imageBitmap=data.getExtras().getParcelable("data");
            Toast.makeText(context, "Bitmap printed", Toast.LENGTH_SHORT).show();

        }

        if (data.getData()!=null)
        {
            Uri uri = data.getData();

            Toast.makeText(context, uri.toString(), Toast.LENGTH_SHORT).show();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));


                binding.selectedImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void selectImageFromGallery()
    {
        Intent galleryIntent=new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        ((MainActivity)context).startActivityForResult(galleryIntent,PICK_IMAGE);

    }






}
