package com.designModel.zerenlianmoshi;

//责任链处理接口
public abstract  class IHandler {
    public static final int FEATHER_LEVEL_REQUEST = 1;
    public static final int HUSBAND_LEVEL_REQUEST = 2;
    public static final int SON_LEVEL_REQUEST = 3;

    private int level = 0;
    private IHandler nexthandler;

    //初始化一个处理类 并设置层级
    public IHandler(int level) {
        this.level = level;
    }


    public void setNexthandler(IHandler nexthandler) {
        this.nexthandler = nexthandler;
    }

    public final void handlerMessage(IWoman woman) {//处理请求方法  为final表示不允许复写
        String request = woman.getRequest();
        if (woman.getType() == this.level) {
            this.response(woman);
        }else{
            if (this.nexthandler != null) {
                if (woman.getType() == this.nexthandler.level) {
                    this.nexthandler.response(woman);
                }
            }else{
                System.out.println("-----没地方请示，按不同意处理----");
            }
        }
    }

    //子类处理相应数据
    public abstract  void response(IWoman woman) ;

}
