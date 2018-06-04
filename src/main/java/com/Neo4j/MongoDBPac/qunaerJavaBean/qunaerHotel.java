package com.Neo4j.MongoDBPac.qunaerJavaBean;

import com.Neo4j.MongoDBPac.FiledMethodAnnotation;

public class qunaerHotel {//去哪儿酒店信息

    @FiledMethodAnnotation(MethodName = "set_id",ParameterType = String.class)
    public  String _id;
    @FiledMethodAnnotation(FieleReallyName = "shop_room_recommend_all",MethodName = "setShop_room_recommend_all",ParameterType = String.class)
    public String shop_room_recommend_all;
    @FiledMethodAnnotation(FieleReallyName = "shop_name",MethodName = "setShop_name",ParameterType = String.class)
    public String shop_name;
    @FiledMethodAnnotation(FieleReallyName = "shop_grade",MethodName = "setShop_grade",ParameterType = float.class)
    public float shop_grade;
    @FiledMethodAnnotation(FieleReallyName = "crawl_time",MethodName = "setCrawl_time",ParameterType = String.class)
    public String crawl_time;
    @FiledMethodAnnotation(FieleReallyName = "data_website",MethodName = "setData_website",ParameterType = String.class)
    public String data_website;
    @FiledMethodAnnotation(FieleReallyName = "shop_traffic",MethodName = "setShop_traffic",ParameterType = String.class)
    public String shop_traffic;
    @FiledMethodAnnotation(FieleReallyName = "shop_curr_url",MethodName = "setShop_curr_url",ParameterType = String.class)
    public String shop_curr_url;
    @FiledMethodAnnotation(FieleReallyName = "shop_facilities",MethodName = "setShop_facilities",ParameterType = String.class)
    public String shop_facilities;
    @FiledMethodAnnotation(FieleReallyName = "shop_comment_num",MethodName = "setShop_comment_num",ParameterType = int.class)
    public int  shop_comment_num;
    @FiledMethodAnnotation(FieleReallyName = "shop_rate",MethodName = "setShop_rate",ParameterType = String.class)
    public String shop_rate;
    @FiledMethodAnnotation(FieleReallyName = "shop_statistics",MethodName = "setShop_statistics",ParameterType = String.class)
    public String shop_statistics;
    @FiledMethodAnnotation(FieleReallyName = "data_source",MethodName = "setData_source",ParameterType = String.class)
    public String data_source;
    @FiledMethodAnnotation(FieleReallyName = "data_region",MethodName = "setData_region",ParameterType = String.class)
    public String data_region;
    @FiledMethodAnnotation(FieleReallyName = "shop_address",MethodName = "setShop_address",ParameterType = String.class)
    public String shop_address;
    @FiledMethodAnnotation(FieleReallyName = "shop_price",MethodName = "setShop_price",ParameterType = float.class)
    public float shop_price;

    @Override
    public String toString() {
        return "qunaerHotel{" +
                "_id='" + _id + '\'' +
                ", shop_room_recommend_all='" + shop_room_recommend_all + '\'' +
                ", shop_name='" + shop_name + '\'' +
                ", shop_grade=" + shop_grade +
                ", crawl_time='" + crawl_time + '\'' +
                ", data_website='" + data_website + '\'' +
                ", shop_traffic='" + shop_traffic + '\'' +
                ", shop_curr_url='" + shop_curr_url + '\'' +
                ", shop_facilities='" + shop_facilities + '\'' +
                ", shop_comment_num='" + shop_comment_num + '\'' +
                ", shop_rate='" + shop_rate + '\'' +
                ", shop_statistics='" + shop_statistics + '\'' +
                ", data_source='" + data_source + '\'' +
                ", data_region='" + data_region + '\'' +
                ", shop_address='" + shop_address + '\'' +
                ", shop_price=" + shop_price +
                '}';
    }

    public qunaerHotel() {
    }



    public qunaerHotel(String _id, String shop_room_recommend_all, String shop_name, float shop_grade, String crawl_time, String data_website, String shop_traffic, String shop_curr_url, String shop_facilities, int  shop_comment_num, String shop_rate, String shop_statistics, String data_source, String data_region, String shop_address, float shop_price) {
        this._id = _id;
        this.shop_room_recommend_all = shop_room_recommend_all;
        this.shop_name = shop_name;
        this.shop_grade = shop_grade;
        this.crawl_time = crawl_time;
        this.data_website = data_website;
        this.shop_traffic = shop_traffic;
        this.shop_curr_url = shop_curr_url;
        this.shop_facilities = shop_facilities;
        this.shop_comment_num = shop_comment_num;
        this.shop_rate = shop_rate;
        this.shop_statistics = shop_statistics;
        this.data_source = data_source;
        this.data_region = data_region;
        this.shop_address = shop_address;
        this.shop_price = shop_price;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getShop_room_recommend_all() {
        return shop_room_recommend_all;
    }

    public void setShop_room_recommend_all(String shop_room_recommend_all) {
        this.shop_room_recommend_all = shop_room_recommend_all;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public float getShop_grade() {
        return shop_grade;
    }

    public void setShop_grade(float shop_grade) {
        this.shop_grade = shop_grade;
    }

    public String getCrawl_time() {
        return crawl_time;
    }

    public void setCrawl_time(String crawl_time) {
        this.crawl_time = crawl_time;
    }

    public String getData_website() {
        return data_website;
    }

    public void setData_website(String data_website) {
        this.data_website = data_website;
    }

    public String getShop_traffic() {
        return shop_traffic;
    }

    public void setShop_traffic(String shop_traffic) {
        this.shop_traffic = shop_traffic;
    }

    public String getShop_curr_url() {
        return shop_curr_url;
    }

    public void setShop_curr_url(String shop_curr_url) {
        this.shop_curr_url = shop_curr_url;
    }

    public String getShop_facilities() {
        return shop_facilities;
    }

    public void setShop_facilities(String shop_facilities) {
        this.shop_facilities = shop_facilities;
    }

    public int getShop_comment_num() {
        return shop_comment_num;
    }

    public void setShop_comment_num(int  shop_comment_num) {
        this.shop_comment_num = shop_comment_num;
    }

    public String getShop_rate() {
        return shop_rate;
    }

    public void setShop_rate(String shop_rate) {
        this.shop_rate = shop_rate;
    }

    public String getShop_statistics() {
        return shop_statistics;
    }

    public void setShop_statistics(String shop_statistics) {
        this.shop_statistics = shop_statistics;
    }

    public String getData_source() {
        return data_source;
    }

    public void setData_source(String data_source) {
        this.data_source = data_source;
    }

    public String getData_region() {
        return data_region;
    }

    public void setData_region(String data_region) {
        this.data_region = data_region;
    }

    public String getShop_address() {
        return shop_address;
    }

    public void setShop_address(String shop_address) {
        this.shop_address = shop_address;
    }

    public float getShop_price() {
        return shop_price;
    }

    public void setShop_price(float shop_price) {
        this.shop_price = shop_price;
    }
}
