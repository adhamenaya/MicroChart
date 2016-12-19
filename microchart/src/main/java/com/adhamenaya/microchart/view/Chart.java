package com.adhamenaya.microchart.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.adhamenaya.microchart.model.ChartData;
import com.adhamenaya.microchart.utils.UiUtils;

/**
 * Created by Admin on 14/12/2016.
 */

public abstract class Chart extends View {

    protected int mColor = Color.RED;
    protected int mWidth = 0;
    protected int mHeight = 0;
    protected Context mContext;
    protected Paint mMainPaint;
    protected ChartData mChartData;

    public Chart(Context context) {
        super(context);
        this.mContext = context;
    }

    public Chart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    public void setColor(int color) {
        this.mColor = color;
        invalidate();
    }

    protected abstract void paintChart(Canvas canvas);

    protected abstract void prepare();

    public abstract void setData(ChartData data);

    public Paint getTitlePaint() {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(40);
        return paint;
    }

    public Paint getMainPaint() {
        if (mMainPaint == null)
            mMainPaint = new Paint();

        mMainPaint.setAntiAlias(true);
        mMainPaint.setStrokeWidth(UiUtils.dpToPx(mContext, 7));
        mMainPaint.setStyle(Paint.Style.STROKE);
        mMainPaint.setColor(mColor);
        return mMainPaint;
    }

    public Paint getRectPaint() {
        Paint paint = new Paint();

        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(mColor);
        return paint;
    }

    public Paint getLinePaint(int color) {
        Paint paint = new Paint();
        paint.setStrokeWidth(UiUtils.dpToPx(mContext, 2));
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(color);
        return paint;
    }

    public void setDimension(int width, int height) {
        this.mWidth = width;
        this.mHeight = height;
        invalidate();
    }

    protected int getMeasurement(int measureSpec, int size) {
        int specSize = MeasureSpec.getSize(measureSpec);
        int measurement = 0;

        switch (MeasureSpec.getMode(measureSpec)) {
            case MeasureSpec.EXACTLY:
                measurement = specSize;
                break;
            case MeasureSpec.AT_MOST:
                measurement = Math.min(specSize, size);
                break;
            default:
                measurement = size;
        }

        return size;
    }
}
