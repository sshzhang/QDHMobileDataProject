package com.Neo4j.MongoDBPac.qunaerJavaBean;

import java.util.List;

public class qHomeEntity {//整合房屋详细信息和list信息

    public qHomeDetail homeDetail;

    public List<qunaerRoomInfoList> roomInfoLists;

    public qHomeEntity() {
    }

    public qHomeEntity(qHomeDetail homeDetail, List<qunaerRoomInfoList> roomInfoLists) {
        this.homeDetail = homeDetail;
        this.roomInfoLists = roomInfoLists;
    }

    public qHomeDetail getHomeDetail() {
        return homeDetail;
    }

    public void setHomeDetail(qHomeDetail homeDetail) {
        this.homeDetail = homeDetail;
    }

    @Override
    public String toString() {
        return "qHomeEntity{" +
                "homeDetail=" + homeDetail +
                ", roomInfoLists=" + roomInfoLists +
                '}';
    }

    public List<qunaerRoomInfoList> getRoomInfoLists() {
        return roomInfoLists;
    }

    public void setRoomInfoLists(List<qunaerRoomInfoList> roomInfoLists) {
        this.roomInfoLists = roomInfoLists;
    }
}
