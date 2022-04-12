package com.groupone.mobilestore.util;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.groupone.mobilestore.R;

public class ClearTextWatcher implements TextWatcher {

    private EditText view;
    public ClearTextWatcher(EditText view) {
        this.view = view;
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void afterTextChanged(Editable s) {

        if(s.length() > 0) {
            view.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_clear, 0);
            view.setOnTouchListener((v, event) -> {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (view.getRight() - view.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        view.setText("");
                        return true;
                    }
                }
                return false;
            });
        } else {
            view.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }



    }



}
