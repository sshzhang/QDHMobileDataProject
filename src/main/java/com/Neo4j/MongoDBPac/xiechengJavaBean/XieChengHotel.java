package com.Neo4j.MongoDBPac.xiechengJavaBean;

import com.Neo4j.MongoDBPac.FiledMethodAnnotation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//携程酒店数据集
public class XieChengHotel {
    @FiledMethodAnnotation(MethodName = "set_id",ParameterType =String.class)
    public String _id;//MongoDB的id
    @FiledMethodAnnotation(MethodName = "setCrawl_time",ParameterType =String.class)
    public String crawl_time;//爬取时间
    @FiledMethodAnnotation(MethodName = "setData_region",ParameterType =String.class)
    public String data_region;//数据区域
    @FiledMethodAnnotation(MethodName = "setData_source",ParameterType =String.class)
    public String data_source;//数据类型 酒店
    @FiledMethodAnnotation(MethodName = "setData_website",ParameterType =String.class)
    public String data_website;//数据来源站点
    @FiledMethodAnnotation(MethodName = "setShop_active_status",ParameterType =String.class)
    public String shop_active_status;//最新时间
    @FiledMethodAnnotation(MethodName = "setShop_address",ParameterType =String.class)
    public String shop_address;//酒店地址
    @FiledMethodAnnotation(MethodName = "setShop_around_facilities",ParameterType =String.class)
    public String shop_around_facilities;//酒店周边设施
    @FiledMethodAnnotation(MethodName = "setShop_category_name",ParameterType =String.class)
    public String shop_category_name;//酒店类别信息
    @FiledMethodAnnotation(MethodName = "setShop_comment_num",ParameterType =int.class)
    public int shop_comment_num;//酒店评论总数
    @FiledMethodAnnotation(MethodName = "setShop_grade",ParameterType =float.class)
    public float shop_grade;//酒店总体评分
    @FiledMethodAnnotation(MethodName = "setShop_grade_text",ParameterType =String.class)
    public String shop_grade_text;//酒店总体评价
    @FiledMethodAnnotation(MethodName = "setShop_id",ParameterType =int.class)
    public int  shop_id;//酒店id
    @FiledMethodAnnotation(MethodName = "setShop_img",ParameterType =String.class)
    public String shop_img;//酒店图片地址
    @FiledMethodAnnotation(MethodName = "setShop_intro",ParameterType =String.class)
    public String shop_intro;//酒店介绍
    @FiledMethodAnnotation(MethodName = "setShop_name",ParameterType =String.class)
    public String shop_name;//酒店名称
    @FiledMethodAnnotation(MethodName = "setShop_phone",ParameterType =String.class)
    public String shop_phone;//酒店电话
    @FiledMethodAnnotation(MethodName = "setShop_price",ParameterType =float.class)
    public float shop_price;//价格
    @FiledMethodAnnotation(MethodName = "setShop_rate",ParameterType =String.class)
    public String shop_rate;//酒店类似星际的皇冠数量
    @FiledMethodAnnotation(MethodName = "setShop_room_favourable",ParameterType =String.class)
    public String shop_room_favourable;//酒店优惠房型
    @FiledMethodAnnotation(MethodName = "setShop_room_recommend_all",ParameterType =String.class)
    public String shop_room_recommend_all;//酒店推荐房型
    @FiledMethodAnnotation(MethodName = "setShop_satisfaction_percent",ParameterType =String.class)
    public String shop_satisfaction_percent;//酒店的满意比率
    @FiledMethodAnnotation(MethodName = "setShop_statistics",ParameterType =String.class)
    public String shop_statistics;//酒店点评数据
    @FiledMethodAnnotation(MethodName = "setShop_url",ParameterType =String.class)
    public String shop_url;//酒店url

    public static String rate_desc[];
    static {
        rate_desc=new String[]{"一星级以下", "一星级", "二星级", "三星级", "四星级", "五星级", "六星级"};
    }


