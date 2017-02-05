package com.adhamenaya.microchart.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;

import com.adhamenaya.microchart.model.ChartData;
import com.adhamenaya.microchart.utils.UiUtils;

/**
 * Created by AENAYA on 26/09/2016.
 */
public class HarveyBallChart extends Chart {

    private Context mContext;
    private int mCenterX;
    private int mCenterY;
    private int mStartX;
    private int mStartY;

    private int mProgress;
    private float mProgressAngel;

    private Path mPath;
    private RectF mRectF;
    private Paint mPaint;
    private int thickness = 6;
    private float percentage;
    protected int mMax;
    private int radius;

    public HarveyBallChart(Context context) {
        super(context);
        this.mContext = context;
        prepare();
    }

    public HarveyBallChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        prepare();
    }

    protected void prepare() {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(UiUtils.dpToPx(mContext, 0));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.GRAY);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // Get measured height and width of the view
        setMeasuredDimension(getMeasurement(widthMeasureSpec, mWidth),
                getMeasurement(heightMeasureSpec, mHeight));

        // mWidth = MeasureSpec.getSize(widthMeasureSpec);
        // mHeight = MeasureSpec.getSize(heightMeasureSpec);

        int circleWidth = mWidth - 60;
        int circleHeight = mHeight - 60;
        radius = Math.min(circleWidth, circleHeight) / 2;

        mCenterX = circleWidth / 2;
        mCenterY = circleHeight / 2;

        // int innerRadius = radius - (int) UiUtils.dpToPx(mContext, thickness);

        mRectF = new RectF(
                mCenterX - radius,
                mCenterY - radius,
                mCenterX + radius,
                mCenterY + radius
        );
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d("---", "onDraw");

        super.onDraw(canvas);
        paintChart(canvas);
    }

    public void setData(float progress) {
        mProgressAngel = (progress / 100) * 360;
        invalidate();
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public void setMax(int max) {
        // this.mMax = max;
    }

    @Override
    public void paintChart(Canvas canvas) {
        canvas.drawPath(mPath, mPaint);
        // Overwrite stroke
        canvas.drawArc(mRectF, 270, mProgressAngel, true, getArcFillPaint());

    }

    public void setDimension(int width, int height) {
        Log.d("---", "setDimension");
        mWidth = Math.min(width, height);
        mHeight = mWidth;
        Log.d("--->", mWidth + "-" + mWidth);
    }

    @Override
    public void setData(ChartData data) {

    }
}
