package com.Neo4j.MongoDBPac.freemarkTemplateText;

//抽象工厂类   不相关的对象具有相同的约束
public interface HumanFactory
{
    public Human createYellowHuman();
    public Human createWhiteHuman();
    public Human createBlackHuman();
}
