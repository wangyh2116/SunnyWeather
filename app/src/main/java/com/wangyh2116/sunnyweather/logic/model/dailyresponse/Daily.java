package com.wangyh2116.sunnyweather.logic.model.dailyresponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Daily {
    private List<Temperature> temperature;
    private List<Skycon> skycon;
    @SerializedName("life_index")
    private LifeIndex lifeIndex;

    public Daily(List<Temperature> temperature, List<Skycon> skycon, LifeIndex lifeIndex) {
        this.temperature = temperature;
        this.skycon = skycon;
        this.lifeIndex = lifeIndex;
    }

    public List<Temperature> getTemperature() {
        return temperature;
    }

    public List<Skycon> getSkycon() {
        return skycon;
    }

    public LifeIndex getLifeIndex() {
        return lifeIndex;
    }
}
