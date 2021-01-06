package com.wangyh2116.sunnyweather;

import android.app.Application;
import android.content.Context;

public class SunnyWeatherApplication extends Application {
    private static Context context;
    final public static String TOKEN="EtDctMqJlaFYvJbU";
    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
    }
    public static Context getContext(){
        return context;
    }
}
