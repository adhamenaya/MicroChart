package com.adhamenaya.microchart.utils;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by AENAYA on 26/09/2016.
 */
public class UiUtils {

    public static float dpToPx(Context context, int dp) {
        return context.getResources().getDisplayMetrics().density * dp;
    }

    public static float toDp(Context context, int value) {
        return value / context.getResources().getDisplayMetrics().density;
    }

    public static int[] getTextBounds(String text, Paint paint) {
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        int[] boundsArr = new int[2];
        boundsArr[0] = bounds.width();
        boundsArr[1] = bounds.height();

        return boundsArr;
    }
}
