package com.example.arpart1.AlerDialogs;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.annotation.AnyRes;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.arpart1.Adapter.ImageAdapter;
import com.example.arpart1.MainActivity;
import com.example.arpart1.Models.ArProduct;
import com.example.arpart1.Models.Images;
import com.example.arpart1.R;
import com.example.arpart1.Utils.SelectorChooseListener;
import com.example.arpart1.databinding.ImageAlertDialogBinding;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.arpart1.Utils.StaticData.arProductToPlace;


public class ImageAlertDialog {
    public static int PICK_IMAGE = 1;
    private Context context;
    private SelectorChooseListener selectorChooseListener;
    private ImageAlertDialogBinding binding;
    private AlertDialog dialog;

    private ImageAdapter imageAdapter;
    Uri uri;
    private ArrayList<Images> dataList = new ArrayList<>();

    public ImageAlertDialog(Context context, SelectorChooseListener selectorChooseListener) {

        this.context = context;
        this.selectorChooseListener = selectorChooseListener;
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

                uri = getUriToDrawable(image);
                binding.selectedImage.setImageResource(image);
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
                if (uri != null) {
                    arProductToPlace = new ArProduct( ArProduct.ArProductType.IMAGE_MODEL);
                    arProductToPlace.setUri(uri);
                    selectorChooseListener.SelectorChooseListener(ArProduct.ArProductType.IMAGE_MODEL);
                }

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


    private Uri getUriToDrawable(@AnyRes int drawableId) {
        Uri parse = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + context.getResources().getResourcePackageName(drawableId)
                + '/' + context.getResources().getResourceTypeName(drawableId)
                + '/' + context.getResources().getResourceEntryName(drawableId));
        return parse;
    }

    private void addData() {
        dataList.add(new Images(R.drawable.chair, "Chair"));
        dataList.add(new Images(R.drawable.table, "Table"));
        dataList.add(new Images(R.drawable.fox, "Fox"));
    }

    public void handleIntentResponse(Intent data) {


        if (data.getData() != null) {
            uri = data.getData();

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
