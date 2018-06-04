package com.designModel.zhongjiezhe;

public class Client {
    public static void main(String... args) {

        AbstractMediator mediator = new Meditor();
        System.out.println("------采购人员采购电脑------");
        mediator.purchase.buyIBMcomputer(100);
        System.out.println("-------销售人员销售电脑------");
        mediator.sale.sellIBMComputer(1);
        System.out.println("-----库房人员清库处理------");
        mediator.stock.clearStock();

    }

}
