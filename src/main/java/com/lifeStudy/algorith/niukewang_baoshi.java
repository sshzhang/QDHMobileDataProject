package com.lifeStudy.algorith;

import java.util.Scanner;

public class niukewang_baoshi {


    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String sp = scanner.nextLine();
            char[] chars = sp.toCharArray();

            int len = sp.length();
            boolean flages = true;
            for(int x=5;x<=len;x++) {

                for (int y=0;y<len;y++) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for(int z=y;z<x+y;z++) {
                        stringBuilder.append(chars[z % len]);
                    }
                    String str = stringBuilder.toString();
                    if (str.contains("A") && str.contains("B") && str.contains("C") && str.contains("D")
                            && str.contains("E")) {
                        System.out.println(len - x);
                        flages = false;
                        break;
                    }
                }
                if (!flages) break;
            }
            if (flages) System.out.println(0);


        }

    }

}
