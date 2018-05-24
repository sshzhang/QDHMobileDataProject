package com.lifeStudy.algorith;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//统计字符串
public class niukewang_tongjizifucuang {


    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        Map<String, Integer> maps = new HashMap<String, Integer>();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (isOk(c)) {
                int orDefault = maps.getOrDefault(c + "", 0);
                if (orDefault == 2) {
                    System.out.println(c);
                    break;
                } else
                    maps.put(c + "", orDefault + 1);
            }
        }
    }

    public static boolean isOk(char c) {
        if ((c <= 'Z' && c >= 'A') || (c <= 'z' && c >= 'a')) return true;
        else return false;
    }

}
