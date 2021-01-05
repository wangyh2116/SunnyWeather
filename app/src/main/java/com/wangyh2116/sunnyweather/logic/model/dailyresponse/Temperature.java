package com.wangyh2116.sunnyweather.logic.model.dailyresponse;

public class Temperature {
    private Float max;
    private Float min;

    public Temperature(Float max, Float min) {
        this.max = max;
        this.min = min;
    }

    public Float getMax() {
        return max;
    }

    public Float getMin() {
        return min;
    }
}
