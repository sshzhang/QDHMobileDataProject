package com.lifeStudy.algorith;

import java.util.Scanner;
//星级穿越
public class niukewang_xingjichuangyue {

    public static void main(String... args) {

        Scanner scanner = new Scanner(System.in);
        long strh = scanner.nextLong();
        scanner.close();
        long srt = (long) Math.pow(strh, 0.5);
        for (long i= srt; i >= 0; i--) {
            if (i * (i+1) <= strh) {
                System.out.println(i);
                break;
            }
        }
    }
}
