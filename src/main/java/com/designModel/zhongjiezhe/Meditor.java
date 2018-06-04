package com.designModel.zhongjiezhe;

public class Meditor extends AbstractMediator {

    @Override
    public void execute(String str, Object... objects) {

        if (str.equals("purchase.buy")) {
            this.buyComputer(((Integer) objects[0]));
        } else if (str.equals("sale.sell")) {
            this.sellComputer(((Integer) objects[0]));
        } else if (str.equals("sale.offsell")) {
            this.offSell();
        } else if (str.equals("stock.clear")) {
            this.clearStock();
        }
    }


    private void buyComputer(int number) {
        int saleStatus = super.sale.getSaleStatus();
        if (saleStatus > 80) {
            System.out.println("采购IBM电脑" + saleStatus);
            super.stock.increase(number);
        }else{
            int buyNumber = saleStatus / 2;
            System.out.println("采购IBM电脑" + buyNumber);
            super.stock.increase(buyNumber);
        }
    }

    private void sellComputer(int number) {

        if (super.stock.getStockNumber() < number) {
            super.purchase.buyIBMcomputer(number);

        }
        super.stock.decrease(number);
    }

    private void offSell() {
        System.out.println("折价销售IBM电脑" + super.stock.getStockNumber());
    }

    private void clearStock() {
        super.sale.offSale();
        super.purchase.refuseBuyIBM();
    }


}
