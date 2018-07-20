package com.adhamenaya.microchart.model;

import com.adhamenaya.microchart.view.Chart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.attr.data;

/**
 * Created by Admin on 18/12/2016.
 */

public class ChartData {

    public ChartData() {
    }

    // For axises labels
    public Map<String, Collection<String>> axises = new HashMap<String, Collection<String>>();

    // Max value
    public int max = Integer.MIN_VALUE;

    // Min value
    public int min = Integer.MAX_VALUE;

    // All items count
    public int itemsCount = 0;

    // For multi chart data
    private Map<String, Map<String, Integer>> data = new HashMap<String, Map<String, Integer>>();

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }


    public Map<String, Integer> getSingleData() {
        if (data != null && data.size() > 0)
            return data.get("single");
        return null;
    }

    public void addData(Map<String, Integer> data) {

        if (data != null) {
            addData("single", data);
        }
    }

    public void addData(String label, Map<String, Integer> data) {
        if (!this.data.containsKey(label))
            this.data.put(label, new HashMap<String, Integer>());
        this.data.get(label).putAll(data);
    }

    public Map<String, Map<String, Integer>> getMultiData() {
        return data;
    }

    public Map<String, Collection<String>> getAxises() {
        return axises;
    }

    public void setAxises(Map<String, Collection<String>> axises) {
        this.axises = axises;
    }

    public boolean checkAxisCorrectness(String axisKey) {
        return axises.containsKey(axisKey);
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

        add("single", key, value);
    }

    public void add(String label, String key, Integer value) {
        if (value > max) max = value;
        if (value < min) min = value;
        itemsCount++;
        if (data != null) {
            if (!data.containsKey(label))
                data.put(label, new HashMap<String, Integer>());
            data.get(label).put(key, value);
        }
    }

    public int getMin() {
        return min;
    }

    public int getItemsCount(String label) {
        return data.get(label).size();
    }

}
