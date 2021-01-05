package com.wangyh2116.sunnyweather.logic.model;

import com.wangyh2116.sunnyweather.logic.model.dailyresponse.Daily;
import com.wangyh2116.sunnyweather.logic.model.realtimeresponse.Realtime;

public class Weather {
    private Realtime realtime;
    private Daily daily;



    private String status="ok";
    private String msg=null;
    public Weather(Realtime realtime, Daily daily) {
        this.realtime = realtime;
        this.daily = daily;
    }
    public Weather(String status,String msg,Realtime realtime, Daily daily){
        this.status=status;
        this.msg=msg;
    }
    public boolean isOk(){
        return status.equals("ok");
    }
    public String getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
    public Realtime getRealtime() {
        return realtime;
    }

    public Daily getDaily() {
        return daily;
    }
}
