package com.example.arpart1.Renderable;

import android.content.Context;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

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

    public ViewRenderable getViewRenderable() {
        return viewRenderable;
    }

    ViewRenderable viewRenderable;

    public ViewRenderableImage(Context context, ArFragment arFragment, Uri imageUri) {
        this.context = context;
        this.arFragment = arFragment;
        this.imageUri = imageUri;
    }


    public  void createModel()
    {
        ViewRenderable.builder()
                .setView(context, R.layout.image_and_text_model)
                .build()
                .thenAccept(viewRenderable -> {
                   View view=viewRenderable.getView();
                    ImageView selectedImage=view.findViewById(R.id.selectedImageModel);
                    selectedImage.setImageURI(imageUri);
                    this.viewRenderable=viewRenderable;
                })    .exceptionally(
                throwable -> {
                    Toast toast =
                            Toast.makeText(context, "Unable to load andy renderable", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return null;
                });
    }

    private void placeModel(ViewRenderable viewRenderable, Anchor anchor)
    {
        AnchorNode anchorNode=new AnchorNode(anchor);
        anchorNode.setRenderable(viewRenderable);
        arFragment.getArSceneView().getScene().addChild(anchorNode);
    }
}
