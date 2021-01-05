package com.wangyh2116.sunnyweather.logic.model;

public class Location {
    private String lng;
    private String lat;

    public Location(String lng, String lat) {
        this.lng = lng;
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public String getLat() {
        return lat;
    }
}
