package com.wangyh2116.sunnyweather.logic.model.dailyresponse;

import java.util.Date;

public class Skycon {
    private String value;
    private Date date;

    public Skycon(String value, Date date) {
        this.value = value;
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public Date getDate() {
        return date;
    }
}
