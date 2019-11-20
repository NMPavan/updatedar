package com.example.arpart1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arpart1.Models.Product;
import com.example.arpart1.ProductDescriptionActivity;
import com.example.arpart1.ProductListActivity;
import com.example.arpart1.R;
import com.example.arpart1.StoreMainActivity;
import com.example.arpart1.Utils.StaticData;
import com.example.arpart1.databinding.LayoutStoreMainViewBinding;

import java.io.Serializable;
import java.util.ArrayList;


public class StoreMainPreviewAdapter extends RecyclerView.Adapter<StoreMainPreviewAdapter.ViewHolder> {
    private Product.productType[] dataList;
    private Context context;

    int index = -1;

    private OnItemClickListener itemClickListener;

    public StoreMainPreviewAdapter(Product.productType[] dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        itemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View view = LayoutInflater.from(context).inflate(R.layout.layout_store_main_list, viewGroup, false);
        LayoutStoreMainViewBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.layout_store_main_view, viewGroup, false);

        return new ViewHolder(binding, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.binding.storeName.setText(dataList[i].toString());

        ArrayList<Product> products = new ArrayList<>();

        for (Product product :
                StaticData.products) {
            if ( product.getProductType().equals(dataList[i]))
                products.add(product);

        }

        viewHolder.binding.cardMore1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductListActivity.class);
                /*Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST",(Serializable)products);
                intent.putExtra("bundle",args);*/
                intent.putExtra("data", (ArrayList<Product>) products);
                  context.startActivity(intent);


//dataList[i] - productType we selected
                // products -arraylist<product>
            }
        });



        StoreMainListAdapter storeMainListAdapter = new StoreMainListAdapter(products, context);
        viewHolder.binding.recyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        viewHolder.binding.recyclerview.setAdapter(storeMainListAdapter);
        storeMainListAdapter.setOnItemClickListener(position -> {


        });
    }

    @Override
    public int getItemCount() {
        return dataList.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        int pos;

        LayoutStoreMainViewBinding binding;


        public ViewHolder(@NonNull LayoutStoreMainViewBinding binding, final OnItemClickListener listener) {
            super(binding.getRoot());
            this.binding=binding;
            binding.parentlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pos = getAdapterPosition();
                    index = pos;

                    if (pos != RecyclerView.NO_POSITION) {
                        listener.OnClick(pos);
                    }

                    notifyDataSetChanged();

                }
            });

        }
    }

    public interface OnItemClickListener {

        void OnClick(int position);
    }

}
