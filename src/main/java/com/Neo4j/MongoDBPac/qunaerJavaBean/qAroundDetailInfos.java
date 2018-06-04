package com.Neo4j.MongoDBPac.qunaerJavaBean;

public class qAroundDetailInfos {

    public String name;//周边车站或景点 名称
    public String description;//描述

    public qAroundDetailInfos() {
    }
    public qAroundDetailInfos(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "qAroundDetailInfos{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
