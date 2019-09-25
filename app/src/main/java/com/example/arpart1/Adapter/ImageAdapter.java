package com.example.arpart1.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.arpart1.Models.Images;
import com.example.arpart1.R;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    List<Images> dataList;
    Context context;

    int index = -1;

    private OnItemClickListener itemClickListener;

    public ImageAdapter(List<Images> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        itemClickListener = listener;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_image_layout, viewGroup, false);
        return new ImageViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder imageViewHolder, int i) {
        imageViewHolder.recyclerImage.setImageResource(dataList.get(i).getBitmap());
        imageViewHolder.recyclerText.setText(dataList.get(i).getName());

        if (index == i) {
            imageViewHolder.imageLayout.setBackgroundColor(0xFFE8F0B0);
        } else {
            imageViewHolder.imageLayout.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public interface OnItemClickListener {


        void OnClick(int pos, int image, String text);
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView recyclerImage;
        TextView recyclerText;
        CardView imageLayout;
        int pos;

        public ImageViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            recyclerImage = itemView.findViewById(R.id.recyclerImage);
            recyclerText = itemView.findViewById(R.id.recyclerText);
            imageLayout = itemView.findViewById(R.id.linearLayoutImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pos = getAdapterPosition();
                    index = pos;
                    int image = dataList.get(pos).getBitmap();
                    String text = dataList.get(pos).getName();
                    if (pos != RecyclerView.NO_POSITION) {
                        listener.OnClick(pos, image, text);
                    }

                    notifyDataSetChanged();

                }
            });

        }
    }
}
