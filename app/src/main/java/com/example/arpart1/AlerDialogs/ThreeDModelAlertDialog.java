package com.example.arpart1.AlerDialogs;

import android.app.AlertDialog;
import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.example.arpart1.Adapter.ThreeModelAdapter;
import com.example.arpart1.Models.ArProduct;
import com.example.arpart1.Models.ImageDetailModel;
import com.example.arpart1.R;
import com.example.arpart1.Utils.SelectorChooseListener;
import com.example.arpart1.databinding.ModelDisplayBinding;

import java.util.ArrayList;

import static com.example.arpart1.Utils.StaticData.arProductToPlace;

public class ThreeDModelAlertDialog {
    Context context;
    private SelectorChooseListener selectorChooseListener;
    ThreeModelAdapter threeModelAdapter;
    AlertDialog dialog;


    ModelDisplayBinding binding;
    ArrayList<ImageDetailModel> modelImage = new ArrayList<>();


    public ThreeDModelAlertDialog(Context context, SelectorChooseListener selectorChooseListener) {
        this.context = context;
        this.selectorChooseListener = selectorChooseListener;
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
            public void OnClick(int pos, int rawModelId) {
                arProductToPlace = new ArProduct(ArProduct.ArProductType.THREED_MODEL);
                arProductToPlace.setRawModel(rawModelId);
                selectorChooseListener.SelectorChooseListener(ArProduct.ArProductType.THREED_MODEL);


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
        modelImage.add(new ImageDetailModel(R.drawable.andy,R.raw.andy));
        modelImage.add(new ImageDetailModel(R.drawable.table,R.raw.table));
        modelImage.add(new ImageDetailModel(R.drawable.chair,R.raw.chair));

    }


}
