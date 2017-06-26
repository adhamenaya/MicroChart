package com.adhamenaya.microchartsamples;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.adhamenaya.microchart.model.ChartData;
import com.adhamenaya.microchart.view.AreaChart;
import com.adhamenaya.microchart.view.ColumnChart;
import com.adhamenaya.microchart.view.DeltaChart;
import com.adhamenaya.microchart.view.HarveyBallChart;
import com.adhamenaya.microchart.view.RadialChart;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        LinearLayout frameLayout = (LinearLayout) findViewById(R.id.lyt_main);

       RadialChart radialChart = new RadialChart(getApplicationContext());
        radialChart.setMax(100);
        radialChart.setData(30);
        radialChart.setColor(Color.GREEN);
        radialChart.setDimension(200, 200);
        frameLayout.addView(radialChart);

        ColumnChart columnChart = new ColumnChart(getApplicationContext());
        columnChart.setDimension(500, 200);
        ChartData data = new ChartData();
        data.add("a", 100);
        data.add("b2", 200);
        data.add("c4", 350);
        data.add("c3", 250);
        data.add("c5", 650);
        data.add("c6", 850);
        data.add("d", 340);
        data.add("e8", 300);
        data.add("b26", 200);
        data.add("c47", 350);
        data.add("7d81", 340);
        data.add("e781", 300);
        data.add("c51", 650);
        data.add("c16", 850);
        data.add("1d", 340);
        data.add("1e8", 300);
        data.add("1b26", 200);
        data.add("1c47", 350);
        data.add("1c38", 250);
        data.add("1c58", 650);
        data.add("1c68", 850);
        data.add("1d8", 340);
        data.add("1e8", 300);
        data.add("1e78", 300);
        columnChart.setData(data);
        columnChart.setColor(Color.BLUE);
        frameLayout.addView(columnChart);

        // Area chart

        AreaChart areaChart = new AreaChart(getApplicationContext());
        ChartData data2 = new ChartData();

        data2.add("sami","c", 120);
        data2.add("sami","d", 150);
        data2.add("sami","dd", 170);
        data2.add("sami","dd2", 140);
        data2.add("sami","dd3", 110);
        data2.add("aa","dd2", 220);
        data2.add("aa","dd3", 230);
        data2.add("aa","fr", 270);
        data2.add("bb","fr", 189);
        data2.add("bb","dv", 450);
        data2.add("bb","dv4", 234);
        data2.add("ww","fr", 433);
        data2.add("ww","dv", 123);
        data2.add("ww","dv4", 183);
        areaChart.setDimension(500, 300);
        areaChart.setData(data2);
        frameLayout.addView(areaChart);

        // Harvey ball chart
        HarveyBallChart harveyBallChart = new HarveyBallChart(getApplicationContext());
        harveyBallChart.setDimension(150, 300);
        harveyBallChart.setData(25);
        harveyBallChart.setColor(Color.BLUE);
        frameLayout.addView(harveyBallChart);

        // Harvey ball chart
        HarveyBallChart harveyBallChart3 = new HarveyBallChart(getApplicationContext());
        harveyBallChart3.setDimension(150, 300);
        harveyBallChart3.setData(75);
        harveyBallChart3.setColor(Color.BLUE);
        frameLayout.addView(harveyBallChart3);

     // Delta ball chart
     DeltaChart deltaChart = new DeltaChart(getApplicationContext());
     deltaChart.setDimension(150, 300);
     deltaChart.setValues(-70,100);
     deltaChart.setColor(Color.BLUE);
     frameLayout.addView(deltaChart);

    }
}
