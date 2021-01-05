package com.wangyh2116.sunnyweather.logic.model.realtimeresponse;

import com.google.gson.annotations.SerializedName;

public class AirQuality {
    @SerializedName("aqi")
    private Aqi aqi;

    public AirQuality(Aqi aqi) {
        this.aqi = aqi;
    }

    public Aqi getAqi() {
        return aqi;
    }
}
