package com.example.arpart1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arpart1.Models.Product;
import com.example.arpart1.R;
import com.example.arpart1.databinding.LayoutStoreMainListBinding;

import java.util.ArrayList;

public class StoreMainListAdapter extends RecyclerView.Adapter<StoreMainListAdapter.StoreMainPreviewViewHolder> {
    private ArrayList<Product> dataList;
    private Context context;

    int index = -1;

    private OnItemClickListener itemClickListener;

    public StoreMainListAdapter(ArrayList<Product> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        itemClickListener = listener;
    }

    @NonNull
    @Override
    public StoreMainPreviewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View view = LayoutInflater.from(context).inflate(R.layout.layout_store_main_list, viewGroup, false);
        LayoutStoreMainListBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.layout_store_main_list, viewGroup, false);
        return new StoreMainPreviewViewHolder(binding, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreMainPreviewViewHolder storeMainPreviewViewHolder, int i) {
        storeMainPreviewViewHolder.binding.productImage.setImageResource(dataList.get(i).getProductImage());
//        storeMainPreviewViewHolder.binding.productImage.setImageDrawable(context.getDrawable(dataList.get(i).getProductImage()));
        storeMainPreviewViewHolder.binding.productName.setText(dataList.get(i).getProductName());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public class StoreMainPreviewViewHolder extends RecyclerView.ViewHolder {

        int pos;

        LayoutStoreMainListBinding binding;


        public StoreMainPreviewViewHolder(@NonNull LayoutStoreMainListBinding binding, final OnItemClickListener listener) {
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
