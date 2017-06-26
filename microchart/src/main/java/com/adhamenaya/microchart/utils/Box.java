package com.adhamenaya.microchart.utils;

import com.adhamenaya.microchart.model.ValueColor;

/**
 * Created by Admin on 14/06/2017.
 */

public class Box {

    public float x1;
    public float y1;
    public float x2;
    public float y2;

    public ValueColor color;

    public Box(float x1, float y1, float x2, float y2, ValueColor color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
    }
}
