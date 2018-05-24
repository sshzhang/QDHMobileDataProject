package com.lifeStudy.algorith;


import java.util.*;

//电话号码分身问题
public class niukewang_phoneM {

    private static Map<String, Integer> equMap = new HashMap<String, Integer>();
    private static List<Integer> Initdata = new ArrayList<Integer>();
    private static Map<Integer, Integer> newdata = new HashMap<Integer, Integer>();

    static {
        equMap.put("Z", 0);
        equMap.put("W", 2);
        equMap.put("U", 4);
        equMap.put("X", 6);
        equMap.put("G", 8);
    }

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        int numIndex = scanner.nextInt();
        scanner.nextLine();
        while (--numIndex >= 0) {
            String str = scanner.nextLine();
            Set<String> strings = equMap.keySet();
            for (String strm : strings) {
                int count = GainTheCharNumbers(str, strm);
                FillValuesDatas(count, strm);
            }
            int count = GainTheCharNumbers(str, "F");
            newdata.put(5, count - newdata.get(4));
            count = GainTheCharNumbers(str, "H");
            newdata.put(3, count - newdata.get(8));

            count = GainTheCharNumbers(str, "S");
            newdata.put(7, count - newdata.get(6));

            count = GainTheCharNumbers(str, "O");
            newdata.put(1, count - newdata.get(0) - newdata.get(2) - newdata.get(4));

            count = GainTheCharNumbers(str, "N");
            newdata.put(9, (count - newdata.get(1) - newdata.get(7))/2);
            ChangeDatas();

            Collections.sort(Initdata);

            for (int i = 0; i < Initdata.size(); i++) {
                System.out.print(Initdata.get(i));
            }
            System.out.println();
            newdata.clear();
            Initdata.clear();

        }
        scanner.close();
    }

    private static void ChangeDatas() {
        Set<Integer> integers = newdata.keySet();
        for (Integer in : integers) {
            int count = newdata.get(in);
            int num = in;
            int newNum = num >= 8 ? num - 8 : num + 2;
            while (--count >= 0) {
                Initdata.add(newNum);
            }
        }
    }

    private static void FillValuesDatas(int count, String strm) {
        int num = equMap.get(strm);
        newdata.put(num, count);

    }

    public static int GainTheCharNumbers(String str, String flages) {
        int original_length = str.length();
        int new_length = str.replace(flages, "").length();
        return original_length - new_length;
        //FIVESIXONENINEZEROZEROTHREESIXFIVEFOURONESIXFOURFIVEONEONETWOTWOFIVE
        //5619003654164511225
    }
}
