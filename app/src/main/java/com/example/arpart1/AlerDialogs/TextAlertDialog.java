package com.example.arpart1.AlerDialogs;

import android.app.AlertDialog;
import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.arpart1.Models.ArProduct;
import com.example.arpart1.R;
import com.example.arpart1.Utils.SelectorChooseListener;
import com.example.arpart1.Utils.StaticData;
import com.example.arpart1.databinding.TextAlertDialogBinding;

import static com.example.arpart1.Utils.StaticData.arProductToPlace;

public class TextAlertDialog {

    private Context context;
    private SelectorChooseListener selectorChooseListener;
    private TextAlertDialogBinding binding;
    AlertDialog dialog;
    private TextWatcher listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (binding.textForPlane.getText().toString().trim().length() == 0) {
                binding.okTextDialog.setEnabled(false);
                binding.okTextDialog.setBackgroundColor(context.getResources().getColor(R.color.disabled_button));

            } else {
                binding.okTextDialog.setEnabled(true);
                binding.okTextDialog.setBackgroundColor(0xFF006400);
            }
        }
    };

    public TextAlertDialog(Context context, SelectorChooseListener selectorChooseListener) {
        this.context = context;
        this.selectorChooseListener = selectorChooseListener;
    }

    public void createAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.text_alert_dialog, null, false);
        builder.setView(binding.getRoot());

        dialog = builder.create();

        binding.textForPlane.addTextChangedListener(listener);

        setListener();


        dialog.show();
    }

    private void setListener() {

        binding.cancelTextDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        binding.okTextDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!binding.okTextDialog.isEnabled()) {
                    Toast.makeText(context, "Please write something to show", Toast.LENGTH_SHORT).show();
                } else {
                    StaticData.selectedStringForModel=binding.textForPlane.getText().toString();

                    arProductToPlace = new ArProduct( ArProduct.ArProductType.TEXT_MODEL);
                    arProductToPlace.setText(binding.textForPlane.getText().toString());
                    selectorChooseListener.SelectorChooseListener(ArProduct.ArProductType.TEXT_MODEL);

                    dialog.dismiss();
                }
            }
        });
    }
}
