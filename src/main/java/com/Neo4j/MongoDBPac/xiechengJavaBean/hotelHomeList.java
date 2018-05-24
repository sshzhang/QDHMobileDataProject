package com.Neo4j.MongoDBPac.xiechengJavaBean;

import java.util.List;

public class hotelHomeList {//酒店所有的房型列表

    public RoomDetaill room_detail;

    public List<RoomInfoList> room_info_list;

    public hotelHomeList() {
    }

    public hotelHomeList(RoomDetaill room_detail, List<RoomInfoList> room_info_list) {
        this.room_detail = room_detail;
        this.room_info_list = room_info_list;
    }

    public RoomDetaill getRoom_detail() {
        return room_detail;
    }

    public void setRoom_detail(RoomDetaill room_detail) {
        this.room_detail = room_detail;
    }

    public List<RoomInfoList> getRoom_info_list() {
        return room_info_list;
    }

    public void setRoom_info_list(List<RoomInfoList> room_info_list) {
        this.room_info_list = room_info_list;
    }
}