    public  XieChengHotel() {
    }
    public XieChengHotel(String _id, String crawl_time, String data_region, String data_source, String data_website, String shop_active_status, String shop_address, String shop_around_facilities, String shop_category_name, int shop_comment_num, float shop_grade, String shop_grade_text, int shop_id, String shop_img, String shop_intro, String shop_name, String shop_phone, float shop_price, String shop_rate, String shop_room_favourable, String shop_room_recommend_all, String shop_satisfaction_percent, String shop_statistics, String shop_url) {
        this._id = _id;
        this.crawl_time = crawl_time;
        this.data_region = data_region;
        this.data_source = data_source;
        this.data_website = data_website;
        this.shop_active_status = shop_active_status;
        this.shop_address = shop_address;
        this.shop_around_facilities = shop_around_facilities;
        this.shop_category_name = shop_category_name;
        this.shop_comment_num = shop_comment_num;
        this.shop_grade = shop_grade;
        this.shop_grade_text = shop_grade_text;
        this.shop_id = shop_id;
        this.shop_img = shop_img;
        this.shop_intro = shop_intro;
        this.shop_name = shop_name;
        this.shop_phone = shop_phone;
        this.shop_price = shop_price;
        this.shop_rate = shop_rate;
        this.shop_room_favourable = shop_room_favourable;
        this.shop_room_recommend_all = shop_room_recommend_all;
        this.shop_satisfaction_percent = shop_satisfaction_percent;
        this.shop_statistics = shop_statistics;
        this.shop_url = shop_url;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCrawl_time() {
        return crawl_time;
    }

    public void setCrawl_time(String crawl_time) {
        this.crawl_time = crawl_time;
    }

    public String getData_region() {
        return data_region;
    }

    public void setData_region(String data_region) {
        this.data_region = data_region;
    }

    public String getData_source() {
        return data_source;
    }

    public void setData_source(String data_source) {
        this.data_source = data_source;
    }

    public String getData_website() {
        return data_website;
    }

    public void setData_website(String data_website) {
        this.data_website = data_website;
    }

    public String getShop_active_status() {
        return shop_active_status;
    }

    public void setShop_active_status(String shop_active_status) {
        this.shop_active_status = shop_active_status;
    }

    public String getShop_address() {
        return shop_address;
    }

    public void setShop_address(String shop_address) {
        this.shop_address = shop_address;
    }

    public String getShop_around_facilities() {
        return shop_around_facilities;
    }

    public void setShop_around_facilities(String shop_around_facilities) {
        this.shop_around_facilities = shop_around_facilities;
    }

    public String getShop_category_name() {
        return shop_category_name;
    }

    public void setShop_category_name(String shop_category_name) {
        this.shop_category_name = shop_category_name;
    }

    public int getShop_comment_num() {
        return shop_comment_num;
    }

    public void setShop_comment_num(int shop_comment_num) {
        this.shop_comment_num = shop_comment_num;
    }

    public float getShop_grade() {
        return shop_grade;
    }

    public void setShop_grade(float shop_grade) {
        this.shop_grade = shop_grade;
    }

    public String getShop_grade_text() {
        return shop_grade_text;
    }

    public void setShop_grade_text(String shop_grade_text) {
        this.shop_grade_text = shop_grade_text;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public String getShop_img() {
        return shop_img;
    }

    public void setShop_img(String shop_img) {
        this.shop_img = shop_img;
    }

    public String getShop_intro() {
        return shop_intro;
    }

    public void setShop_intro(String shop_intro) {
        this.shop_intro = shop_intro;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_phone() {
        return shop_phone;
    }

    public void setShop_phone(String shop_phone) {
        this.shop_phone = shop_phone;
    }

    public float getShop_price() {
        return shop_price;
    }

    public void setShop_price(float shop_price) {
        this.shop_price = shop_price;
    }

    public String getShop_rate() {
        try{
            if (shop_rate != null &&(!"".equals(shop_rate))) {
                String substring = shop_rate.substring(shop_rate.length() - 1, shop_rate.length());
                int rate = Integer.parseInt(substring);

                return rate_desc[rate];
            }else{
                return "";
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(shop_rate);

        }
        return shop_rate;
    }

    public void setShop_rate(String shop_rate) {
        this.shop_rate = shop_rate;
    }

    public String getShop_room_favourable() {
        return shop_room_favourable;
    }

    public void setShop_room_favourable(String shop_room_favourable) {
        this.shop_room_favourable = shop_room_favourable;
    }

    public String getShop_room_recommend_all() {
        return shop_room_recommend_all;
    }

    public void setShop_room_recommend_all(String shop_room_recommend_all) {
        this.shop_room_recommend_all = shop_room_recommend_all;
    }

    public String getShop_satisfaction_percent() {
        return shop_satisfaction_percent;
    }

    public void setShop_satisfaction_percent(String shop_satisfaction_percent) {
        this.shop_satisfaction_percent = shop_satisfaction_percent;
    }

    public String getShop_statistics() {
        return shop_statistics;
    }

    public void setShop_statistics(String shop_statistics) {
        this.shop_statistics = shop_statistics;
    }

    public String getShop_url() {
        return shop_url;
    }

    public void setShop_url(String shop_url) {
        this.shop_url = shop_url;
    }


    public static void main(String... args) {

        String str = "\"床型\": \"多床\", \"宽带\": \"免费\", \"房价\": \"¥7556\", \"政策\": \"不可取消立即确认\", \"入住人数\": \"每间最多入住4人\", \"早餐\": \"每天四早\", \"满意度\": \"(\\\"约会春天\\\"情侣套餐) 礼预订满意度 97%\"";
        Pattern compile = Pattern.compile("(\"(nihao)\")+?:\\s(\"(.*?)\")+?");
        Matcher matcher =
                compile.matcher(str);
        matcher.find();
            System.out.println(matcher.group(2));
            System.out.println(matcher.group(4));
        Pattern compile1 = Pattern.compile("(?<=\"满意度\":)\\s\"(.*?)\"");
        Matcher matcher1 = compile1.matcher(str);
        matcher1.find();
        System.out.println(matcher1.group(1));


    }
}
