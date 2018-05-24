package com.qdh.Beans;

import java.util.List;

public class YkrsBeans {
    private String name;
    private List<YkrsBean> result;
    private String success;

    public YkrsBeans() {
    }

    public YkrsBeans(String name, List<YkrsBean> result,String success) {
        this.name = name;
        this.result = result;
        this.success = success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getSuccess() {
        return success;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<YkrsBean> getResult() {
        return result;
    }

    public void setResult(List<YkrsBean> result) {
        this.result = result;
    }
}
