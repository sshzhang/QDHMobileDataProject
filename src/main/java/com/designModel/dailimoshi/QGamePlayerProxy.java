package com.designModel.dailimoshi;

public class QGamePlayerProxy implements QIGamePlayer {

    private QIGamePlayer qiGamePlayer = null;

    public QGamePlayerProxy(QIGamePlayer qiGamePlayer) {
        this.qiGamePlayer = qiGamePlayer;
    }

    @Override
    public void login(String user, String password) {

        this.qiGamePlayer.login(user,password);

    }

    @Override
    public void killBoss() {
        this.qiGamePlayer.killBoss();
    }

    @Override
    public void upgrade() {

        this.qiGamePlayer.upgrade();

    }

    @Override
    public QIGamePlayer getProxy() {
        return this;
    }
}
