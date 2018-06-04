package com.Neo4j.MongoDBPac.qunaerJavaBean;

import java.util.List;
import java.util.Map;

public class qunaerCompleteHotel {//预处理完成之后的数据信息


    //mongoDB中图数据库中信息
    public qunaerHotel qhotel;
    //周边设施
    public qAroundFacility aroundFacility;
    //设施概况  包含基本信息 和设施信息
    public Map<String, String> params;
    //评论信息统计
    public qevaluate qvaluate;
    //房型基本信息
    public List<qHomeEntity> qHomeEntityList;

    public qunaerCompleteHotel() {
    }

    public qunaerCompleteHotel(qunaerHotel qhotel, qAroundFacility aroundFacility, Map<String, String> params, qevaluate qvaluate, List<qHomeEntity> qHomeEntityList) {
        this.qhotel = qhotel;
        this.aroundFacility = aroundFacility;
        this.params = params;
        this.qvaluate = qvaluate;
        this.qHomeEntityList = qHomeEntityList;
    }

    public qunaerHotel getQhotel() {
        return qhotel;
    }

    public void setQhotel(qunaerHotel qhotel) {
        this.qhotel = qhotel;
    }

    public qAroundFacility getAroundFacility() {
        return aroundFacility;
    }

    public void setAroundFacility(qAroundFacility aroundFacility) {
        this.aroundFacility = aroundFacility;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public qevaluate getQvaluate() {
        return qvaluate;
    }

    public void setQvaluate(qevaluate qvaluate) {
        this.qvaluate = qvaluate;
    }

    public List<qHomeEntity> getqHomeEntityList() {
        return qHomeEntityList;
    }

    public void setqHomeEntityList(List<qHomeEntity> qHomeEntityList) {
        this.qHomeEntityList = qHomeEntityList;
    }
}
