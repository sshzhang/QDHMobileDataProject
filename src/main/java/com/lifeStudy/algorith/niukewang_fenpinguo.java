package com.lifeStudy.algorith;

import java.util.Scanner;

public class niukewang_fenpinguo {//分苹果

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int datas[] = new int[n];
        int sum = 0;
        for(int i=1;i<=n;i++) {
            int data = scanner.nextInt();
            sum += data;
            datas[i - 1] = data;
        }

        if (sum % n != 0) {
            System.out.println(-1);
            return;
        }
        int average = sum / n;

        int Count = 0;
        for (int j = 0; j < datas.length; j++) {
            datas[j] = datas[j] - average;
            System.out.print(datas[j]);
            if (Math.abs(datas[j]) % 2 != 0) {
                System.out.println(-1);
                return;
            }
            if (datas[j] >0) {
                Count += datas[j] / 2;
            }
        }
        System.out.println(Count);

    }




}
