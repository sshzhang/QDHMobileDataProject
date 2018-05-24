package com.Neo4j.MongoDBPac;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Array;


@Retention(RetentionPolicy.RUNTIME)
@Target( {ElementType.FIELD ,ElementType.TYPE})
public @interface FiledNameReplace {
    //字段对应的名称，默认""表示和自己字段相同
    String name() default "";
    //表示返回的类型信息
    Class<?> datatypes() default Array.class;
    String MethodName() default  "";

    //GET方法
    String GetMethodName() default "";
}
