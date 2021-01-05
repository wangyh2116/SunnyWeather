package com.wangyh2116.sunnyweather.logic.model;

import java.util.List;

public class PlaceResponse {
    private String status;
    private String error =null;
    private List<Place> places;
    public PlaceResponse(String status, List<Place> places) {
        this.status = status;
        this.places = places;
    }
    public PlaceResponse(String status,String msg,List<Place> places) {
        this.status = status;
        this.error =msg;
        this.places = places;
    }

    public String getError() {
        return error;
    }

    public boolean isOk(){
        return getStatus().equals("ok");
    }
    public String getStatus() {
        return status;
    }

    public List<Place> getPlaces() {
        return places;
    }
}
