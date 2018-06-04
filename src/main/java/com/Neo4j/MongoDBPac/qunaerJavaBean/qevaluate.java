package com.Neo4j.MongoDBPac.qunaerJavaBean;

import com.Neo4j.MongoDBPac.FiledNameReplace;

public class qevaluate {//去哪儿评价

    @FiledNameReplace(name = "好评", MethodName = "setGood", datatypes = int.class)
    public int good;
    @FiledNameReplace(name = "中评", MethodName = "setCommon", datatypes = int.class)
    public int common;
    @FiledNameReplace(name = "差评", MethodName = "setBad", datatypes = int.class)
    public int bad;
    @Override
    public String toString() {
        return "qevaluate{" +
                "good=" + good +
                ", common=" + common +
                ", bad=" + bad +
                ", tag_list='" + tag_list + '\'' +
                '}';
    }

    @FiledNameReplace(name = "tag_list", MethodName = "setTag_list", datatypes = String[].class)


    public String[] tag_list;

    public void setTag_list(String[] tag_list) {
        this.tag_list = tag_list;
    }

    public String[] getTag_list() {
        return tag_list;
    }

    public qevaluate() {
    }
    public qevaluate(int good, int common, int bad) {
        this.good = good;
        this.common = common;
        this.bad = bad;
    }

    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }

    public int getCommon() {
        return common;
    }

    public void setCommon(int common) {
        this.common = common;
    }

    public int getBad() {
        return bad;
    }

    public void setBad(int bad) {
        this.bad = bad;
    }
}
