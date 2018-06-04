package com.designModel.zhongjiezhe;

public class AbstarctColleague {//抽象同事类

    protected AbstractMediator mediator;

    protected AbstarctColleague(AbstractMediator mediator) {
        this.mediator = mediator;
    }
}
