package com.adhamenaya.microchart.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

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

    public abstract void paintChart(Canvas canvas);
    protected abstract void init();

    public Paint getTitlePaint() {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(40);
        return paint;
    }

    public Paint getMainPaint(){
        if (mMainPaint==null)
            mMainPaint = new Paint();

        mMainPaint.setAntiAlias(true);
        mMainPaint.setStrokeWidth(UiUtils.doToPx(mContext, 7));
        mMainPaint.setStyle(Paint.Style.STROKE);
        mMainPaint.setColor(mColor);
        return mMainPaint;
    }
    public void setDimension(int width,int height){
        this.mWidth = width;
        this.mHeight = height;
        invalidate();
    }
}
