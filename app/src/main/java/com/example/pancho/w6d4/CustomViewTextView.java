package com.example.pancho.w6d4;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;


public class CustomViewTextView extends android.support.v7.widget.AppCompatTextView {

    private String fontName;

    public CustomViewTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomViewTextView);
        fontName = a.getString(R.styleable.CustomViewTextView_fontName);
        init();
        a.recycle();
    }

    public CustomViewTextView(Context context) {
        super(context);
        init();
    }

    public CustomViewTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomViewTextView);
        fontName = a.getString(R.styleable.CustomViewTextView_fontName);
        init();
        a.recycle();
    }

    public void init() {
        Typeface tf;
        if(fontName == null)
            tf = Typeface.createFromAsset(getContext().getAssets(), "fontawesome.ttf");
        else
            tf = Typeface.createFromAsset(getContext().getAssets(), fontName);
        setTypeface(tf);
    }

}
