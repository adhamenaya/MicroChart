package com.adhamenaya.microchart.model;

import com.adhamenaya.microchart.view.Chart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 18/12/2016.
 */

public class ChartData {

    public ChartData() {
    }

    // For axises labels
    public Map<String, Collection<String>> axises = new HashMap<String, Collection<String>>();

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    // Max column
    public int max = 0;

    public Map<String, Integer> getData() {
        return data;
    }

    public void setData(Map<String, Integer> data) {
        this.data = data;
    }

    public Map<String, Collection<String>> getAxises() {
        return axises;
    }

    public void setAxises(Map<String, Collection<String>> axises) {
        this.axises = axises;
    }

    // For chart data
    public Map<String, Integer> data = new HashMap<String, Integer>();

    public boolean checkAxisCorrectness(String axisKey) {
        return axises.containsKey(axisKey) ? true : false;
    }

    public void setAxis(String name, Collection<String> values) {
        if (axises.containsKey(name)) {
            axises.get(name).addAll(values);
        } else {
            axises.put(name, new ArrayList<String>());
            setAxis(name, values);
        }
    }

    public void add(String key, Integer value) {
        if (value > max) max = value;
        data.put(key, value);
    }
}
