package com.wangyh2116.sunnyweather.logic.network;

import com.wangyh2116.sunnyweather.SunnyWeatherApplication;
import com.wangyh2116.sunnyweather.logic.model.dailyresponse.DailyResponse;
import com.wangyh2116.sunnyweather.logic.model.realtimeresponse.RealtimeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface WeatherService {
    @GET("v2.5/"+ SunnyWeatherApplication.TOKEN+"/{lng},{lat}/realtime.json")
    Call<RealtimeResponse> getRealtimeWeather(@Path("lng") String lng,@Path("lat") String lat);

    @GET("v2.5/"+ SunnyWeatherApplication.TOKEN+"/{lng},{lat}/daily.json")
    Call<DailyResponse> getDailyWeather(@Path("lng") String lng, @Path("lat") String lat);
}
