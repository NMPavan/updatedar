package com.example.arpart1.Renderable;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arpart1.R;
import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class ViewRenderableText {
    Context context;
    ArFragment arFragment;
    String textModel;
    private TransformableNode node;

    public ViewRenderableText(Context context, ArFragment arFragment, String textModel, TransformableNode node) {
        this.context = context;
        this.arFragment = arFragment;
        this.textModel = textModel;
        this.node = node;
    }

    public void createModel() {
        ViewRenderable.builder()
                .setView(context, R.layout.image_and_text_model)
                .build()
                .thenAccept(viewRenderable -> {
                    View view = viewRenderable.getView();
                    TextView selectedText = view.findViewById(R.id.selectedTextModel);
                    selectedText.setText(textModel);
                    node.setRenderable(viewRenderable);
                    node.select();
                })
                .exceptionally(throwable -> {
                    Toast toast =
                            Toast.makeText(context, "Unable to load andy renderable", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return null;
                });
    }


}
