package com.example.arpart1;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;
import android.support.design.widget.Snackbar;

import com.example.arpart1.Models.AddedObject;
import com.example.arpart1.Utils.StaticData;
import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.collision.Box;
import com.google.ar.sceneform.math.Quaternion;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

/**
 * This is an example activity that uses the Sceneform UX package to make common AR tasks easier.
 */
public class SceneActivity extends AppCompatActivity {
    private static final String TAG = SceneActivity.class.getSimpleName();
    private static final double MIN_OPENGL_VERSION = 3.0;
    private static final double THRESHOLD = 1.5;
    int i = 0;
    private ArFragment arFragment;
    private ModelRenderable andyRenderable;
    TextView textPlane;
    int select = 1;


    AddedObjectsAdapter addedObjectsAdapter;

    @Override
    @SuppressWarnings({"AndroidApiChecker", "FutureReturnValueIgnored"})
    // CompletableFuture requires api level 24
    // FutureReturnValueIgnored is not valid
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!checkIsSupportedDeviceOrFinish(this)) {
            return;
        }

        setContentView(R.layout.activity_ux);
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);
        textPlane = findViewById(R.id.plane_text);
        addRecyclerViewSetUp();
        InitializeGallery();
        planeTapAction();


    }

    private ViewRenderable IntializeViewRenderable() {
        final ViewRenderable[] renderable = {null};
        try {
            ViewRenderable.builder()
                    .setView(this, R.layout.frame_text)
                    .build()
                    .thenAccept(viewRenderable -> renderable[0] = viewRenderable)
                    .exceptionally(
                            throwable -> {
                                Toast toast =
                                        Toast.makeText(this, "Unable to load andy renderable", Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                                return null;
                            });
        } catch (Exception e) {
            e.printStackTrace();
            snackBarShow(e.getLocalizedMessage());
        }
        return renderable[0];
    }

    private void planeTapAction() {
        arFragment.setOnTapArPlaneListener(
                new BaseArFragment.OnTapArPlaneListener() {
                    @Override
                    public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
                        if(plane!=null)
                        if (plane.getType().equals(Plane.Type.HORIZONTAL_UPWARD_FACING)
                                && hitResult.getDistance() > THRESHOLD) {
                            // Create the Anchor.
                            Anchor anchor = hitResult.createAnchor();
                            AnchorNode anchorNode = new AnchorNode(anchor);
                            anchorNode.setParent(arFragment.getArSceneView().getScene());
                            anchorNode.setLocalPosition(new Vector3(
                                    anchorNode.getLocalPosition().x, 0, anchorNode.getLocalPosition().z));

                            // Create the transformable andy and add it to the anchor.
                            TransformableNode andy = new TransformableNode(arFragment.getTransformationSystem());
                            try {
                                andy.setLocalPosition(new Vector3(0.0f, 0.0f, 0.0f));
                            } catch (Exception e) {
                                e.printStackTrace();
                                snackBarShow(e.getLocalizedMessage());

                            }
                            andy.setParent(anchorNode);
                           /*
                            todo:bug in these lines
                            andy.getScaleController().setMaxScale(1f);
                            andy.getScaleController().setMinScale(1f);*/
                            if (i == 2)
                                andy.setLocalRotation(Quaternion.axisAngle(new Vector3(0, -1, 0), 90));
                            andy.getTranslationController().setEnabled(false);//disble drag place interaction
                            andy.setRenderable(andyRenderable);

                            andy.select();

                            try {
                                addName(anchorNode, andy, andyRenderable);
                            } catch (Exception e) {
                                e.printStackTrace();
                                snackBarShow(e.getLocalizedMessage());
                            }

                            try {
                                StaticData.addedObjects.add(new AddedObject(anchorNode, i));
                                addedObjectsAdapter.notifyDataSetChanged();
                            } catch (Exception e) {
                                e.printStackTrace();
                                snackBarShow(e.getLocalizedMessage());

                            }

                        } else if (hitResult.getDistance() < THRESHOLD) {
                            snackBarShow("Please keep more distance between camera and selected position");

                        } else if (!plane.getType().equals(Plane.Type.HORIZONTAL_UPWARD_FACING)) {
                            snackBarShow(
                                    "Cannot place table on selected plane. please select vertically upward facing plane");
                        }
                    }
                });
    }

    private void addName(AnchorNode anchorNode, TransformableNode andy, ModelRenderable parentModelRenderable) {

        TransformableNode chairname = new TransformableNode(arFragment.getTransformationSystem());
        chairname.setLocalPosition(new Vector3(0f, andy.getLocalPosition().y + 1f,
                andy.getLocalPosition().z + 1f));
        chairname.setParent(anchorNode);

        try {
            ViewRenderable.builder()
                    .setView(this, R.layout.frame_text)
                    .build()
                    .thenAccept(viewRenderable -> {
                        chairname.setRenderable(viewRenderable);
                        chairname.getScaleController().setMaxScale(1f);
                        chairname.getScaleController().setMinScale(1f);
                        try {
                            Box box = (Box) andyRenderable.getCollisionShape();

                            TextView chair_text = (TextView) viewRenderable.getView();
                            String s = getTextViewText() + "x=" + box.getSize().x +
                                    "y=" + box.getSize().y +
                                    "z=" + box.getSize().z;
                            chair_text.setText(s);
                            chair_text.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //                chairname.setParent(null);
                                    try {
                                        snackBarShow(s);
                                    } catch (Exception e) {
                                        snackBarShow(e.getLocalizedMessage());

                                    }

                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                            snackBarShow(e.getLocalizedMessage());


                        }

                    })
                    .exceptionally(
                            throwable -> {
                                Toast toast =
                                        Toast.makeText(this, "Unable to load andy renderable", Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                                return null;
                            });
        } catch (Exception e) {
            e.printStackTrace();
            snackBarShow(e.getLocalizedMessage());
        }
        chairname.select();


    }

    private String getTextViewText() {
        String name = "";
        switch (i) {
            case 0:
                name = "android";
                break;
            case 1:
                name = "chair";

                break;
            case 2:
                name = "table";

                break;
        }
        return name;
    }

    private void addRecyclerViewSetUp() {

        RecyclerView rewardRecycler = findViewById(R.id.rewardRecycler);
        rewardRecycler.setHasFixedSize(true);
        rewardRecycler.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, true));

        addedObjectsAdapter = new AddedObjectsAdapter(StaticData.addedObjects, this);
        rewardRecycler.setAdapter(addedObjectsAdapter);
        addedObjectsAdapter.setOnItemClickListener(new AddedObjectsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                try {
                    removeAnchorNode(StaticData.addedObjects.get(position).getAnchorNode());
                    StaticData.addedObjects.remove(position);
                    addedObjectsAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void InitializeGallery() {

        ImageView android = findViewById(R.id.andy);
        ImageView chair = findViewById(R.id.chair);
        ImageView table = findViewById(R.id.table);


        android.setOnClickListener(view -> {
            setModel(R.raw.andy);
        });


        chair.setOnClickListener(view -> {
            setModel(R.raw.chair);
        });

        table.setOnClickListener(view -> {
            setModel(R.raw.table);
        });

        setModel(R.raw.andy);


    }

    private void snackBarShow(String s) {
        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.root), s.trim(), Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private void setModel(int source) {
        switch (source) {
            case R.raw.andy:
                i = 0;
                break;
            case R.raw.chair:
                i = 1;
                break;
            case R.raw.table:
                i = 2;
                break;
        }
        ModelRenderable.builder()
                .setSource(this, source)
                .build()

                .thenAccept(renderable -> andyRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Toast toast =
                                    Toast.makeText(this, "Unable to load andy renderable", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                            return null;
                        });

    }

    private void removeAnchorNode(AnchorNode nodeToremove) {
        //Remove an anchor node
        if (nodeToremove != null) {
            arFragment.getArSceneView().getScene().removeChild(nodeToremove);
            nodeToremove.getAnchor().detach();
            nodeToremove.setParent(null);
            nodeToremove = null;
            snackBarShow("Test Delete - anchorNode removed");
        } else {
            snackBarShow("Test Delete - markAnchorNode was null");
        }
    }

    /**
     * Returns false and displays an error message if Sceneform can not run, true if Sceneform can run
     * on this device.
     *
     * <p>Sceneform requires Android N on the device as well as OpenGL 3.0 capabilities.
     *
     * <p>Finishes the activity if Sceneform can not run
     */
    public static boolean checkIsSupportedDeviceOrFinish(final Activity activity) {
        if (Build.VERSION.SDK_INT < VERSION_CODES.N) {
            Log.e(TAG, "Sceneform requires Android N or later");
            Toast.makeText(activity, "Sceneform requires Android N or later", Toast.LENGTH_LONG).show();
            activity.finish();
            return false;
        }
        String openGlVersionString =
                ((ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE))
                        .getDeviceConfigurationInfo()
                        .getGlEsVersion();
        if (Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
            Log.e(TAG, "Sceneform requires OpenGL ES 3.0 later");
            Toast.makeText(activity, "Sceneform requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG)
                    .show();
            activity.finish();
            return false;
        }
        return true;
    }


}


