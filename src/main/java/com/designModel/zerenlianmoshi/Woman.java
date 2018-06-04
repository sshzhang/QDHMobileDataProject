package com.designModel.zerenlianmoshi;

public class Woman implements IWoman {


    public int type;
    public String request;

    public Woman(int type, String request) {
        this.type = type;
        this.request = request;
    }
    @Override
    public int getType() {

        return this.type;
    }

    @Override
    public String getRequest() {
        return this.request;
    }

}
