package com.Neo4j.MongoDBPac.xiechengJavaBean;

import com.Neo4j.MongoDBPac.FiledNameReplace;
public class RoomDetaill {
    @FiledNameReplace(name = "便利设施", MethodName = "setConvenient_facility")
    public String convenient_facility;
    @FiledNameReplace(name = "可加床", MethodName = "setIncreasebed")
    public String increasebed;
    @FiledNameReplace(name = "不可加床", MethodName = "setDecreasebed")
    public String decreasebed;
    @FiledNameReplace(name = "床型", MethodName = "setBedType")
    public String bedType;
    @FiledNameReplace(name = "建筑面积", MethodName = "setBuildingArea")
    public String buildingArea;
    @FiledNameReplace(name = "房型", MethodName = "setHomeType")
    public String homeType;
    @FiledNameReplace(name = "楼层", MethodName = "setFloor")
    public String floor;
    @FiledNameReplace(name = "浴室", MethodName = "setBedthroom")
    public String bedthroom;
    @FiledNameReplace(name = "*无烟*", MethodName = "setHomeArrangeNoSmorkingPlace")
    public String homeArrangeNoSmorkingPlace;

    public RoomDetaill() {
    }
    public RoomDetaill(String convenient_facility, String increasebed, String decreasebed,String bedType, String buildingArea, String homeType, String floor, String bedthroom, String homeArrangeNoSmorkingPlace) {
        this.convenient_facility = convenient_facility;
        this.increasebed = increasebed;
        this.decreasebed = decreasebed;
        this.bedType = bedType;
        this.buildingArea = buildingArea;
        this.homeType = homeType;
        this.floor = floor;
        this.bedthroom = bedthroom;
        this.homeArrangeNoSmorkingPlace = homeArrangeNoSmorkingPlace;
    }

    public String getDecreasebed() {
        return decreasebed;
    }

    public void setDecreasebed(String decreasebed) {
        this.decreasebed = decreasebed;
    }

    public String getConvenient_facility() {
        return convenient_facility;
    }

    public void setConvenient_facility(String convenient_facility) {
        this.convenient_facility = convenient_facility;
    }

    public String getIncreasebed() {
        return increasebed;
    }

    public void setIncreasebed(String increasebed) {
        this.increasebed = increasebed;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public String getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(String buildingArea) {
        this.buildingArea = buildingArea;
    }

    public String getHomeType() {
        return homeType;
    }

    public void setHomeType(String homeType) {
        this.homeType = homeType;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getBedthroom() {
        return bedthroom;
    }

    public void setBedthroom(String bedthroom) {
        this.bedthroom = bedthroom;
    }

    public String getHomeArrangeNoSmorkingPlace() {
        return homeArrangeNoSmorkingPlace;
    }

    public void setHomeArrangeNoSmorkingPlace(String homeArrangeNoSmorkingPlace) {
        this.homeArrangeNoSmorkingPlace = homeArrangeNoSmorkingPlace;
    }
}
