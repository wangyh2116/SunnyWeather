package com.wangyh2116.sunnyweather.logic.dao;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.wangyh2116.sunnyweather.SunnyWeatherApplication;
import com.wangyh2116.sunnyweather.logic.model.Place;

public class PlaceDao {
    public static void savePlace(Place place){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("place",new Gson().toJson(place));
        editor.apply();
    }

    public static Place getPlace(){
        String placeJson = sharedPreferences.getString("place","");
        return new Gson().fromJson(placeJson,Place.class);
    }
    public static Boolean isPlaceSaved(){
        return sharedPreferences.contains("place");
    }
    private static SharedPreferences sharedPreferences =
            SunnyWeatherApplication.getContext().getSharedPreferences("sunny_weather", Context.MODE_PRIVATE);
}
