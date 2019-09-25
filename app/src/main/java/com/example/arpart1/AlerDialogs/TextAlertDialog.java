package com.example.arpart1.AlerDialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;

import com.example.arpart1.R;
import com.example.arpart1.databinding.TextAlertDialogBinding;

public class TextAlertDialog {

    Context context;
    TextAlertDialogBinding binding;

    public TextAlertDialog(Context context) {
        this.context = context;
    }

    public void createAlertDialog()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        binding= DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.text_alert_dialog,null,false);
        builder.setView(binding.getRoot());

        final AlertDialog dialog=builder.create();


        binding.textForPlane.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (binding.textForPlane.getText().toString().trim().length()>0)
                {
                    binding.okTextDialog.setEnabled(true);
                    binding.okTextDialog.setBackgroundColor(Color.GREEN);
                }
                else
                {
                    binding.okTextDialog.setEnabled(false);
                    binding.okTextDialog.setBackgroundColor(Color.GRAY);

                }
            }
        });

        binding.cancelTextDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        binding.okTextDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }
}
