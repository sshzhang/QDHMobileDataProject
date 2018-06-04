package com.Neo4j.MongoDBPac;

public @interface Start {
    String name();
    boolean isArrayIndexOpen() default  false;
}
