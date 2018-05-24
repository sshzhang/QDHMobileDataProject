package com.lifeStudy.algorith;

import java.util.Scanner;

public class niukewang_sumArray {


    public static void main(String... args) {


        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            float sum = 0;
            double t = n;
            for (int i = 1; i <= m; i++) {
                sum += t;
                t = Math.sqrt(t);
            }
            System.out.println(String.format("%.2f",sum));


        }
        scanner.close();


    }
}
