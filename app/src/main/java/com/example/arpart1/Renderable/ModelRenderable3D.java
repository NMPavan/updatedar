package com.example.arpart1.Renderable;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.example.arpart1.R;
import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;


public class ModelRenderable3D
{
    private Context context;
    private ArFragment arFragment;
    Uri imageUri;

    public ModelRenderable getViewRenderable() {
        return modelRenderable;
    }

    ModelRenderable modelRenderable;

    public ModelRenderable3D(Context context, ArFragment arFragment, Uri imageUri) {
        this.context = context;
        this.arFragment = arFragment;
        this.imageUri = imageUri;
    }


    public  void createModel(int rawModel)
    {
        ModelRenderable.builder()
                .setSource(context,rawModel )
                .build()
                .thenAccept(viewRenderable -> {
                    this.modelRenderable=viewRenderable;
                });
    }

}