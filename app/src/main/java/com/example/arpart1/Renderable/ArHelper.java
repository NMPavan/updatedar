package com.example.arpart1.Renderable;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.arpart1.R;
import com.example.arpart1.Utils.StaticData;
import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.HitTestResult;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class ArHelper {
    private static final double THRESHOLD = 1.5;

    private Context context;
    private ArFragment arFragment;
    private HitResult hitResult;
    private Plane plane;
    private MotionEvent motionEvent;

    public ArHelper(Context context, ArFragment arFragment, HitResult hitResult, Plane plane, MotionEvent motionEvent) {
        this.context = context;
        this.arFragment = arFragment;
        this.hitResult = hitResult;
        this.plane = plane;
        this.motionEvent = motionEvent;
    }

    public void placeModel() {
        verifyConditionsForPlacement();
    }


    private void verifyConditionsForPlacement() {
        if (plane != null)
            if (plane.getType().equals(Plane.Type.HORIZONTAL_UPWARD_FACING)
                    && hitResult.getDistance() > THRESHOLD) {
                placeModelOnAnchor();
            } else if (hitResult.getDistance() < THRESHOLD) {
                setError(context.getString(R.string.error_camera_distance));
            } else if (!plane.getType().equals(Plane.Type.HORIZONTAL_UPWARD_FACING)) {
                setError(context.getString(R.string.error_vertical_plane));
            }

    }


    private void placeModelOnAnchor() {
        AnchorNode anchorNode = getAnchorNode(hitResult.createAnchor());
        if (anchorNode != null) {
            createTransformableNode(anchorNode);
        }

    }

    private void createTransformableNode(AnchorNode anchorNode) {
        TransformableNode andy = new TransformableNode(arFragment.getTransformationSystem());
        andy.setLocalPosition(new Vector3(0.0f, 0.0f, 0.0f));
        andy.setParent(anchorNode);
        andy.getTranslationController().setEnabled(false);//disble drag place interaction
        placeRenderable(andy);
//        andy.select();
        setError("MODEL PLACED");

        anchorNode.setOnTapListener(new Node.OnTapListener() {
            @Override
            public void onTap(HitTestResult hitTestResult, MotionEvent motionEvent) {
                setError("MODEL tapped");

            }
        });


    }

    private void placeRenderable(TransformableNode andy) {
        if (StaticData.arProductToPlace != null) {
            switch (StaticData.arProductToPlace.getArProductType()) {
                case IMAGE_MODEL:
                    ViewRenderableImage viewRenderableImage = new ViewRenderableImage(context, arFragment,
                            StaticData.arProductToPlace.getUri(),andy);
                    viewRenderableImage.createModel();
//                    andy.setRenderable(viewRenderableImage.getViewRenderable());
                    setError("MODEL rendered");
                    break;
                case THREED_MODEL:
                    ModelRenderable3D modelRenderable=new ModelRenderable3D(context, arFragment,
                           R.raw.andy,andy);
                    modelRenderable.createModel();
//                    andy.setRenderable(modelRenderable.getViewRenderable());
                    setError("MODEL rendered");


                    break;
                case TEXT_MODEL:

                    break;

            }
        }

    }

    private AnchorNode getAnchorNode(Anchor anchor) {
        //parent anchor
        //doesn't depend on placing model
        AnchorNode anchorNode = new AnchorNode(anchor);
        anchorNode.setParent(arFragment.getArSceneView().getScene());
        anchorNode.setLocalPosition(new Vector3(
                anchorNode.getLocalPosition().x, 0, anchorNode.getLocalPosition().z));
        setError("anchor added");

        return anchorNode;
    }

    private void setError(String string) {
        if (arFragment.getView() != null && arFragment.getView().getRootView() != null)
            StaticData.showSnackBar(arFragment.getView().getRootView(),
                    string);
        else
            Toast.makeText(context, string, Toast.LENGTH_SHORT).show();

    }
}
