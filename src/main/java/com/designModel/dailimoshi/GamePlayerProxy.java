package com.designModel.dailimoshi;

//普通代理的  代理类
public class GamePlayerProxy implements IGamePlayer {

    private IGamePlayer iGamePlayer;

    public GamePlayerProxy(String name) {
        iGamePlayer = new GamePlayer(this, name);
    }
    @Override
    public void login(String user, String password) {
        iGamePlayer.login(user, password);
    }

    @Override
    public void killBoss() {
        iGamePlayer.killBoss();
    }

    @Override
    public void upgrade() {
        iGamePlayer.upgrade();
    }
}
