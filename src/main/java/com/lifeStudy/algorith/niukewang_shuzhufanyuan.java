package com.lifeStudy.algorith;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//数组还原
public class niukewang_shuzhufanyuan {


    public static List<int[]> allSort = new ArrayList<>();

    //递归求解全排列
    public static void permutation(int[] nums, int start, int end) {

        if (start == end) {
            int[] newNums = new int[nums.length];
            for (int i=0;i<=end;i++) {
                newNums[i] = nums[i];
            }
            allSort.add(newNums);
        }else{
            for (int i=start;i<=end;i++) {
                int tempt = nums[start];//把第i个位置的元素和 start 位置元素交换
                nums[start] = nums[i];
                nums[i] = tempt;
                permutation(nums, start + 1, end);
                nums[i] = nums[start];  //把数据换回来
                nums[start] = tempt;
            }
        }
    }

    public static void main(String... args) {


        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> nums= new ArrayList<>();
        int k = scanner.nextInt();
        int b[] = new int[n+1];
        for (int j=1;j<=n;j++) {//添加所有的数据
            nums.add(j);
        }
        for (int i = 1; i <= n; i++) { //获取所有不清楚的数据取值
            b[i] = scanner.nextInt();
            if (b[i] != 0)
                nums.remove((Integer) b[i]);
        }
        scanner.close();


        int[] numArray = new int[nums.size()];
        for (int i=0;i<nums.size();i++) {
            numArray[i] = nums.get(i);
        }
        //递归调用 获取数组全排列集合
        permutation(numArray, 0, numArray.length - 1);
        int[][] a = new int[allSort.size()][];
        allSort.toArray(a);
        int sumAll = 0;
        for (int i=0;i<a.length;i++) {//对于每一个全排列集合元素
            //复制数据
            int tema[] = new int[b.length];
            for (int m=0;m<tema.length;m++) {
                tema[m] = b[m];   //辅助数组
            }
            int num[] = a[i];
            for (int j=1,m=0;j<=n;j++) {
                if (tema[j] == 0) {
                    tema[j] = num[m++];//填充不清楚数据
                }
            }
            //计算满足条件的个数
            int count = 0;
            for (int j=2;j<=n;j++) {//计算顺序对的个数
                for (int m=j-1;m>=1;m--) {
                    if(tema[j]>tema[m]) count++;
                }
            }
            if(count==k) sumAll++;

        }

        System.out.println(sumAll);
    }

}
