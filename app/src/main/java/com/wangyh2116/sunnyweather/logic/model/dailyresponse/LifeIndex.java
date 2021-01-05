package com.wangyh2116.sunnyweather.logic.model.dailyresponse;

import java.util.List;

public class LifeIndex {
    private List<LifeDescription> coldRisk;
    private List<LifeDescription> carWashing;
    private List<LifeDescription> ultraviolet;
    private List<LifeDescription> dressing;

    public LifeIndex(List<LifeDescription> coldRisk, List<LifeDescription> carWashing, List<LifeDescription> ultraviolet, List<LifeDescription> dressing) {
        this.coldRisk = coldRisk;
        this.carWashing = carWashing;
        this.ultraviolet = ultraviolet;
        this.dressing = dressing;
    }

    public List<LifeDescription> getColdRisk() {
        return coldRisk;
    }

    public List<LifeDescription> getCarWashing() {
        return carWashing;
    }

    public List<LifeDescription> getUltraviolet() {
        return ultraviolet;
    }

    public List<LifeDescription> getDressing() {
        return dressing;
    }
}
