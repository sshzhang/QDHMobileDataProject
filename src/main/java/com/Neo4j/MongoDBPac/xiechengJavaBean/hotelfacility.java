package com.Neo4j.MongoDBPac.xiechengJavaBean;

import com.Neo4j.MongoDBPac.FiledNameReplace;

@FiledNameReplace(name = "酒店设施")
public class hotelfacility {//酒店设施
    @FiledNameReplace(name = "交通服务",MethodName="setTransportService",GetMethodName="getTransportService")
    public String transportService;
    @FiledNameReplace(name = "交通设施",MethodName = "setTrasnportFacility",GetMethodName = "getTrasnportFacility")
    public String trasnportFacility;
    @FiledNameReplace(name = "休闲娱乐",MethodName = "setLeisureEntertainment",GetMethodName = "getLeisureEntertainment")
    public String leisureEntertainment;
    @FiledNameReplace(name = "儿童设施",MethodName = "setChildrenFacility",GetMethodName = "getChildrenFacility")
    public String childrenFacility;
    @FiledNameReplace(name = "其他服务",MethodName = "setOtherService",GetMethodName = "getOtherService")
    public String otherService;
    @FiledNameReplace(name = "前台服务",MethodName = "setQiantaiService",GetMethodName = "getQiantaiService")
    public String qiantaiService;
    @FiledNameReplace(name = "商务服务",MethodName = "setBusinessServce",GetMethodName = "getBusinessServce")
    public String businessServce;
    @FiledNameReplace(name = "网络",MethodName ="setInternet" ,GetMethodName = "getInternet")
    public String Internet;
    @FiledNameReplace(name = "通用设施",MethodName = "setCommonFacility",GetMethodName = "getCommonFacility")
    public String commonFacility;
    @FiledNameReplace(name = "餐饮服务",MethodName = "setCateriService",GetMethodName = "getCateriService")
    public String cateriService;

    public hotelfacility() {
    }
    public hotelfacility(String transportService, String trasnportFacility, String leisureEntertainment, String childrenFacility, String otherService, String qiantaiService, String businessServce, String internet, String commonFacility, String cateriService) {
        this.transportService = transportService;
        this.trasnportFacility = trasnportFacility;
        this.leisureEntertainment = leisureEntertainment;
        this.childrenFacility = childrenFacility;
        this.otherService = otherService;
        this.qiantaiService = qiantaiService;
        this.businessServce = businessServce;
        Internet = internet;
        this.commonFacility = commonFacility;
        this.cateriService = cateriService;
    }

    public String getTransportService() {
        return transportService;
    }

    public void setTransportService(String transportService) {
        this.transportService = transportService;
    }

    public String getTrasnportFacility() {
        return trasnportFacility;
    }

    public void setTrasnportFacility(String trasnportFacility) {
        this.trasnportFacility = trasnportFacility;
    }

    public String getLeisureEntertainment() {
        return leisureEntertainment;
    }

    public void setLeisureEntertainment(String leisureEntertainment) {
        this.leisureEntertainment = leisureEntertainment;
    }

    public String getChildrenFacility() {
        return childrenFacility;
    }

    public void setChildrenFacility(String childrenFacility) {
        this.childrenFacility = childrenFacility;
    }

    public String getOtherService() {
        return otherService;
    }

    public void setOtherService(String otherService) {
        this.otherService = otherService;
    }

    public String getQiantaiService() {
        return qiantaiService;
    }

    public void setQiantaiService(String qiantaiService) {
        this.qiantaiService = qiantaiService;
    }

    public String getBusinessServce() {
        return businessServce;
    }

    public void setBusinessServce(String businessServce) {
        this.businessServce = businessServce;
    }

    public String getInternet() {
        return Internet;
    }

    public void setInternet(String internet) {
        Internet = internet;
    }

    public String getCommonFacility() {
        return commonFacility;
    }

    public void setCommonFacility(String commonFacility) {
        this.commonFacility = commonFacility;
    }

    public String getCateriService() {
        return cateriService;
    }

    public void setCateriService(String cateriService) {
        this.cateriService = cateriService;
    }
}
