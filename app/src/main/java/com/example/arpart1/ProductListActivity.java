package com.example.arpart1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.arpart1.Adapter.ProductListAdapter;
import com.example.arpart1.Models.Product;
import com.example.arpart1.Models.ProductListModel;
import com.example.arpart1.Utils.StaticData;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {

    RecyclerView product_list;
    ProductListAdapter productListAdapter;
    ArrayList<Product> products;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        products=new ArrayList<>();

        products=(ArrayList<Product>)getIntent().getSerializableExtra("data");

        for (Product product:products)
            Log.d("Clicked",""+product.getProductPrice());
        

        product_list = findViewById(R.id.recycler_productlist);

        product_list.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));


        productListAdapter=new ProductListAdapter(products,this);

        product_list.setAdapter(productListAdapter);






    }





}