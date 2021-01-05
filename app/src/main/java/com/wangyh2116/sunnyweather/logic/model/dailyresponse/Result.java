package com.wangyh2116.sunnyweather.logic.model.dailyresponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    private Daily daily;

    public Result(Daily daily) {
        this.daily = daily;
    }

    public Daily getDaily() {
        return daily;
    }
}
