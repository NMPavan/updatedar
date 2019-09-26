package com.example.arpart1.Renderable;

import android.content.Context;
import android.net.Uri;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.arpart1.R;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.HitTestResult;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class ViewRenderableCrossButton {
    Context context;
    ArFragment arFragment;
    private TransformableNode node;
    private AnchorNode nodeToremove;


    public ViewRenderableCrossButton(Context context, ArFragment arFragment, TransformableNode node, AnchorNode nodeToremove) {
        this.context = context;
        this.arFragment = arFragment;
        this.node = node;
        this.nodeToremove = nodeToremove;
    }


    public void createModel() {
        ViewRenderable.builder()
                .setView(context, R.layout.image_and_text_model)
                .build()
                .thenAccept(viewRenderable -> {
                    View view = viewRenderable.getView();
                    ImageView deleteNode = view.findViewById(R.id.selectedImageModel);
                    setImage(deleteNode);

                    deleteNode.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //Remove an anchor node
                            if (nodeToremove != null) {
                                try {
                                    arFragment.getArSceneView().getScene().removeChild(nodeToremove);
                                    nodeToremove.getAnchor().detach();
                                    nodeToremove.setParent(null);
                                    nodeToremove = null;


                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                            }
                        }
                    });
                    assert nodeToremove != null;
                    nodeToremove.setOnTouchListener(new Node.OnTouchListener() {
                        @Override
                        public boolean onTouch(HitTestResult hitTestResult, MotionEvent motionEvent) {
                            try {
                                if (deleteNode.getVisibility() == View.VISIBLE)
                                    deleteNode.setVisibility(View.INVISIBLE);
                                else
                                    deleteNode.setVisibility(View.VISIBLE);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                            return false;
                        }

                    });
                    node.setRenderable(viewRenderable);
                    node.select();
                    arFragment.getTransformationSystem().getSelectionVisualizer().removeSelectionVisual(node);

                    nodeToremove.addChild(node);

                }).exceptionally(
                throwable -> {
                    Toast toast =
                            Toast.makeText(context, "Unable to load delete renderable", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return null;
                });

    }

    private void setImage(ImageView deleteNode) {
        try {
            ViewGroup.LayoutParams params = deleteNode.getLayoutParams();
            params.width = 24;
            params.height = 24;
// existing height is ok as is, no need to edit it
            deleteNode.setLayoutParams(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        deleteNode.setImageResource(R.drawable.ic_delete_black_24dp);
        Toast toast =
                Toast.makeText(context, "setImage", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();

    }

}
