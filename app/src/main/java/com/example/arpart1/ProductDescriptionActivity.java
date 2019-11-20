package com.example.arpart1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;

public class ProductDescriptionActivity extends AppCompatActivity {
    AppBarLayout appBarLayout;

    int imageBitmap;
    String desc;
    int price;
    ImageView productImage;
    TextView ProductPrice;

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Clicked",""+getIntent().getIntExtra("price",0));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_description);
        productImage=findViewById(R.id.expandedImage);
        ProductPrice=findViewById(R.id.productprice1);
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
        mainpageProduct();
       // productlistPage();
    }

//    private void productlistPage() {
//        if (getIntent()!=null){
//            imageBitmap = getIntent().getIntExtra("productimage",0);
//            price = getIntent().getIntExtra("productprice",0);
//            productImage.setImageResource(imageBitmap);
//            ProductPrice.setText(""+price);
//        }
//    }

    private void mainpageProduct() {
        if (getIntent()!=null)
        {
            imageBitmap=getIntent().getIntExtra("image",0);
            price=getIntent().getIntExtra("price",0);
            //  desc=getIntent().getStringExtra("desc");
            productImage.setImageResource(imageBitmap);
            ProductPrice.setText(""+price);

        }
    }

}
