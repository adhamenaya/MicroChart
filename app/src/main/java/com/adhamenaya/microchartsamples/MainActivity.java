package com.adhamenaya.microchartsamples;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.adhamenaya.microchart.model.ChartData;
import com.adhamenaya.microchart.view.BarChart;
import com.adhamenaya.microchart.view.PieChart;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        LinearLayout frameLayout = (LinearLayout) findViewById(R.id.lyt_main);

        PieChart pieChart = new PieChart(getApplicationContext());
        pieChart.setMax(100);
        pieChart.setData(30);
        pieChart.setColor(Color.GREEN);
        pieChart.setDimension(300, 300);
        frameLayout.addView(pieChart);

        BarChart barChart = new BarChart(getApplicationContext());
        barChart.setDimension(500, 200);
        barChart.setBackgroundColor(Color.GRAY);
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
        data.add("c38", 250);
        data.add("c58", 650);
        data.add("c68", 850);
        data.add("d8", 340);
        data.add("e8", 300);
        data.add("e78", 300);
        data.add("b276", 200);
        data.add("c477", 350);
        data.add("7c38", 250);
        data.add("c758", 650);
        data.add("c678", 850);
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
        barChart.setData(data);
        barChart.setColor(Color.BLUE);
        frameLayout.addView(barChart);

    }
}
