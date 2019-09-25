package com.example.arpart1.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arpart1.Models.Images;
import com.example.arpart1.R;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    List<Images> dataList;
    Context context;

    public ImageAdapter(List<Images> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom_image_layout,viewGroup,false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder imageViewHolder, int i) {
        imageViewHolder.recyclerImage.setImageResource(dataList.get(i).getBitmap());
        imageViewHolder.recyclerText.setText(dataList.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView recyclerImage;
        TextView  recyclerText;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerImage=itemView.findViewById(R.id.recyclerImage);
            recyclerText=itemView.findViewById(R.id.recyclerText);

        }
    }
}
