package com.example.arpart1.Renderable;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.arpart1.R;
import com.example.arpart1.Utils.StaticData;
import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.ArSceneView;
import com.google.ar.sceneform.HitTestResult;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.collision.Box;
import com.google.ar.sceneform.math.Quaternion;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.Texture;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import static android.support.constraint.Constraints.TAG;
import static com.google.ar.sceneform.rendering.MaterialFactory.MATERIAL_TEXTURE;
import static com.google.ar.sceneform.rendering.PlaneRenderer.MATERIAL_UV_SCALE;

public class ArHelper {
    private static double THRESHOLD = 0.5;

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
        if (StaticData.arProductToPlace != null)
            THRESHOLD = StaticData.arProductToPlace.getThresholdDistance();
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
        if (StaticData.arProductToPlace.getIfModelIsTable())
            andy.setLocalRotation(Quaternion.axisAngle(new Vector3(0, -1, 0), 90));

        andy.getTranslationController().setEnabled(false);//disble drag place interaction
        placeRenderable(andy);
        setCrossButton(andy, anchorNode);


    }

    private void setCrossButton(TransformableNode andy, AnchorNode anchorNode) {
        Box box = (Box) andy.getCollisionShape();
        float yCross = 0f;
        float zCross = 0f;
        if (box != null && box.getExtents() != null) {
            yCross = box.getExtents().y + 0.25f;
            zCross = box.getExtents().z;
        }
        TransformableNode transformableNode = new TransformableNode(arFragment.getTransformationSystem());
        transformableNode.setLocalPosition(new Vector3(0.0f, andy.getLocalPosition().y + yCross,
                andy.getLocalPosition().z + zCross));
        transformableNode.setParent(anchorNode);
        transformableNode.getTranslationController().setEnabled(false);//disble dra
        ViewRenderableCrossButton viewRenderableCrossButton = new ViewRenderableCrossButton(context,
                arFragment, transformableNode, anchorNode);
        viewRenderableCrossButton.createModel();


        andy.setOnTapListener(new Node.OnTapListener() {
            @Override
            public void onTap(HitTestResult hitTestResult, MotionEvent motionEvent) {
                viewRenderableCrossButton.toggleVisibility();
            }
        });

    }

    private void placeRenderable(TransformableNode andy) {
        if (StaticData.arProductToPlace != null) {
            switch (StaticData.arProductToPlace.getArProductType()) {
                case IMAGE_MODEL:
                    ViewRenderableImage viewRenderableImage = new ViewRenderableImage(context, arFragment,
                            StaticData.arProductToPlace.getUri(), andy);
                    viewRenderableImage.createModel();
                    break;
                case THREED_MODEL:
                    ModelRenderable3D modelRenderable = new ModelRenderable3D(context, arFragment,
                            StaticData.arProductToPlace.getRawModel(), andy);
                    modelRenderable.createModel();
                    break;
                case TEXT_MODEL:
                    ViewRenderableText viewRenderableText = new ViewRenderableText(context, arFragment,
                            StaticData.arProductToPlace.getText(), andy);
                    viewRenderableText.createModel();
                    break;

            }
        }

    }

    public static void setPlaneTexture(Context context, String texturePath, ArSceneView arSceneView) {

        Texture.Sampler sampler = Texture.Sampler.builder()
                .setMinFilter(Texture.Sampler.MinFilter.LINEAR_MIPMAP_LINEAR)
                .setMagFilter(Texture.Sampler.MagFilter.LINEAR)
                .setWrapModeR(Texture.Sampler.WrapMode.REPEAT)
                .setWrapModeS(Texture.Sampler.WrapMode.REPEAT)
                .setWrapModeT(Texture.Sampler.WrapMode.REPEAT)
                .build();

        Texture.builder().setSource(() -> context.getAssets().open(texturePath))
                .setSampler(sampler)
                .build().thenAccept((texture) -> {
            arSceneView.getPlaneRenderer().getMaterial()
                    .thenAccept((material) -> {
                        material.setTexture(MATERIAL_TEXTURE, texture);
                        material.setFloat(MATERIAL_UV_SCALE, 1f);
                    });
        }).exceptionally(ex -> {
            Log.e(TAG, "Failed to read an asset file", ex);
            return null;
        });
    }

    private AnchorNode getAnchorNode(Anchor anchor) {
        //parent anchor
        //doesn't depend on placing model
        AnchorNode anchorNode = new AnchorNode(anchor);
        anchorNode.setParent(arFragment.getArSceneView().getScene());
        anchorNode.setLocalPosition(new Vector3(
                anchorNode.getLocalPosition().x, 0, anchorNode.getLocalPosition().z));
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
