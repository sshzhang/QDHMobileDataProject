package com.Neo4j.MongoDBPac.xiechengJavaBean;

import java.util.HashMap;
import java.util.Map;

public class ImpressionAndDianping {//印象和点评

    public String info;
    public Map<String, String> params = new HashMap<String, String>();

    public ImpressionAndDianping() {

    }
    public ImpressionAndDianping(String info, Map<String, String> params) {
        this.info = info;
        this.params = params;
    }

    public String getInfo() {

        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
