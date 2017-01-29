package com.adhamenaya.microchart.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

import com.adhamenaya.microchart.model.ChartData;
import com.adhamenaya.microchart.utils.UiUtils;

/**
 * Created by Admin on 18/12/2016.
 */

public class HarveyBallChart extends Chart {
    private Context mContext;
    private int mCenterX;
    private int mCenterY;

    private Path mPath;
    private RectF mRectF;
    private Paint mPaint;

    public HarveyBallChart(Context context) {
        super(context);
        prepare();
    }

    @Override
    protected void paintChart(Canvas canvas) {
        canvas.drawPath(mPath, mPaint);
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
    public void setData(ChartData data) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // Get measured height and width of the view
        setMeasuredDimension(getMeasurement(widthMeasureSpec, mWidth),
                getMeasurement(heightMeasureSpec, mHeight));

        int circleWidth = mWidth - 60;
        int circleHeight = mHeight - 60;
        int radius = (Math.min(circleWidth, circleHeight) - (int) UiUtils.dpToPx(mContext, 2)) / 2 - 2;

        mCenterX = circleWidth / 2;
        mCenterY = circleHeight / 2;

        mPath.reset();
        mPath.addCircle(mCenterX, mCenterY, radius, Path.Direction.CW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paintChart(canvas);

    }
}
