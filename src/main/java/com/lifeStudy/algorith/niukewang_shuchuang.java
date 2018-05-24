package com.lifeStudy.algorith;

import java.util.Scanner;

//牛客网数串问题
public class niukewang_shuchuang {


    public static void main(String... args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int N = scanner.nextInt();
            String[] sts = new String[N];
            if (N > 100) return;
            while (N-- > 0) {
                sts[N] = scanner.next();
            }
            sortArrays(sts);
        }
    }

    public static void sortArrays(String strs[]) {
//        插入排序
        String newstrs[] = new String[strs.length];
        newstrs[0] = strs[0];
        for (int i = 1, j; i < strs.length; i++) {
            for (j = i - 1; j >= 0 && CompareValues(strs[i], newstrs[j]) == 1; j--) {//插入每一个元素
                newstrs[j + 1] = newstrs[j];
            }
            newstrs[j + 1] = strs[i];
        }
        String stm = "";
        for (String st : newstrs) {
            stm += st;
        }
        System.out.println(stm);

    }

    private static int CompareValues(String str, String str1) {
//        取最小的长度
        int small_length = str.length() > str1.length() ? str1.length() : str.length();

//        位数相同的数据
        if (str1.length() == str.length()) {
            return str.compareTo(str1) >= 1 ? 1 : -1;//
        } else {
            for (int i = 0; i < small_length; i++) {
                if (str.charAt(i) != str1.charAt(i)) {//没有相同前缀字符串
                    return str.charAt(i) - str1.charAt(i) > 0 ? 1 : -1;
                }
            }
            //有相同的前缀
//            判断到底谁更长  1表示str更长  0表示 str1更长
            int flages = str.length() > str1.length() ? 1 : 0;
            return RecusiveTheValues(str, str1, 0, flages);
        }
    }

    //    递归调用
    private static int RecusiveTheValues(String str, String str1, int i, int flages) {

        if (flages == 1) {
            int count = i % str1.length();
            if (str1.length() + i >= str.length()) return 0;

            if (str.charAt(str1.length() + i) > str1.charAt(count)) {
                return 1;
            } else if (str.charAt(str1.length() + i) < str1.charAt(count)) {
                return -1;
            } else {//等于情况下
                i++;
                return RecusiveTheValues(str, str1, i, flages);
            }
        } else {
            int count = i % str.length();
            if (str.length() + i >= str1.length()) return 0;

            if (str1.charAt(str.length() + i) > str.charAt(count)) {
                return -1;
            } else if (str1.charAt(str.length() + i) < str.charAt(count)) {
                return 1;
            } else {//等于情况下
                i++;
                return RecusiveTheValues(str, str1, i, flages);
            }
        }
    }
}
