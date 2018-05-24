package com.lifeStudy.algorith;


import java.util.*;

//电话号码分身问题
public class niukewang_phone {
    private static String numbers[] = new String[]{"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"};
    private static Map<String, List<Integer>> hashmaps = new HashMap<String, List<Integer>>();
    private static Map<String, Integer> equalsMap = new HashMap<String, Integer>();
    private static List<Integer> dataCol = new ArrayList<Integer>();
    private static List<Integer> resultData = new ArrayList<Integer>();

    public static void preDatas() { //处理数据
        for (int j = 0; j < numbers.length; j++) {
            String st = numbers[j];
            for (int i = 0; i < st.length(); i++) {
                String stm = st.charAt(i) + "";
                if (hashmaps.containsKey(stm)) {
                    if (!hashmaps.get(stm).contains(j))
                        hashmaps.get(stm).add(j);
                } else {
                    List<Integer> newList = new ArrayList<Integer>();
                    newList.add(j);
                    hashmaps.put(stm, newList);
                }
            }
        }
        Set<String> strings = hashmaps.keySet();
        for (String stKey : strings) {
            if (hashmaps.get(stKey).size() == 1) {
                equalsMap.put(stKey, hashmaps.get(stKey).get(0));
            }
        }
    }

    public static void main(String... args) {

        preDatas();
//        Scanner scanner = new Scanner(System.in);
//        int numIndex = scanner.nextInt();
//        scanner.nextLine();
//        while (--numIndex >= 0) {
//            String str = scanner.nextLine();
        ReclusiveTheData("FIVESIXONENINEZEROZEROTHREESIXFIVEFOUR");
        getTheOutPut();
        dataCol.clear();
        resultData.clear();
//        }
//        scanner.close();


    }

    private static void getTheOutPut() {
        int std = 0;
        for (int i = 0; i < dataCol.size(); i++) {

            if (dataCol.get(i) < 8) {
                std = dataCol.get(i) + 10 - 8;
            } else {
                std = dataCol.get(i) - 8;
            }
            resultData.add(std);
        }
        Collections.sort(resultData);
        for (Integer st : resultData) {
            System.out.print(st);
        }
        System.out.println();
    }

    private static void ReclusiveTheData(String str) {
        //      先除去能唯一辨别的数字
        StringBuilder strcopy = new StringBuilder(str);
        Set<String> strKeys = equalsMap.keySet();

        boolean flages = true;
        while (flages) {
            boolean stg = false;
            for (String strm : strKeys) {//删除唯一指定元素
                if (strcopy.indexOf(strm) != -1) {
                    stg = true;//存在一次数据满足条件
//                 删除元素
                    int num = equalsMap.get(strm);
//                获得对应的字符串
                    String numStr = numbers[num];
                    dataCol.add(num);
                    for (int i = 0; i < numStr.length(); i++) {//删除这个字串
                        char atPo = numStr.charAt(i);
                        strcopy.deleteCharAt(strcopy.indexOf(atPo + ""));
                    }
                }
            }
            flages = stg;
        }


        for (int j = dataCol.size(); strcopy.length() > 0; j++) {
            ReclvsiveDifferent(strcopy);
            String delestr = numbers[dataCol.get(j)];
            for (int i = 0; i < delestr.length(); i++) {
                strcopy.deleteCharAt(strcopy.indexOf(delestr.charAt(i) + ""));
            }
        }
    }

    private static void ReclvsiveDifferent(StringBuilder strcopy) {
        List<Integer> pafirst =new ArrayList<Integer>();

        if (strcopy.length() >= 1)
            pafirst.addAll(hashmaps.get(strcopy.charAt(0) + ""));
        for (int i = 1; i < strcopy.length(); i++) {
            List<Integer> tempt = new ArrayList<Integer>();
            tempt.addAll(hashmaps.get(strcopy.charAt(i) + ""));
            List<Integer> copyfirst = new ArrayList<Integer>();
            copyfirst.addAll(pafirst);
            pafirst.retainAll(tempt);
            if (pafirst.size() == 0) {//没有交集
                pafirst.addAll(copyfirst);
            } else if (pafirst.size() == 1) {
//                return pafirst.get(0);
                dataCol.add(pafirst.get(0));
                return;
            }
        }
    }


}
