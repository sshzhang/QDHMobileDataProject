package com.designModel.dailimoshi;

public class Client {

    public static void main(String... args) {

        /**
         * 普通代理模式
         *
         * 特点  调用者只知道代理而不用知道真实的角色是谁，屏蔽了真实角色的变更对高层模块的影响
         * 真实的主题角色想怎么改就怎么修改
         */
        IGamePlayer proxy = new GamePlayerProxy("张三");
        proxy.login("zhansan", "password");
        proxy.killBoss();
        proxy.upgrade();


        /**
         * 强制代理模式
         * 通过真实角色找到代理角色
         *
         */

        QGamePlayer qGamePlayer = new QGamePlayer("张三");
        QIGamePlayer proxy1 = qGamePlayer.getProxy();
        proxy1.login("user", "password");
        proxy1.killBoss();
        proxy1.upgrade();

    }

}
