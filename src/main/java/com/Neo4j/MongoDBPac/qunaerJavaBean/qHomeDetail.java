package com.Neo4j.MongoDBPac.qunaerJavaBean;

import com.Neo4j.MongoDBPac.FiledNameReplace;

public class qHomeDetail  {//房型信息
    @FiledNameReplace(name = "room_area", MethodName = "setMianji",datatypes = String.class, GetMethodName = "getMianji",isRegex = true,RegexExpression = "(?<=面积)([0-9]*-*[0-9]*㎡)")
    public String mianji;
    @FiledNameReplace(name = "room_area", MethodName = "setLouceng",datatypes = String.class, GetMethodName = "getLouceng",isRegex = true,RegexExpression = "(?<=位于)([0-9]*-*[0-9]*层)")
    public String louceng;
    @FiledNameReplace(name = "room_area", MethodName = "setChuangType", datatypes = String.class,GetMethodName = "getChungType",isRegex = true,RegexExpression = "[0-9]张(.*?床)(和[0-9]张(.*?床))*")
    public String chuangType;
    @FiledNameReplace(name = "room_area", MethodName = "setHasWindows", datatypes = String.class,GetMethodName = "getHashWindows",isRegex = true,RegexExpression = "独立卫浴")
    public String hasWindows;
    @FiledNameReplace(name = "room_area", MethodName = "setHomeName", datatypes = String.class,GetMethodName = "getHomeName", isRegex = true, RegexExpression = "有窗")
    public String HomeName;

    @FiledNameReplace(name = "facily_list", MethodName = "setRuzhurenshu",datatypes = String.class, GetMethodName = "getRuzhurenshu",isKey = true ,key="人")
    public String ruzhurenshu;
    @FiledNameReplace(name = "facily_list", MethodName = "setKuangdai",datatypes = String.class, GetMethodName = "getKuangdai",isKey = true ,key="宽带")
    public String kuangdai;
    @FiledNameReplace(name = "facily_list", MethodName = "setWifi",datatypes = String.class, GetMethodName = "getWifi",isKey= true ,key="WIFI")
    public String wifi;

    public String getRuzhurenshu() {
        return ruzhurenshu;
    }

    public void setRuzhurenshu(String ruzhurenshu) {
        this.ruzhurenshu = ruzhurenshu;
    }

    public String getKuangdai() {
        return kuangdai;
    }

    public void setKuangdai(String kuangdai) {
        this.kuangdai = kuangdai;
    }

    public String getWifi() {
        return wifi;
    }

    @Override
    public String toString() {
        return "qHomeDetail{" +
                "mianji='" + mianji + '\'' +
                ", louceng='" + louceng + '\'' +
                ", chuangType='" + chuangType + '\'' +
                ", hasWindows='" + hasWindows + '\'' +
                ", HomeName='" + HomeName + '\'' +
                ", ruzhurenshu='" + ruzhurenshu + '\'' +
                ", kuangdai='" + kuangdai + '\'' +
                ", wifi='" + wifi + '\'' +
                '}';
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public qHomeDetail() {
    }
    public qHomeDetail(String mianji, String louceng, String chuangType, String hasWindows, String homeName,String ruzhurenshu,String kuangdai,String wifi) {
        this.mianji = mianji;
        this.louceng = louceng;
        this.chuangType = chuangType;
        this.hasWindows = hasWindows;
        this.HomeName = homeName;
        this.ruzhurenshu = ruzhurenshu;
        this.kuangdai = kuangdai;
        this.wifi = wifi;
    }

    public String getMianji() {
        return mianji;
    }

    public void setMianji(String mianji) {
        this.mianji = mianji;
    }

    public String getLouceng() {
        return louceng;
    }

    public void setLouceng(String louceng) {
        this.louceng = louceng;
    }

    public String getChuangType() {
        return chuangType;
    }

    public void setChuangType(String chuangType) {
        this.chuangType = chuangType;
    }

    public String getHasWindows() {
        return hasWindows;
    }

    public void setHasWindows(String hasWindows) {
        this.hasWindows = hasWindows;
    }

    public String getHomeName() {
        return HomeName;
    }

    public void setHomeName(String homeName) {
        HomeName = homeName;
    }
}
