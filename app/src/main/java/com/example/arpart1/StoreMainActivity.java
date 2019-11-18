package com.example.arpart1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.example.arpart1.Adapter.StoreMainListAdapter;
import com.example.arpart1.Adapter.StoreMainPreviewAdapter;
import com.example.arpart1.Models.Product;
import com.example.arpart1.Utils.StaticData;
import com.example.arpart1.databinding.ActivityStoreMainBinding;

import java.util.ArrayList;
import java.util.Collections;

public class StoreMainActivity extends AppCompatActivity {

    private  final String TAG = "store";
    ActivityStoreMainBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_store_main);


        initialization();
    }

    private void initialization(){
        StaticData.loadDummyProducts();

        Log.d(TAG, "initialization: datalist length"+Product.productType.values());

        StoreMainPreviewAdapter adapter =new StoreMainPreviewAdapter(Product.productType.values(),this);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        binding.recyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(position -> {


        });



    }
}
