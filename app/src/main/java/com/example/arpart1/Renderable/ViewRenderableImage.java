package com.example.arpart1.Renderable;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.example.arpart1.R;
import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;

public class ViewRenderableImage
{
    Context context;
    ArFragment arFragment;
    Uri imageUri;

    public ViewRenderableImage(Context context, ArFragment arFragment, Uri imageUri) {
        this.context = context;
        this.arFragment = arFragment;
        this.imageUri = imageUri;
    }


    public  void createModel(Anchor anchor)
    {
        ViewRenderable.builder()
                .setView(context, R.layout.image_and_text_model)
                .build()
                .thenAccept(viewRenderable -> {
                   View view=viewRenderable.getView();
                    ImageView selectedImage=view.findViewById(R.id.selectedImageModel);
                    selectedImage.setImageURI(imageUri);
                    placeModel(viewRenderable,anchor);
                });
    }

    private void placeModel(ViewRenderable viewRenderable, Anchor anchor)
    {
        AnchorNode anchorNode=new AnchorNode(anchor);
        anchorNode.setRenderable(viewRenderable);
        arFragment.getArSceneView().getScene().addChild(anchorNode);
    }
}
