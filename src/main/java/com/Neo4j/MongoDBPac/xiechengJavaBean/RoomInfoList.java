package com.Neo4j.MongoDBPac.xiechengJavaBean;

import com.Neo4j.MongoDBPac.FiledNameReplace;

public class RoomInfoList {//房间的信息列表

    @FiledNameReplace(name = "入住人数", MethodName = "setRuzhurenshu")
    public String ruzhurenshu;
    @FiledNameReplace(name = "宽带", MethodName = "setKuangdai")
    public String kuangdai;
    @FiledNameReplace(name = "床型", MethodName = "setChuangxing")
    public String chuangxing;

    @Override
    public String toString() {
        return "RoomInfoList{" +
                "ruzhurenshu='" + ruzhurenshu + '\'' +
                ", kuangdai='" + kuangdai + '\'' +
                ", chuangxing='" + chuangxing + '\'' +
                ", fangjia='" + fangjia + '\'' +
                ", zhengce='" + zhengce + '\'' +
                ", zaocan='" + zaocan + '\'' +
                ", mayidu='" + mayidu + '\'' +
                '}';
    }

    @FiledNameReplace(name = "房价", MethodName = "setFangjia")
    public String fangjia;

    public String getFangjia() {
        return fangjia;
    }

    public void setFangjia(String fangjia) {
        this.fangjia = fangjia;
    }

    @FiledNameReplace(name = "政策", MethodName = "setZhengce")
    public String zhengce;
    @FiledNameReplace(name = "早餐", MethodName = "setZaocan")
    public String zaocan;
    @FiledNameReplace(name = "满意度", MethodName = "setMayidu")
    public String mayidu;

    public RoomInfoList() {
    }

    public RoomInfoList(String ruzhurenshu, String kuangdai, String chuangxing, String fangjia, String zhengce, String zaocan, String mayidu) {
        this.ruzhurenshu = ruzhurenshu;
        this.kuangdai = kuangdai;
        this.chuangxing = chuangxing;
        this.fangjia = fangjia;
        this.zhengce = zhengce;
        this.zaocan = zaocan;
        this.mayidu = mayidu;
    }

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

    public String getChuangxing() {
        return chuangxing;
    }

    public void setChuangxing(String chuangxing) {
        this.chuangxing = chuangxing;
    }

    public String getZhengce() {
        return zhengce;
    }

    public void setZhengce(String zhengce) {
        this.zhengce = zhengce;
    }

    public String getZaocan() {
        return zaocan;
    }

    public void setZaocan(String zaocan) {
        this.zaocan = zaocan;
    }

    public String getMayidu() {
        return mayidu;
    }

    public void setMayidu(String mayidu) {
        this.mayidu = mayidu;
    }
}
