package com.designModel.zerenlianmoshi;

//责任链模式中的Request请求接口
public interface IWoman {

    //获得个人状况
    int getType();

    //获得请求信息
    String getRequest();

}
