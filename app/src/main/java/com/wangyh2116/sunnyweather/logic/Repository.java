package com.wangyh2116.sunnyweather.logic;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.wangyh2116.sunnyweather.logic.dao.PlaceDao;
import com.wangyh2116.sunnyweather.logic.model.Place;
import com.wangyh2116.sunnyweather.logic.model.PlaceResponse;
import com.wangyh2116.sunnyweather.logic.model.Weather;
import com.wangyh2116.sunnyweather.logic.model.dailyresponse.DailyResponse;
import com.wangyh2116.sunnyweather.logic.model.realtimeresponse.RealtimeResponse;
import com.wangyh2116.sunnyweather.logic.network.SunnyWeatherNetwork;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    public static  LiveData<PlaceResponse> searchPlaces(final String query) {
        final MutableLiveData<PlaceResponse> liveData = new MutableLiveData<>();
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    PlaceResponse placeResponse = SunnyWeatherNetwork.searchPlace(query);
                    liveData.postValue(placeResponse);
                }catch (IOException e){
                    PlaceResponse placeResponse =new PlaceResponse("failed","网络出错",null);
                    liveData.postValue(placeResponse);
                }
            }
        });
        t1.start();
        return liveData;
    }
    public static LiveData<Weather> refreshWeather(final String lng, final String lat){
        final MutableLiveData<Weather> liveData = new MutableLiveData<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<RealtimeResponse> realtimeResponse=new ArrayList<>();
                final List<DailyResponse> dailyResponse=new ArrayList<>();
                Thread t1=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            realtimeResponse.add(SunnyWeatherNetwork.getRealtimeWeather(lng,lat));
                        } catch (IOException e) {
                            RealtimeResponse realtimeResponse1=new RealtimeResponse("failed","网络出错",null);
                            realtimeResponse.add(realtimeResponse1);
                        }
                    }
                });
                Thread t2=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            dailyResponse.add(SunnyWeatherNetwork.getDailyWeather(lng,lat));
                        } catch (IOException e) {
                            DailyResponse dailyResponse1=new DailyResponse("failed","网络出错",null);
                            dailyResponse.add(dailyResponse1);
                        }
                    }
                });
                t1.start();t2.start();
                try {
                    t1.join();
                    t2.join();
                } catch (InterruptedException e) {
                    Log.e("Repository",e.toString());
                }
                try {
                    if(dailyResponse.size()==1&&dailyResponse.get(0).isOk()&&realtimeResponse.size()==1&&realtimeResponse.get(0).isOk()){
                        liveData.postValue(new Weather(realtimeResponse.get(0).getResult().getRealtime(),dailyResponse.get(0).getResult().getDaily()));
                    }else {
                        liveData.postValue(new Weather("failed","网络出错",null,null));
                    }
                }catch (NullPointerException e){
                    liveData.postValue(new Weather("failed","系统出错",null,null));
                }
            }
        }).start();
        return liveData;
    }

    public static void savePlace(Place place){
        PlaceDao.savePlace(place);
    }
    public static Place getPlace(){
        return PlaceDao.getPlace();
    }
    public static Boolean isPlaceSaved(){
        return PlaceDao.isPlaceSaved();
    }
}

