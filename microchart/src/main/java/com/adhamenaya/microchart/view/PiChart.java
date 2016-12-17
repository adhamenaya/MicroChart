package com.adhamenaya.microchart.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.adhamenaya.microchart.utils.UiUtils;

/**
 * Created by AENAYA on 26/09/2016.
 */
public class PiChart extends Chart {

    private Context mContext;
    private int mCenterX;
    private int mCenterY;
    private int mStartX;
    private int mStartY;

    private int mProgress;
    private int mProgressAngel;

    private Path mPath;
    private RectF mRectF;
    private Paint mPaint;
    private int thickness = 6;
    private float percentage;
    protected int mMax;

    public PiChart(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public PiChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        init();
    }

    protected void init() {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(UiUtils.doToPx(mContext, 0));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.GRAY);
    }



    private int getMeasurement(int measureSpec, int size) {
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
        int radius = (Math.min(circleWidth, circleHeight) - (int) UiUtils.doToPx(mContext, 2)) / 2 - 2;

        mCenterX = circleWidth / 2;
        mCenterY = circleHeight / 2;

        mPath.reset();
        mPath.addCircle(mCenterX, mCenterY, radius, Path.Direction.CW);

        // Draw the inner circle
        int innerRadius = radius - (int) UiUtils.doToPx(mContext, thickness);

        mPath.addCircle(mCenterX, mCenterY, innerRadius, Path.Direction.CW);

        innerRadius += UiUtils.doToPx(mContext, 3);

        mRectF = new RectF(
                mCenterX - innerRadius,
                mCenterY - innerRadius,
                mCenterX + innerRadius,
                mCenterY + innerRadius
        );
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d("---","onDraw");

        super.onDraw(canvas);
        paintChart(canvas);
    }

    public void setProgress(int progress,@Nullable boolean isRounded) {
        this.mProgress = progress;
        percentage = mProgress * 100 / mMax;
        if(isRounded) percentage = Math.round(percentage);
        mProgressAngel = (int) (percentage * 360 / 100);
        invalidate();
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public void setMax(int max) {
        this.mMax = max;
    }

    @Override
    public void paintChart(Canvas canvas) {
        canvas.drawPath(mPath, mPaint);
        canvas.drawArc(mRectF, 270, mProgressAngel, false, getMainPaint());

        //Write text
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);

        // Calculate a relative text size
        int textSize = 20 +(int)(mWidth/13.3);
        paint.setTextSize(textSize);

        // Calculate text with to center the percentage text
        String text = percentage + "%";
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);

        int height = bounds.height();
        int width = bounds.width();

        canvas.drawText(text, mCenterX - (width / 2), mCenterY + (height / 2), paint);

        int titleWidth = UiUtils.getTextBounds("Title test 1",getTitlePaint())[0];
        int titleHeight = UiUtils.getTextBounds("Title test 1",getTitlePaint())[1];
        canvas.drawText("Title test 1", mCenterX-(titleWidth/2) , mHeight-10, getTitlePaint());
    }

    public void setDimension(int width, int height) {
        Log.d("---","setDimension");
        mWidth = Math.min(width, height);
        mHeight = mWidth;
        Log.d("--->",mWidth+"-"+mWidth);

    }
}
