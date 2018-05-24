package com.lifeStudy.algorith;

import java.util.*;

//保留最大数字

/** 思路
 * 主要通过从数字从左到右开始 第一个最小的递减位(不是严格递减) 做为删除数据
 * 例如 87784201  第一个删除数据就是 7(第二个7)  删除之后一次下去
 *     67899   删除6
 *     77677 删除6
 */
public class niukewang_baoliuzuidashuzi {


    public static void main(String... args) {


        Scanner scanner = new Scanner(System.in);
        String num =scanner.next();
        int cnt =scanner.nextInt();
        List<Integer> datas = new ArrayList<Integer>();
        for (int k = 0; k < num.length(); k++) {
            datas.add(num.charAt(k) - '0');
        }
//        每次删除第一个递减元素
        for (int i = 1; i <= cnt; i++) {
            int min = datas.get(0);
            int posi = 0;
            for (int j = 1; j < datas.size(); j++) { //等于号主要时为了确保一开始相等数据算作递减
                if (datas.get(j) <= min) {
                    min = datas.get(j);
                    posi = j;
                } else break;
            }
            datas.remove(posi);
        }
        //输出数据

        for (int m = 0; m < datas.size(); m++)
            System.out.print(datas.get(m));
        System.out.println();

    }

}
