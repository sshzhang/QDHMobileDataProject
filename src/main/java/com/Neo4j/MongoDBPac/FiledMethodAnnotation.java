package com.Neo4j.MongoDBPac;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target( ElementType.FIELD )
//字段对应的方法注解
public @interface FiledMethodAnnotation {
    String FieleReallyName() default "";//字段的真实名称
    String MethodName(); //没有参数的成员
    //参数类型
    Class<?> ParameterType();
}
