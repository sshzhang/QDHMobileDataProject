package com.Neo4j.MongoDBPac.xiechengJavaBean;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CglibBean {//动态实体类

    public Object object = null;

    public BeanMap beanMap = null;

    public CglibBean(Map<String, Class> propertyMap) {
        this.object = generateBean(propertyMap);
        this.beanMap = BeanMap.create(this.object);
    }

    /**

     * 给bean属性赋值
     * @param property属性名
     * @param value值

     */

    public void setValue(String property, Object value) {

        beanMap.put(property, value);

    }
    /**
     * 得到该实体bean对象。

     */

    public Object getObject() {

        return this.object;

    }


    /**
     * 通过属性名得到属性值
     * @param property属性名

     */
    public Object getValue(String property) {

        return beanMap.get(property);

    }



    /**
     * 生成Bean
     * @param propertyMap
     * @return
     */

    @SuppressWarnings("unchecked")

    private Object generateBean(Map<String, Class> propertyMap) {

        BeanGenerator generator = new BeanGenerator();

        Set keySet = propertyMap.keySet();

        for (Iterator i = keySet.iterator(); i.hasNext();) {

            String key = (String) i.next();

            generator.addProperty(key, (Class) propertyMap.get(key));

        }

        return generator.create();

    }


}
