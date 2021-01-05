package com.wangyh2116.sunnyweather.logic.model.realtimeresponse;

import com.google.gson.annotations.SerializedName;

public class Realtime {
    private String skycon;
    private Float temperature;
    @SerializedName("air_quality")
    private AirQuality airQuality;

    public Realtime(String skycon, Float temperature, AirQuality airQuality) {
        this.skycon = skycon;
        this.temperature = temperature;
        this.airQuality = airQuality;
    }

    public String getSkycon() {
        return skycon;
    }

    public Float getTemperature() {
        return temperature;
    }

    public AirQuality getAirQuality() {
        return airQuality;
    }
}
