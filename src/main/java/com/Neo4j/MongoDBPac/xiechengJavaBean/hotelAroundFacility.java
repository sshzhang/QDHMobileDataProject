package com.Neo4j.MongoDBPac.xiechengJavaBean;

import com.Neo4j.MongoDBPac.FiledNameReplace;
@FiledNameReplace(name = "酒店周边设施")
public class hotelAroundFacility {//酒店周边设施

    @FiledNameReplace(name = "景点", MethodName = "setSight",GetMethodName = "getSight")
    public String sight;
    @FiledNameReplace(name = "娱乐", MethodName = "setEnterTainment",GetMethodName = "getEnterTainment")
    public String enterTainment;
    @FiledNameReplace(name = "餐饮", MethodName = "setCatering",GetMethodName = "getCatering")
    public String catering;
    @FiledNameReplace(name = "购物", MethodName = "setShopping",GetMethodName = "getShopping")
    public String shopping;

    public hotelAroundFacility() {
    }
    public hotelAroundFacility(String sight, String enterTainment, String catering, String shopping) {
        this.sight = sight;
        this.enterTainment = enterTainment;
        this.catering = catering;
        this.shopping = shopping;
    }

    public String getSight() {
        return sight;
    }

    public void setSight(String sight) {
        this.sight = sight;
    }

    public String getEnterTainment() {
        return enterTainment;
    }

    public void setEnterTainment(String enterTainment) {
        this.enterTainment = enterTainment;
    }

    public String getCatering() {
        return catering;
    }

    public void setCatering(String catering) {
        this.catering = catering;
    }

    public String getShopping() {
        return shopping;
    }

    public void setShopping(String shopping) {
        this.shopping = shopping;
    }
}
