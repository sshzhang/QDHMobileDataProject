package com.Neo4j.MongoDBPac.xiechengJavaBean;

import com.Neo4j.MongoDBPac.FiledNameReplace;

@FiledNameReplace(name = "酒店政策")
public class hotelPolicy {//酒店政策

    @FiledNameReplace(name = "儿童政策",MethodName = "setChildrenPolicy",GetMethodName = "getChildrenPolicy")
    public String childrenPolicy;
    @FiledNameReplace(name = "入住和离店",MethodName = "setEnterAndLeave",GetMethodName = "getEnterAndLeave")
    public String enterAndLeave;
    @FiledNameReplace(name = "可用支付方式",MethodName = "setAcceptPayWay",GetMethodName = "getAcceptPayWay")
    public String acceptPayWay;
    @FiledNameReplace(name = "宠物",MethodName = "setAcceptPet",GetMethodName = "getAcceptPet")
    public String acceptPet;
    @FiledNameReplace(name = "膳食安排",MethodName = "setDietArrange",GetMethodName = "getDietArrange")
    public String dietArrange;

    public hotelPolicy() {
    }
    public hotelPolicy(String childrenPolicy, String enterAndLeave, String acceptPayWay, String acceptPet, String dietArrange) {
        this.childrenPolicy = childrenPolicy;
        this.enterAndLeave = enterAndLeave;
        this.acceptPayWay = acceptPayWay;
        this.acceptPet = acceptPet;
        this.dietArrange = dietArrange;
    }

    public String getChildrenPolicy() {
        return childrenPolicy;
    }

    public void setChildrenPolicy(String childrenPolicy) {
        this.childrenPolicy = childrenPolicy;
    }

    public String getEnterAndLeave() {
        return enterAndLeave;
    }

    public void setEnterAndLeave(String enterAndLeave) {
        this.enterAndLeave = enterAndLeave;
    }

    public String getAcceptPayWay() {
        return acceptPayWay;
    }

    public void setAcceptPayWay(String acceptPayWay) {
        this.acceptPayWay = acceptPayWay;
    }

    public String getAcceptPet() {
        return acceptPet;
    }

    public void setAcceptPet(String acceptPet) {
        this.acceptPet = acceptPet;
    }

    public String getDietArrange() {
        return dietArrange;
    }

    public void setDietArrange(String dietArrange) {
        this.dietArrange = dietArrange;
    }
}
