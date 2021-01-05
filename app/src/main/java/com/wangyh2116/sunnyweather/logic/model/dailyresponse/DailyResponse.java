package com.wangyh2116.sunnyweather.logic.model.dailyresponse;



public class DailyResponse {
    private String status;
    private Result result;
    private String error =null;
    public DailyResponse(String status, Result result) {
        this.status = status;
        this.result = result;
    }
    public DailyResponse(String status,String msg, Result result) {
        this.status = status;
        this.error =msg;
        this.result = result;
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
