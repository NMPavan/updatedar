package com.example.arpart1.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.arpart1.Models.ImageDetailModel;
import com.example.arpart1.R;

import java.util.ArrayList;

public class ThreeModelAdapter extends RecyclerView.Adapter<ThreeModelAdapter.ImageModelViewHolder> {
    //List<Imagesmodels> dataList;
    Context context;
    int index = -1;
    private ArrayList<ImageDetailModel> imageDetailModel;
    private ThreeModelAdapter.OnItemClickListener itemClickListener;


    public ThreeModelAdapter(ArrayList<ImageDetailModel> modelImage, Context context) {
        //this.dataList = dataList;
        this.context = context;
        this.imageDetailModel = modelImage;
    }
    public void setOnItemClickListener(ThreeModelAdapter.OnItemClickListener listener) {
        itemClickListener = listener;
    }


    @NonNull
    @Override
    public ThreeModelAdapter.ImageModelViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.model_detail, viewGroup, false);
        return new ThreeModelAdapter.ImageModelViewHolder(view, (ThreeModelAdapter.OnItemClickListener) itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageModelViewHolder imageViewHolder, int i) {
        imageViewHolder.recyclerImageModel.setImageResource(imageDetailModel.get(i).getAndroid_image_url());
        if (index == i) {
            imageViewHolder.imageLayoutModel.setCardBackgroundColor(0xFFE8F0B0);
        } else {
            imageViewHolder.imageLayoutModel.setCardBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return imageDetailModel.size();
    }

    public interface OnItemClickListener {

        void OnClick(int pos, int image);
    }

    public class ImageModelViewHolder extends RecyclerView.ViewHolder {
        ImageView recyclerImageModel;
        int pos;
       CardView imageLayoutModel;


        public ImageModelViewHolder(@NonNull View itemView, final ThreeModelAdapter.OnItemClickListener listener) {
            super(itemView);
            recyclerImageModel = itemView.findViewById(R.id.recyclerImagemodel);
            imageLayoutModel=itemView.findViewById(R.id.linearLayoutImageModel);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pos = getAdapterPosition();
                    index = pos;
                    int imageModel = imageDetailModel.get(pos).getModel_location();
                    if (pos != RecyclerView.NO_POSITION) {
                        listener.OnClick(pos, imageModel);


                    }
                    notifyDataSetChanged();
                }

            });
        }

        }

}
