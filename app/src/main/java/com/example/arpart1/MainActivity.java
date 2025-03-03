package com.example.arpart1;

import android.app.Activity;
import android.app.ActivityManager;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arpart1.AlerDialogs.ImageAlertDialog;
import com.example.arpart1.AlerDialogs.ThreeDModelAlertDialog;
import com.example.arpart1.AlerDialogs.TextAlertDialog;
import com.example.arpart1.Models.ArProduct;
import com.example.arpart1.Renderable.ArHelper;
import com.example.arpart1.Utils.SelectorChooseListener;
import com.example.arpart1.Utils.StaticData;
import com.example.arpart1.databinding.ActivityMainBinding;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;

import java.util.ArrayList;

import static com.example.arpart1.Utils.StaticData.placedObjects;


public class MainActivity extends AppCompatActivity implements SelectorChooseListener {
    private static final String TAG = SceneActivity.class.getSimpleName();


    ActivityMainBinding binding;
    ImageAlertDialog imageAlertDialog;
    TextAlertDialog textAlertDialog;
    ThreeDModelAlertDialog threeDModelAlertDialog;


    ArFragment arFragment;
    int[] countPlacedObjects = {0, 0, 0};
    int arFragmentId = R.id.ux_ar_fragment;

    //region sceneForm setup
    // CompletableFuture requires api level 24
    // FutureReturnValueIgnored is not valid
    @SuppressWarnings({"AndroidApiChecker", "FutureReturnValueIgnored"})
    private static final double MIN_OPENGL_VERSION = 3.0;

    /**
     * Returns false and displays an error message if Sceneform can not run, true if Sceneform can run
     * on this device.
     *
     * <p>Sceneform requires Android N on the device as well as OpenGL 3.0 capabilities.
     *
     * <p>Finishes the activity if Sceneform can not run
     */
    public static boolean checkIsSupportedDeviceOrFinish(final Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
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
    //endregion


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        imageAlertDialog = new ImageAlertDialog(this, this::SelectorChooseListener);
        textAlertDialog = new TextAlertDialog(this, this::SelectorChooseListener);
        threeDModelAlertDialog = new ThreeDModelAlertDialog(this, this::SelectorChooseListener);
        setListeners();
        setArFragment();
        updateCount();

        try {
            View decorView = getWindow().getDecorView();
// Hide both the navigation bar and the status bar.
// SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
// a general rule, you should design your app to hide the status bar whenever you
// hide the navigation bar.

//        int uiOptions = View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR ;
            decorView.setSystemUiVisibility(16);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void setArFragment() {
        if (findViewById(arFragmentId) != null) {
            arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(arFragmentId);
            try {
                //plane material change
//                ArHelper.setPlaneTexture(MainActivity.this, "asterisk.png", arFragment.getArSceneView());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (arFragment != null)
            arFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
                @Override
                public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
                    ArHelper arHelper = new ArHelper(MainActivity.this, arFragment, hitResult, plane, motionEvent);
                    arHelper.placeModel();


                    try {
                        arHelper.getArrayListMutableLiveData().observe(MainActivity.this,
                                new Observer<ArrayList<ArProduct>>() {
                            @Override
                            public void onChanged(@Nullable ArrayList<ArProduct> arProducts) {
                                updateCount();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            });
    }

    private void updateCount() {
        countPlacedObjects[0] = 0;
        countPlacedObjects[1] = 0;
        countPlacedObjects[2] = 0;

        for (ArProduct arProduct :
                placedObjects) {
            switch (arProduct.getArProductType()) {
                case IMAGE_MODEL:
                    countPlacedObjects[1]++;

                    break;
                case THREED_MODEL:
                    countPlacedObjects[0]++;

                    break;
                case TEXT_MODEL:
                    countPlacedObjects[2]++;

                    break;

            }
        }
        setTextToTextView(countPlacedObjects[0], binding.count3dPlaced);
        setTextToTextView(countPlacedObjects[1], binding.countImagePlaced);
        setTextToTextView(countPlacedObjects[2], binding.countTextPlaced);
        StaticData.showToast(getApplicationContext(), "count 0:" + countPlacedObjects[0] + "1:" + countPlacedObjects[1] + "2:" + countPlacedObjects[2]);

    }

    private void setTextToTextView(int countPlacedObject, TextView textView) {
        textView.setBackground(null);

        if (countPlacedObject != 0) {
            textView.setText(String.valueOf(countPlacedObject));
            textView.setBackground(getDrawable(R.drawable.object_count_background));
            textView.setVisibility(View.VISIBLE);

        } else {
            textView.setText("");
            textView.setVisibility(View.GONE);
        }


    }

    private void selectorChoose(ArProduct.ArProductType arProductType) {
        View view = null;
        switch (arProductType) {
            case IMAGE_MODEL:
                view = binding.ClImageModel;
                break;
            case THREED_MODEL:
                view = binding.Cl3dModel;
                break;
            case TEXT_MODEL:
                view = binding.ClTextModel;
                break;

        }
        binding.ClImageModel.setBackground(null);
        binding.ClTextModel.setBackground(null);
        binding.Cl3dModel.setBackground(null);

        if (view != null)
            view.setBackground(getDrawable(R.drawable.selected_background));


    }


    private void setListeners() {

        binding.Cl3dModel.setOnClickListener(view -> {
            threeDModelAlertDialog.createAlertDialog();

        });
        binding.ClImageModel.setOnClickListener(view -> {

            imageAlertDialog.createAlertDialog();

        });
        binding.ClTextModel.setOnClickListener(view -> {
            textAlertDialog.createAlertDialog();
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == ImageAlertDialog.PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            imageAlertDialog.handleIntentResponse(data);
            StaticData.showSnackBar(binding.root, "User Selected the image");
        }
        if (requestCode == ImageAlertDialog.PICK_IMAGE && resultCode == RESULT_CANCELED) {
            StaticData.showSnackBar(binding.root, "User cancelled the image");
        }

    }


    @Override
    public void SelectorChooseListener(ArProduct.ArProductType arProductType) {
        selectorChoose(arProductType);
    }
}
