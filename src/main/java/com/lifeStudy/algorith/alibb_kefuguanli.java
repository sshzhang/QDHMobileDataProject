package com.lifeStudy.algorith;

import java.util.*;

/**
 * 阿里巴巴客服管理员
 * 通过并差集合
 * 每个约束条件生成一个 一些集合 ，然后与上面约束条件的集合做并集
 * 最后如果集合为空 说明没有通路 ，否则有解
 */
public class alibb_kefuguanli {


    public static void main(String... args) {

        List<String> yuesu=new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        for (int i=1;i<=m;i++){
            yuesu.add(sc.nextLine());
            System.out.println(yuesu.get(i - 1));
        }
        Map<Integer, List<Integer>> params = new HashMap<Integer, List<Integer>>();
        for (int i=1;i<=m;i++) {//对于每一个约束条件
            String line = yuesu.get(i - 1);
            String[] split = line.split(",");
            int data1 = Integer.parseInt(split[0]);
            int data2 = Integer.parseInt(split[1]);
            //调整顺序
            if (data1 > data2) {
                int tempt = data1;
                data1 = data2;
                data2 = tempt;
            }
            UnionDataSet(params, data1, data2);
        }
        if (params.size() == 0) {
            System.out.println("no");
        }else{
            System.out.println("yes");
        }
    }

    private static void UnionDataSet(Map<Integer, List<Integer>> params, int data1, int data2) {//求所谓的并查集合

        //计算当前集合
        int num1=(data1 % 2 + data1) / 2, num2=(data2 % 2 + data2) / 2;
        int data1b, data2b;
        data1b= (data1%2==0)?data1-1:data1+1;
        data2b = (data2 % 2 == 0) ? data2 - 1 : data2 + 1;
        List<Integer> params1 = new ArrayList<Integer>();
        params1.add(data1);
        params1.add(data2b);
        List<Integer> params2 = new ArrayList<Integer>();
        params2.add(data1b);
        params2.add(data2b);
        List<Integer> params3 = new ArrayList<Integer>();
        params3.add(data1b);
        params3.add(data2);
        Map<Integer,List<Integer>>initValues=new HashMap<Integer, List<Integer>>();
        initValues.put(0, params1);
        initValues.put(1, params2);
        initValues.put(2, params3);
        if (params.size() == 0) {
            int k = params.size();
            params.put(k++, params1);
            params.put(k++, params2);
            params.put(k++, params3);
        }else{//做并集操作

            Map<Integer, List<Integer>>newDatas= new HashMap<Integer, List<Integer>>();

            for (int k=0;k<3;k++) {//由于每一次迭代一定是三个元素

                List<Integer> binjihe = initValues.get(k);
                for (int i = 0; i < params.size(); i++) {
                    List<Integer> originalList =new ArrayList<>(params.get(i));
                    originalList.addAll(binjihe);
                    Set<Integer> allintegers = new HashSet<>(originalList);
                    Set<Integer> nums = new HashSet<>();
                    Iterator<Integer> iterator = allintegers.iterator();
                    boolean flages = true;
                    while (iterator.hasNext()) {
                        int curreent= iterator.next();
                        int value = (curreent % 2 + curreent) / 2;
                        if (nums.contains(value)) {
                            flages = false;
                            break;
                        } else {
                            nums.add(value);
                        }
                    }
                    if(flages)
                        newDatas.put(newDatas.size(), new ArrayList<Integer>(allintegers));
                }
            }
            params.clear();
            params.putAll(newDatas);
        }
    }
}
