package com.wangyh2116.sunnyweather.ui.weather;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.wangyh2116.sunnyweather.logic.Repository;
import com.wangyh2116.sunnyweather.logic.model.Location;
import com.wangyh2116.sunnyweather.logic.model.Weather;

public class WeatherViewModel extends ViewModel {
    private MutableLiveData<Location> locationMutableLiveData=new MutableLiveData<>();
    private String locationLng="";
    private String locationLat="";
    private String placeName="";



    private LiveData<Weather> weatherLiveData = Transformations.switchMap(locationMutableLiveData, new Function<Location, LiveData<Weather>>() {
        @Override
        public LiveData<Weather> apply(Location location) {
            return Repository.refreshWeather(location.getLng(),location.getLat());
        }
    });
    public void refreshWeather(String lng, String lat){
        locationMutableLiveData.setValue(new Location(lng,lat));
    }
    public void setLocationLng(String locationLng) {
        this.locationLng = locationLng;
    }

    public void setLocationLat(String locationLat) {
        this.locationLat = locationLat;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
    public String getLocationLng() {
        return locationLng;
    }

    public String getLocationLat() {
        return locationLat;
    }

    public String getPlaceName() {
        return placeName;
    }

    public LiveData<Weather> getWeatherLiveData() {
        return weatherLiveData;
    }
}
