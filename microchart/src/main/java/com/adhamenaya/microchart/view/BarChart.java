package com.adhamenaya.microchart.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

import com.adhamenaya.microchart.model.ChartData;
import com.adhamenaya.microchart.utils.UiUtils;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by Admin on 18/12/2016.
 */

public class BarChart extends Chart {

    private int mSpace = 0;
    private int mColumnSize = 0;
    private int mColumnsCount = 0;
    private ChartData mChartData;
    private int mCurrentStart = 0;
    private float mHeightUnit = 0;

    public BarChart(Context context) {
        super(context);
    }

    @Override
    protected void init() {
        if (mChartData != null && mChartData.data.size() > 0) {
            mColumnsCount = mChartData.data.size();

            // Column size, 1 is added to reserve a space at the end of the chart
            mColumnSize = (int) ((mWidth / (mColumnsCount)) * 0.9);

            // Calculate the space between the bars
            mSpace = (mWidth / mColumnsCount) - mColumnSize;

            // Calculate height unit
            // Calculate 80% of the total height
            float height08 = mHeight * 0.8f;
            mHeightUnit = height08 / mChartData.getMax();

        }
    }

    @Override
    public void setData(ChartData data) {
        this.mChartData = data;
        init();
        invalidate();
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
        Log.d("---", "onDraw");

        super.onDraw(canvas);
        paintChart(canvas);
    }

    @Override
    public void paintChart(Canvas canvas) {

        // Draw bars
        Iterator iterator = mChartData.getData().entrySet().iterator();


        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();

            int columnHeight = (int) ((int) entry.getValue() * mHeightUnit);
            String key = (String) entry.getKey();

            // Shift the
            mCurrentStart += mSpace;

            // Calculate the difference between the total height and the height of the column
            int heightDiff = mHeight - columnHeight;

            canvas.drawRect(mCurrentStart,
                    heightDiff,
                    mCurrentStart + mColumnSize,
                    mHeight, getRectPaint());

            mCurrentStart += mColumnSize;
            iterator.remove();
        }
    }
}