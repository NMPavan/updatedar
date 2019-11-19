package com.example.arpart1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.arpart1.Adapter.ProductListAdapter;
import com.example.arpart1.Models.Product;
import com.example.arpart1.Models.ProductListModel;
import com.example.arpart1.Utils.StaticData;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {

    RecyclerView product_list;
    ProductListAdapter productListAdapter;
    ArrayList<Product> product;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        product_list = findViewById(R.id.recycler_productlist);

        product_list.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

        productListAdapter = new ProductListAdapter( product);
        product_list.setAdapter(productListAdapter);



        getIncomingIntent();


    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("producttype") && getIntent().hasExtra("product")) {

            String productType = getIntent().getStringExtra("producttype");
            product = ((ArrayList<Product>)getIntent().getSerializableExtra("product"));

            productListAdapter.notifyDataSetChanged();

        }
    }



}