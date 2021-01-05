package com.wangyh2116.sunnyweather.logic.model.realtimeresponse;

public class Result {
    private Realtime realtime;

    public Result(Realtime realtime) {
        this.realtime = realtime;
    }

    public Realtime getRealtime() {
        return realtime;
    }
}
