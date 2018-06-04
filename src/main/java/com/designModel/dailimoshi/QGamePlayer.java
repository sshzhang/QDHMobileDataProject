package com.designModel.dailimoshi;

//强制代理
public class QGamePlayer implements QIGamePlayer {


    private String name = "";
    //我的代理对象
    private QIGamePlayer qiGamePlayerProxy = null;

    public QGamePlayer(String name) {
        this.name = name;
    }
    @Override
    public void login(String user, String password) {
        if (this.isProxy()) {
            System.out.println("请使用代理对象");
        }else{
            System.out.println(this.name+"登录");
        }
    }
    @Override
    public void killBoss() {
        if (this.isProxy()) {
            System.out.println("请使用代理对象");
        }else{
            System.out.println(this.name+"击杀怪物");
        }
    }
    @Override
    public void upgrade() {
        if (this.isProxy()) {
            System.out.println("请使用代理对象");
        }else{
            System.out.println(this.name + "又升一级");
        }
    }


    private boolean isProxy() {
        return this.qiGamePlayerProxy == null;
    }
    @Override
    public QIGamePlayer getProxy() {
        this.qiGamePlayerProxy = new QGamePlayerProxy(this);
        return this.qiGamePlayerProxy;
    }

}
