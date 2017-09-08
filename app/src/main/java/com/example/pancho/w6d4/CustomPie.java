package com.example.pancho.w6d4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Admin on 9/7/2017.
 */

public class CustomPie extends View {

    private Paint paint;
    private int[] sliceClrs = {  Color.GREEN, Color.BLUE, Color.RED, Color.YELLOW };
    private RectF rectf; //Our box
    private float[] datapoints;

    public CustomPie(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.FILL);
    }
    private float[] scale() {
        float[] scaledValues = new float[this.datapoints.length];
        float total = getTotal(); //Total all values supplied to the chart
        for (int i = 0; i < this.datapoints.length; i++) {
            scaledValues[i] = (this.datapoints[i] / total) * 360; //Scale each value
        }
        return scaledValues;
    }

    private float getTotal() {
        float total = 0;
        for (float val : this.datapoints)
            total += val;
        return total;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(this.datapoints != null) {
            int startTop = 0;
            int startLeft = 0;
            int endBottom = getHeight();
            int endRight = getWidth(); //To make this an equal square
            //Create the box
            rectf = new RectF(startLeft, startTop, endRight, endBottom); //Creating the box
            float[] scaledValues = scale(); //Get the scaled values
            float sliceStartPoint = 0;
            for (int i = 0; i < scaledValues.length; i++) {
                paint.setColor(sliceClrs[i]);
                canvas.drawArc(rectf, sliceStartPoint, scaledValues[i], true, paint); //Draw slice
                sliceStartPoint += scaledValues[i]; //Update starting point of the next slice
            }
        }
    }
    public void setDataPoints(float[] datapoints, int colors[]) {
        this.sliceClrs = colors;
        this.datapoints = datapoints;
        invalidate(); //Tells the chart to redraw itself
    }
}