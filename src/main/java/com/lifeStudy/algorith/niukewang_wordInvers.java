package com.lifeStudy.algorith;

import java.util.Scanner;
import java.util.Stack;

//句子反转
public class niukewang_wordInvers {
    public static void main(String... args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            Stack sm = new Stack<String>();
            String data = scanner.nextLine();
            String[] split = data.split(" ");
            for (String sp : split) {
                sm.push(sp);
            }
            while (!sm.isEmpty()) {
                System.out.print(sm.pop());
                if (sm.size() != 0) System.out.print(" ");
            }
            System.out.println();
        }
        scanner.close();
    }
}
