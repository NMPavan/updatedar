package com.example.arpart1.AlerDialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.arpart1.Adapter.ImageAdapter;
import com.example.arpart1.MainActivity;
import com.example.arpart1.Models.ArProduct;
import com.example.arpart1.Models.Images;
import com.example.arpart1.R;
import com.example.arpart1.Utils.StaticData;
import com.example.arpart1.databinding.ImageAlertDialogBinding;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.arpart1.Utils.StaticData.arProductToPlace;


public class ImageAlertDialog {
    public static int PICK_IMAGE = 1;
    Context context;
    ImageAlertDialogBinding binding;
    AlertDialog dialog;

    ImageAdapter imageAdapter;
    Bitmap imageBitmap;
    ArrayList<Images> dataList = new ArrayList<>();

    public ImageAlertDialog(Context context) {

        this.context = context;
    }

    public void createAlertDialog() {
        AlertDialog.Builder builder = null;
        try {
            builder = new AlertDialog.Builder(context);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.image_alert_dialog, null, false);
        builder.setView(binding.getRoot());
        dialog = builder.create();

        setRecycler();


        setListener();


        dialog.show();


    }

    private void setRecycler() {
        binding.selectImageRecycler.setHasFixedSize(true);
        binding.selectImageRecycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

        dataList.clear();

        addData();

        imageAdapter = new ImageAdapter(dataList, context);

        binding.selectImageRecycler.setAdapter(imageAdapter);
    }

    private void setListener() {

        imageAdapter.setOnItemClickListener(new ImageAdapter.OnItemClickListener() {
            @Override
            public void OnClick(int pos, int image, String text) {
                StaticData.selectedFinalImage = image;
                StaticData.selectedItemName = text;

                binding.selectedImage.setImageResource(StaticData.selectedFinalImage);
            }
        });

        binding.cancelSelectedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        binding.okSelectedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                arProductToPlace=new ArProduct(0,ArProduct.ArProductType.IMAGE_MODEL);
//                arProductToPlace.setUri()

                dialog.dismiss();
            }
        });

        binding.selctImageTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImageFromGallery();
            }
        });
    }


    private void addData() {
        dataList.add(new Images(R.drawable.chair_thumb, "Chair"));
        dataList.add(new Images(R.drawable.images, "Table"));
        dataList.add(new Images(R.drawable.fox, "Fox"));
    }

    public void handleIntentResponse(Intent data) {


        if (data.getData() != null) {
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

    private void selectImageFromGallery() {
        Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        ((MainActivity) context).startActivityForResult(galleryIntent, PICK_IMAGE);

    }


}
