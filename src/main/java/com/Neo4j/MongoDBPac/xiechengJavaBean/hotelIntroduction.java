package com.Neo4j.MongoDBPac.xiechengJavaBean;

import com.Neo4j.MongoDBPac.FiledNameReplace;

import java.lang.invoke.MethodHandle;
import java.util.Map;

@FiledNameReplace(name = "酒店介绍")
public class hotelIntroduction {//酒店介绍
    @FiledNameReplace(datatypes = Map.class,MethodName = "setInfo",name = "info")
    public String info;
    @FiledNameReplace(MethodName = "setLabel",name = "label")
    public String label;
    @FiledNameReplace(MethodName = "setOther", name = "other")
    public String other;

    public void setOther(String other) {
        this.other = other;
    }

    public String getOther() {
        return other;
    }

    public  hotelIntroduction() {

    }
    public hotelIntroduction(String info, String label,String other) {
        this.info = info;
        this.label = label;
        this.other = other;
    }
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
