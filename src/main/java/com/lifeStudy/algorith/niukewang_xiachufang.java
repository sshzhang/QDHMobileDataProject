package com.lifeStudy.algorith;

import java.util.*;

public class niukewang_xiachufang {


    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        Set<String> values = new HashSet<String>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            values.addAll(Arrays.asList(line.split(" ")));
        }
        scanner.close();
        System.out.println(values.size());
    }
}
