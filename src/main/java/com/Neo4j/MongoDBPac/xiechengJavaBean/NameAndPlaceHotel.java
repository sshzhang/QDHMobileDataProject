package com.Neo4j.MongoDBPac.xiechengJavaBean;


import com.Neo4j.MongoDBPac.FiledNameReplace;

//携程酒店所有的名称和地点信息
public class NameAndPlaceHotel {
    @FiledNameReplace(name = "url", MethodName = "setUrl")
    private String url;
    @FiledNameReplace(name = "name", MethodName = "setName")
    private String name;
    @FiledNameReplace(name = "place", MethodName = "setPlace")
    private String place;

    public NameAndPlaceHotel() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "NameAndPlaceHotel{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", place='" + place + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public NameAndPlaceHotel(String url, String name, String place) {
        this.url = url;
        this.name = name;
        this.place = place;
    }

}
