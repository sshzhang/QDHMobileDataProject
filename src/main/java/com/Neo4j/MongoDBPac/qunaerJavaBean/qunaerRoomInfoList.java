package com.Neo4j.MongoDBPac.qunaerJavaBean;

import com.Neo4j.MongoDBPac.FiledNameReplace;

public class qunaerRoomInfoList { //每种房屋类型的具体信息
    @FiledNameReplace(name = "来源", MethodName = "setComFrom",isIndex = true,index = 0)
    public String comFrom;//来源于那个网站
    @FiledNameReplace(name = "名称", MethodName = "setHomeName",isIndex = true,index = 1)
    public String HomeName;//名称
    @FiledNameReplace(name = "含早", MethodName = "setHaszao",isIndex = true,index = 2)
    public String haszao;//含早
    @FiledNameReplace(name = "退订政策", MethodName = "setTuidingzhence",isIndex = true,index = 3)
    public String tuidingzhence; //退订政策
    @FiledNameReplace(name = "日均价", MethodName = "setDayAveragePrice",isIndex = true,index = 4)
    public String dayAveragePrice;//日均价

    @Override
    public String toString() {
        return "qunaerRoomInfoList{" +
                "comFrom='" + comFrom + '\'' +
                ", HomeName='" + HomeName + '\'' +
                ", haszao='" + haszao + '\'' +
                ", tuidingzhence='" + tuidingzhence + '\'' +
                ", dayAveragePrice='" + dayAveragePrice + '\'' +
                '}';
    }

    public qunaerRoomInfoList() {
    }
    public qunaerRoomInfoList(String comFrom, String homeName, String haszao, String tuidingzhence, String dayAveragePrice) {
        this.comFrom = comFrom;
        HomeName = homeName;
        this.haszao = haszao;
        this.tuidingzhence = tuidingzhence;
        this.dayAveragePrice = dayAveragePrice;
    }

    public String getComFrom() {
        return comFrom;
    }

    public void setComFrom(String comFrom) {
        this.comFrom = comFrom;
    }

    public String getHomeName() {
        return HomeName;
    }

    public void setHomeName(String homeName) {
        HomeName = homeName;
    }

    public String getHaszao() {
        return haszao;
    }

    public void setHaszao(String haszao) {
        this.haszao = haszao;
    }

    public String getTuidingzhence() {
        return tuidingzhence;
    }

    public void setTuidingzhence(String tuidingzhence) {
        this.tuidingzhence = tuidingzhence;
    }

    public String getDayAveragePrice() {
        return dayAveragePrice;
    }

    public void setDayAveragePrice(String dayAveragePrice) {
        this.dayAveragePrice = dayAveragePrice;
    }
}
