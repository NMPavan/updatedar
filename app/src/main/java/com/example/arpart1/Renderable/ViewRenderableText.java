package com.example.arpart1.Renderable;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.arpart1.R;
import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;

public class ViewRenderableText
{
    Context context;
    ArFragment arFragment;
    String textModel;

    public ViewRenderableText(Context context, ArFragment arFragment, String textModel) {
        this.context = context;
        this.arFragment = arFragment;
        this.textModel = textModel;
    }

    public void createModel(Anchor anchor)
    {
        ViewRenderable.builder()
                .setView(context, R.layout.image_and_text_model)
                .build()
                .thenAccept(viewRenderable -> {
                    View view=viewRenderable.getView();
                    TextView selectedText=view.findViewById(R.id.selectedTextModel);
                    selectedText.setText(textModel);
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
