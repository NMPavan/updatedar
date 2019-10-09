package com.example.arpart1.Renderable;

import android.content.Context;
import android.net.Uri;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arpart1.R;
import com.example.arpart1.Utils.ModelDeleteListener;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.HitTestResult;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class ViewRenderableCrossButton {
    private String textDesc;
    Context context;
    ArFragment arFragment;
    private TransformableNode node;
    private AnchorNode nodeToremove;
    private int productId;
    private ModelDeleteListener modelDeleteListener;
    ImageView deleteNode;
    TextView textDescTv;


    public ViewRenderableCrossButton(Context context, ArFragment arFragment, TransformableNode node,
                                     AnchorNode nodeToremove, int productId, ModelDeleteListener modelDeleteListener) {
        this.context = context;
        this.arFragment = arFragment;
        this.node = node;
        this.nodeToremove = nodeToremove;
        this.productId = productId;
        this.modelDeleteListener = modelDeleteListener;
    }

    public ViewRenderableCrossButton(String textDesc, Context context, ArFragment arFragment, TransformableNode node,
                                     AnchorNode nodeToremove, int productId, ModelDeleteListener modelDeleteListener) {
        this.textDesc = textDesc;
        this.context = context;
        this.arFragment = arFragment;
        this.node = node;
        this.nodeToremove = nodeToremove;
        this.productId = productId;
        this.modelDeleteListener = modelDeleteListener;
    }


    public void createModel() {
        ViewRenderable.builder()
                .setView(context, R.layout.layout_delete_node)
                .build()
                .thenAccept(viewRenderable -> {
                    View view = viewRenderable.getView();
                    deleteNode = view.findViewById(R.id.iv_delete);
                    textDescTv = view.findViewById(R.id.desc);
                    setImage(deleteNode);
                    textDescTv.setText(textDesc);
                    viewRenderable.setShadowCaster(false);
                    viewRenderable.setShadowReceiver(false);
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
                                    modelDeleteListener.modelDeleteListener(productId);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                            }
                        }
                    });
                    node.setRenderable(viewRenderable);
                    node.select();

                    toggleVisibility();

                    arFragment.getTransformationSystem().getSelectionVisualizer().removeSelectionVisual(node);
//unknown line
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

    public void toggleVisibility() {
        if (deleteNode != null) {

            try {
                if (deleteNode.getVisibility() == View.VISIBLE)
                    deleteNode.setVisibility(View.INVISIBLE);
                else
                    deleteNode.setVisibility(View.VISIBLE);
                Toast toast =
                        Toast.makeText(context, "toggle start:" + deleteNode.getVisibility(), Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast toast1 =
                        Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_LONG);
                toast1.setGravity(Gravity.BOTTOM, 0, 0);
                toast1.show();
            }
        }
    }

    private void setImage(ImageView deleteNode) {
        deleteNode.setImageResource(R.drawable.ic_cross);
    }

}
