package com.designModel.dailimoshi;

//强制代理
public interface QIGamePlayer {
    void login(String user, String password);

    void killBoss();

    void upgrade();

    //获取真实对象对应的代理对象
    QIGamePlayer getProxy();

}
