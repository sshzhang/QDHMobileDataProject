package com.qdh.Beans;

public class SightBean {
    private  int id;
    private  int area_id;
    private  String area_name;
    private  int parent_id;
    private int layer;

    public SightBean() {

    }
    public SightBean(int id, int area_id, String area_name, int parent_id, int layer) {
        this.id = id;
        this.area_id = area_id;
        this.area_name = area_name;
        this.parent_id = parent_id;
        this.layer = layer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    @Override
    public String toString() {
        return "SightBean{" +
                "id=" + id +
                ", area_id=" + area_id +
                ", area_name='" + area_name + '\'' +
                ", parent_id=" + parent_id +
                ", layer=" + layer +
                '}';
    }
}
