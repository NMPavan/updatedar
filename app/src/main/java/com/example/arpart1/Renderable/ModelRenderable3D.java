package com.example.arpart1.Renderable;

import android.content.Context;

import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;


public class ModelRenderable3D
{
    private Context context;
    private ArFragment arFragment;
    int modelId;
    private TransformableNode node;

    public ModelRenderable getViewRenderable() {
        return modelRenderable;
    }

    ModelRenderable modelRenderable;

    public ModelRenderable3D(Context context, ArFragment arFragment, int modelId, TransformableNode node) {
        this.context = context;
        this.arFragment = arFragment;
        this.modelId = modelId;
        this.node = node;
    }


    public  void createModel()
    {
        ModelRenderable.builder()
                .setSource(context,modelId )
                .build()
                .thenAccept(viewRenderable -> {
                    this.modelRenderable=viewRenderable;
                    node.setRenderable(viewRenderable);
                    node.select();
                });
    }

}