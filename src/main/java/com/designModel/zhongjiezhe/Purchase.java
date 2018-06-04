package com.designModel.zhongjiezhe;

public class Purchase extends  AbstarctColleague{

    protected Purchase(AbstractMediator mediator) {
        super(mediator);
    }

    public void buyIBMcomputer(int number) {
        super.mediator.execute("purchase.buy", number);
    }

    public void refuseBuyIBM() {
        System.out.println("不再采购IBM电脑");
    }

}
