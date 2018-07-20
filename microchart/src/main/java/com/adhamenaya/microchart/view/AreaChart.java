package com.adhamenaya.microchart.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.util.Log;

import com.adhamenaya.microchart.model.ChartData;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by Admin on 18/12/2016.
 */

public class AreaChart extends Chart {

    public AreaChart(Context context) {
        super(context);
    }

    private int mPointsCount = 0;
    private int mPointSize = 0;
    private float mHeightUnit = 0;
    private int mCurrentStart = 0;
    private int colorIndex = 0;

    private int[] colors = {Color.RED, Color.BLUE, Color.GREEN};

    @Override
    public void setData(ChartData data) {
        this.mChartData = data;
        prepare();
        invalidate();
    }

    @Override
    protected void prepare() {
        Log.d("****min", mChartData.getMin() + "");
        Log.d("****max", mChartData.getMax() + "");

        if (mChartData != null && mChartData.getMultiData().size() > 0) {
            // Calculate height unit
            // Calculate 80% of the total height
            float height08 = mHeight * 0.9f;
            mHeightUnit = height08 / mChartData.getMax();

            Log.d("mHeight", mHeight + "");
            Log.d("mWidth", mWidth + "");
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // Get measured height and width of the view
        setMeasuredDimension(getMeasurement(widthMeasureSpec, mWidth),
                getMeasurement(heightMeasureSpec, mHeight));

        // mWidth = MeasureSpec.getSize(widthMeasureSpec);
        // mHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paintChart(canvas);
    }

    @Override
    public void paintChart(Canvas canvas) {

        // Draw bars
        Iterator iterator = mChartData.getMultiData().entrySet().iterator();


        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();

            Map<String, Integer> lineData = (Map<String, Integer>) entry.getValue();
            String key = (String) entry.getKey();

            // Column size, 1 is added to reserve a space at the end of the chart
            mPointSize = (int) ((mWidth / (mChartData.getItemsCount(key) - 1)));

            paintPolyline(canvas, lineData);

            iterator.remove();
            colorIndex++;
            if (colorIndex == colors.length) colorIndex = 0;
        }
    }

    private void paintPolyline(Canvas canvas, Map<String, Integer> lineData) {

        canvas.drawPath(buildPath(lineData), getLinePaint(colors[colorIndex]));
    }

    private Path buildPath(Map<String, Integer> data) {

        // init current position
        mCurrentStart = 0;


        Path path = new Path();
        Iterator iterator = data.entrySet().iterator();
        int index = 0;

        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();

            int value = (int) ((int) entry.getValue() * mHeightUnit);
            String key = (String) entry.getKey();
            int height = mHeight - value;

            if (index == 0) {
                path.moveTo(mCurrentStart, height);
            } else {
                mCurrentStart += mPointSize;
            }
            Log.d("hhh", mHeightUnit + "");
            path.lineTo(mCurrentStart, height);

            iterator.remove();
            index++;

        }
        path.moveTo(mCurrentStart, mHeight);
        path.close();

        return path;
    }

    @Override
    float getConvertHeight(float height) {
        return 0;
    }

    @Override
    float getConvertedWidth(float width) {
        return 0;
    }
}
