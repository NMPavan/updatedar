package com.example.arpart1;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arpart1.Models.AddedObject;

import java.util.List;

public class AddedObjectsAdapter extends RecyclerView.Adapter<AddedObjectsAdapter.RewardViewHolder> {

    List<AddedObject> addedObjects;
    Context context;
    private OnItemClickListener itemClickListener;

    public AddedObjectsAdapter(List<AddedObject> addedObjects, Context context) {
        this.addedObjects = addedObjects;
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        itemClickListener = listener;
    }

    @NonNull
    @Override
    public RewardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_dialog, viewGroup, false);
        return new RewardViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RewardViewHolder rewardViewHolder, int position) {
        String name = "";
        switch (addedObjects.get(position).getI()) {
            case 0:
                name = "android";
                break;
            case 1:
                name = "chair";

                break;
            case 2:
                name = "table";

                break;
        }
        rewardViewHolder.rewardProductName.setText(name);
    }

    @Override
    public int getItemCount() {
        return addedObjects.size();
    }

    public interface OnItemClickListener {

        void onItemClick(int position);
    }

    public class RewardViewHolder extends RecyclerView.ViewHolder {
        ImageView rewardImage;
        TextView rewardProductName;

        public RewardViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            rewardImage = itemView.findViewById(R.id.delete_object);
            rewardProductName = itemView.findViewById(R.id.object_name);

            rewardImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });


        }
    }


}








