package com.lifeStudy.algorith;

import java.util.Scanner;

//藏宝图
public class niukewang_cangbaotu {

    public static void main(String... args) {

        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String t = scanner.nextLine();
        if (s.length() < t.length()) {System.out.println("No");return;}
        int len = s.length();
        int lent = t.length();
        int j=0;
        for (int i = 0; i < len && j < lent; i++) {
            if (s.charAt(i) == t.charAt(j)) {
                j++;
            }
        }
        if (j == lent) {
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }
    }
}