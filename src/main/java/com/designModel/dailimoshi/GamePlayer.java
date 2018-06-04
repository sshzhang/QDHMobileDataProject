package com.designModel.dailimoshi;

//普通代理   游戏对象
public class GamePlayer implements IGamePlayer {

    private String name = "";

    public GamePlayer(IGamePlayer gamePlayerProxy, String name) {
        if (gamePlayerProxy == null) {//可以限制指定的代理类才能创建 真实角色
            throw new RuntimeException("不能创建真实角色");
        }else{
            System.out.println(gamePlayerProxy.getClass().toString());
            this.name = name;
        }
    }
    @Override
    public void login(String user, String password) {
        System.out.println(this.name + "登录系统");
    }

    @Override
    public void killBoss() {
        System.out.println(this.name+"击杀怪物");
    }

    @Override
    public void upgrade() {
        System.out.println(this.name + "又升一级");
    }
}
