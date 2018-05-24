package com.qdh.DBDao;

import com.qdh.Beans.SightBean;

import java.util.List;

//景区数据库表处理类
public interface SightDao {

    /**
     * 根据 level 查找所有景点数据集
     * @param level  0表示查找所有  1表示查找最高等级的景区集合   2表示第二层的景区集合  3表示第三层的景区集合
     * @return 景点数据集
     */
    List<SightBean> findAllSightsByLevels(int level);
 }
