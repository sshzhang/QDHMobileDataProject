package com.designModel.zerenlianmoshi;

public class Father extends IHandler {

    public Father() {
        super(IHandler.FEATHER_LEVEL_REQUEST);
    }
    @Override
    public void response(IWoman woman) {
        System.out.println("----女儿向父亲请示-----");
        System.out.println(woman.getRequest());
        System.out.println("父亲的答复是:同意");
    }
}
