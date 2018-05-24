package com.lifeStudy.algorith;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class niukewang_Wflower {//水仙花

    private static List<Integer> params = new ArrayList<Integer>();


    public static void main(String... args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int firstValue = scanner.nextInt();
            int sencondValue = scanner.nextInt();
            if (firstValue > sencondValue) {
                int tempt = firstValue;
                firstValue = sencondValue;
                sencondValue = tempt;
            }

            for (int i = firstValue; i <= sencondValue; i++) {
                CopeWithTheData(i);
            }
            if (params.size() == 0) {
                System.out.println("no");
            } else{
                for (int i = 0; i < params.size(); i++) {
                    System.out.print(params.get(i));
                    if (i != params.size() - 1) System.out.print(" ");
                }
                System.out.println();
            }
            params.clear();
        }
        scanner.close();


    }

    private static void CopeWithTheData(int num) {


        String strnum = num + "";
        int sum = 0;
        for (int i = 0; i < strnum.length(); i++) {

            int pos = strnum.charAt(i) - '0';
            sum += pos * pos * pos;
        }

        if (sum == num) {
            params.add(num);
        }


    }


}
