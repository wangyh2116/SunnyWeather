package com.wangyh2116.sunnyweather.logic.network;

import com.wangyh2116.sunnyweather.logic.model.PlaceResponse;
import com.wangyh2116.sunnyweather.logic.model.dailyresponse.DailyResponse;
import com.wangyh2116.sunnyweather.logic.model.realtimeresponse.RealtimeResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class SunnyWeatherNetwork {
    private static PlaceService placeService=ServiceCreator.create(PlaceService.class);
    private static WeatherService weatherService=ServiceCreator.create(WeatherService.class);
    public static PlaceResponse searchPlace(String query) throws IOException{
            return await(placeService.searchPlaces(query));
    }
    public static RealtimeResponse getRealtimeWeather(String lng, String lat) throws IOException{
        return await(weatherService.getRealtimeWeather(lng,lat));
    }
    public static DailyResponse getDailyWeather(String lng, String lat) throws IOException{
        return await(weatherService.getDailyWeather(lng,lat));
    }
    private static <T> T await(Call<T> call) throws IOException{
        T result=null;
        try{
            Response<T>response=call.execute();
            result=response.body();
            if(result == null){
                throw new SearchException("responce.body() is null!");
            }
        }catch (IOException e){
            throw new SearchException("call.execute() fail!");
        }
        return  result;
    }
    public static void main(String[] args){
        try {
            DailyResponse rr= getDailyWeather("104.066801","30.572815");
            System.out.println("");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
