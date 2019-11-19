package com.example.arpart1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;

public class ProductDescriptionActivity extends AppCompatActivity {
    AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_description);
        appBarLayout = findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if(scrollRange == -1){
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + i == 0) {
                    isShow = true;

                } else if (isShow) {
                    isShow = false;
                }

            }
        });
    }
}
