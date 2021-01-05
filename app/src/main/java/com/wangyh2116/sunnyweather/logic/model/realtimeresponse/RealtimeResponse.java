package com.wangyh2116.sunnyweather.logic.model.realtimeresponse;

public class RealtimeResponse {
    private String status;
    private Result result;
    private String error;
    public RealtimeResponse(String status, Result result) {
        this.status = status;
        this.result = result;
    }

    public RealtimeResponse(String status, String msg,Result result ) {
        this.status = status;
        this.result = result;
        this.error = msg;
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

    public Result getResult() {
        return result;
    }
}
