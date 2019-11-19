package com.example.arpart1.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arpart1.Models.Product;
import com.example.arpart1.Models.ProductListModel;
import com.example.arpart1.R;

import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {
    public ProductListAdapter(ArrayList<Product> productListModels) {
        this.productListModels = productListModels;
    }

    ArrayList<Product> productListModels;
    @NonNull
    @Override
    public ProductListAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_productlist,parent,false);

        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ProductViewHolder holder, int position) {
        holder.recyclerViewImage.setImageResource(productListModels.get(position).getProductImage());
        holder.ProductName.setText(productListModels.get(position).getProductName());
        holder.ProductCost.setText(""+productListModels.get(position).getProductPrice());
    }

    @Override
    public int getItemCount() {
        return productListModels.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView recyclerViewImage;
        TextView ProductName,ProductCost;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerViewImage=itemView.findViewById(R.id.productimage);
            ProductName=itemView.findViewById(R.id.product_name);
            ProductCost=itemView.findViewById(R.id.product_price);
        }
    }
}
