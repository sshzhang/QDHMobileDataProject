package com.qdh.DBDao;

import com.qdh.Beans.SightBean;
import com.qdh.DBUtils.DbHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SightImpl implements SightDao {

    public List<SightBean> findAllSightsByLevels(int level) {

        String SQL = (level == 0) ? "select * from qdh_jingqu" : "select * from qdh_jingqu where layer=" + level + "";
        DbHelper helper = new DbHelper();
        List<Map<Object, Object>> moreLines =
                helper.findMoreLines(SQL, null);
        List<SightBean> sightBeans = new ArrayList<SightBean>();
        try {
            for (Map<Object, Object> moreLine : moreLines) {
                SightBean sightBean = new SightBean(Integer.parseInt(moreLine.get("id").toString()), Integer.parseInt(moreLine.get("area_id").toString()), moreLine.get("area_name").toString(), Integer.parseInt(moreLine.get("parent_id").toString()), Integer.parseInt(moreLine.get("layer").toString()));
                sightBeans.add(sightBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return sightBeans;
    }
}
