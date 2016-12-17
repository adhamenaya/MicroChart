package com.adhamenaya.microchartsamples;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.adhamenaya.microchart.view.PiChart;

import java.io.LineNumberReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        LinearLayout frameLayout = (LinearLayout) findViewById(R.id.lyt_main);

        PiChart piChart = new PiChart(getApplicationContext());
        piChart.setMax(100);
        piChart.setProgress(30,true);
        piChart.setColor(Color.GREEN);
        piChart.setDimension(300,300);
        frameLayout.addView(piChart);

        PiChart piChart2 = new PiChart(getApplicationContext());
        piChart2.setMax(100);
        piChart2.setProgress(60,true);
        piChart2.setColor(Color.RED);
        piChart2.setDimension(300,300);
        frameLayout.addView(piChart2);

        PiChart piChart3 = new PiChart(getApplicationContext());
        piChart3.setMax(100);
        piChart3.setProgress(45,true);
        piChart3.setColor(Color.YELLOW);
        piChart3.setDimension(300,300);
        frameLayout.addView(piChart3);
    }
}
