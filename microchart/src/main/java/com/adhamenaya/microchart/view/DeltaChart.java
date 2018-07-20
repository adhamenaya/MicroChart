package com.adhamenaya.microchart.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;

import com.adhamenaya.microchart.model.ChartData;
import com.adhamenaya.microchart.model.ValueColor;
import com.adhamenaya.microchart.utils.Box;

import static com.adhamenaya.microchart.model.ValueColor.NEUTRAL;

/**
 * Created by Admin on 18/12/2016.
 */

public class DeltaChart extends Chart {

    private ValueColor color;
    private String deltaDisplayValue;
    private float value1;
    private float value2;
    private float deltaValue;
    private String valueTitle1;
    private String valueTitle2;

    Box box1;
    Box box2;
    Box delta;

    public DeltaChart(Context context) {
        super(context);
    }

    @Override
    protected void paintChart(Canvas canvas) {
        canvas.drawRect(box1.x1, box1.y1, box1.x2, box1.y2, getColoredPaint(Color.GRAY));
        canvas.drawRect(delta.x1, delta.y1, delta.x2, delta.y2, getColoredPaint(Color.BLUE));
        canvas.drawRect(box2.x1, box2.y1, box2.x2, box2.y2, getColoredPaint(Color.GRAY));
    }

    @Override
    protected void prepare() {

        float deltaValue;
        float maxValueWidth;
        float space = 0;
        float currentY = 0;

        // Calculate space between boxes
        int boxHeight = (int) ((mHeight / 3) * 0.9);

        // Calculate the space between the bars
        space = (mHeight / 3) - boxHeight;

        deltaValue = Math.abs(Math.abs(value1) - Math.abs(value2));

        float shiftValue = 0;

        // If we have one value is negative, then the 2 boxes will in opposite edges.
        if (value1 < 0 ^ value2 < 0) {
            // Start from left and right
            // Box 1
            shiftValue = value1 < 0 ? 0 : getConvertedWidth(value2);
            box1 = new Box(shiftValue, currentY, getConvertedWidth(Math.abs(value1)) + shiftValue, currentY + boxHeight, NEUTRAL);
            currentY += (space + boxHeight);

            // Delta box
            maxValueWidth = Math.abs(value1) + Math.abs(value2);
            delta = new Box(0, currentY, getConvertedWidth(maxValueWidth), currentY + boxHeight, NEUTRAL);
            currentY += (space + boxHeight);

            // Box 2
            shiftValue = value2 < 0 ? 0 : getConvertedWidth(value1);
            box2 = new Box(shiftValue, currentY, getConvertedWidth(Math.abs(value2)) + shiftValue, currentY + boxHeight, NEUTRAL);

        } else if (value1 < 0 && value2 < 0) {
            // Start from left
            maxValueWidth = Math.max(Math.abs(value1), Math.abs(value2));
        } else {
            // Start from right
            maxValueWidth = Math.max(value1, value2);
        }
    }

    @Override
    float getConvertedWidth(float width) {

        float maxValue = (Math.abs(value1) + Math.abs(value2));
        if (maxValue <= 0) return 0;

        return Math.abs(width * (mWidth / maxValue));
    }


    @Override
    float getConvertHeight(float height) {
        return 0;
    }

    @Override
    public void setData(ChartData data) {

    }

    public void setValues(float value1, float value2) {
        this.value1 = value1;
        this.value2 = value2;
        prepare();
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // Get measured height and width of the view
        setMeasuredDimension(getMeasurement(widthMeasureSpec, mWidth),
                getMeasurement(heightMeasureSpec, mHeight));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d("---", "onDraw");

        super.onDraw(canvas);
        paintChart(canvas);
    }
}
