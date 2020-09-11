package com.example.finalyearprojectlearning;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

class Stories {
    private int imageView;
    private String textView;

    public Stories(int imageView, String textView) {
        this.imageView = imageView;
        this.textView = textView;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

    public String getTextView() {
        return textView;
    }

    public void setTextView(String textView) {
        this.textView = textView;
    }
}
