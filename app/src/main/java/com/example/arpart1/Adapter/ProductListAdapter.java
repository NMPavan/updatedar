package com.example.arpart1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arpart1.Models.Product;
import com.example.arpart1.Models.ProductListModel;
import com.example.arpart1.ProductDescriptionActivity;
import com.example.arpart1.R;

import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {
    private Context context;
    private ProductListAdapter.OnItemClickListener itemClickListener;
    int index = -1;

    public ProductListAdapter(ArrayList<Product> productListModels) {
        this.productListModels = productListModels;

        this.context=context;
    }
    public  void setOnItemListener(OnItemClickListener listener){
        itemClickListener = listener;
    }

    ArrayList<Product> productListModels;

    @NonNull
    @Override
    public ProductListAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_productlist,parent,false);

        return new ProductViewHolder(v,itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ProductViewHolder holder, int position) {
        holder.recyclerViewImage.setImageResource(productListModels.get(position).getProductImage());
        holder.ProductName.setText(productListModels.get(position).getProductName());
        holder.ProductCost.setText(""+productListModels.get(position).getProductPrice());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ProductDescriptionActivity.class);
                intent.putExtra("image",productListModels.get(position).getProductImage());
                intent.putExtra("price",productListModels.get(position).getProductPrice());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productListModels.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView recyclerViewImage;
        TextView ProductName,ProductCost;
        int pos;
        LinearLayout linearLayout;
        public ProductViewHolder(@NonNull View itemView,final OnItemClickListener listener ) {
            super(itemView);
            recyclerViewImage=itemView.findViewById(R.id.productimage);
            ProductName=itemView.findViewById(R.id.product_name);
            ProductCost=itemView.findViewById(R.id.product_price);
            linearLayout=itemView.findViewById(R.id.productlistlayout);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
