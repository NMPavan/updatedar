package com.example.arpart1.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.arpart1.Models.ImageDetailModel;
import com.example.arpart1.Models.Images;
import com.example.arpart1.R;

import java.util.ArrayList;

public class ImageModel extends RecyclerView.Adapter<ImageModel.ImageModelViewHolder> {
      //List<Imagesmodels> dataList;
        Context context;
    private ArrayList<ImageDetailModel> modelImage;

    public ImageModel(ArrayList<ImageDetailModel> modelImage, Context context) {
            //this.dataList = dataList;
            this.context = context;
            this.modelImage=modelImage;
        }



    @NonNull
        @Override
        public ImageModel.ImageModelViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view= LayoutInflater.from(context).inflate(R.layout.model_detail,viewGroup,false);
            return new ImageModel.ImageModelViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ImageModelViewHolder imageViewHolder, int i) {
            imageViewHolder.recyclerImage.setImageResource(modelImage.get(i).getAndroid_image_url());
        }

        @Override
        public int getItemCount() {
            return modelImage.size();
        }

        public class ImageModelViewHolder extends RecyclerView.ViewHolder {
            ImageView recyclerImage;

            public ImageModelViewHolder(@NonNull View itemView) {
                super(itemView);
                recyclerImage=itemView.findViewById(R.id.recyclerImagemodel);

            }
        }
    }
