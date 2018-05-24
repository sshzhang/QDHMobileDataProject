package com.lifeStudy.algorith;

import java.util.Scanner;

//合唱团问题
public class niukewang_hechangtuan {

    public static void main(String[] args){

        Scanner s = new Scanner(System.in);
        while(s.hasNextInt()){
            int n = s.nextInt(); //学生人数
            int[] ability = new int[n];
            for(int i = 0; i < n; i++){
                ability[i] = s.nextInt();
            }
            int k = s.nextInt();
            int d = s.nextInt();

            //maxProduct[i][j]表示以第i个人为结尾，合唱团的人数为j+1时，合唱团最大的能力乘积
            long[][] maxProduct = new long[n][k];
            //minProduct[i][j]表示以第i个人为结尾，合唱团的人数为j+1时，合唱团最小的能力乘积
            long[][] minProduct = new long[n][k];
            //合唱团中只有一个人
//            for(int i = 0; i < n; i++){
//                maxProduct[i][0] = ability[i];
//                minProduct[i][0] = ability[i];
//            }
//            long max = Long.MIN_VALUE;
//            for(int i = 0; i < n; i++){ //对于每开始成员 F(start,k) 从start结尾的数组索引
//                for(int j = 1; j < k; j++){
//                    for(int p = i-1; p >= Math.max(i-d,0); p--){// 从 p结尾的数组索引中选择 j-1个元素
//                        maxProduct[i][j] = Math.max(maxProduct[i][j],
//                                maxProduct[p][j-1]*ability[i]);
//                        maxProduct[i][j] = Math.max(maxProduct[i][j],
//                                minProduct[p][j-1]*ability[i]);
//                        minProduct[i][j] = Math.min(minProduct[i][j],
//                                minProduct[p][j-1]*ability[i]);
//                        minProduct[i][j] = Math.min(minProduct[i][j],
//                                maxProduct[p][j-1]*ability[i]);
//                    }
//                }
//                max = Math.max(max, maxProduct[i][k-1]);
//            }
//            for (int j=0;j<k;j++) {
//
//
//                for (int i=0;i<n;i++) {
//
//                    if (j == 0) {
//                        maxProduct[i][j] = ability[i];
//                        minProduct[i][j] = ability[i];
//                    }else{
//
//                        for (int index=i-1;index<Math.min(i+d,n-1);index++) {
//                            maxProduct[i][j] = Math.max(maxProduct[i][j],
//                                maxProduct[index][j-1]*ability[i]);
//                        maxProduct[i][j] = Math.max(maxProduct[i][j],
//                                minProduct[index][j-1]*ability[i]);
//                        minProduct[i][j] = Math.min(minProduct[i][j],
//                                minProduct[index][j-1]*ability[i]);
//                        minProduct[i][j] = Math.min(minProduct[i][j],
//                                maxProduct[index][j-1]*ability[i]);
//                        }
//
//                    }
//                    if (j == k) result = Math.max(result, dpmax[i]);
//
//                }
//
//
//
//            }
//
//
//
//
//            System.out.println(max);
        }
    }

}

