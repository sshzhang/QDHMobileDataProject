package com.qdh.Beans;

//数据解析是用到Bean对象
public class YkrsBean {
    private long id;
    private int kpiId;
    private String kpiTime;
    private int kpiValue;
    private int spotId;

    public YkrsBean() {
    }


    public YkrsBean(long id, int kpiId, String kpiTime, int kpiValue, int spotId) {
        this.id = id;
        this.kpiId = kpiId;
        this.kpiTime = kpiTime;
        this.kpiValue = kpiValue;
        this.spotId = spotId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getKpiId() {
        return kpiId;
    }

    public void setKpiId(int kpiId) {
        this.kpiId = kpiId;
    }

    public String getKpiTime() {
        return kpiTime;
    }

    public void setKpiTime(String kpiTime) {
        this.kpiTime = kpiTime;
    }

    public int getKpiValue() {
        return kpiValue;
    }

    public void setKpiValue(int kpiValue) {
        this.kpiValue = kpiValue;
    }

    public int getSpotId() {
        return spotId;
    }

    public void setSpotId(int spotId) {
        this.spotId = spotId;
    }
}
