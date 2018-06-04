package com.designModel.zhongjiezhe;

public abstract  class AbstractMediator {//抽象中介者


    protected Purchase purchase;
    protected Sale sale;
    protected Stock stock;
    public AbstractMediator() {
        //每一个同事类 独有一个中介者
        purchase = new Purchase(this);
        sale = new Sale(this);
        stock = new Stock(this);
    }

    public abstract void execute(String str, Object... objects);
}
