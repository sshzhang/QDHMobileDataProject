package com.lifeStudy.algorith;

public class QuickSort {


    public static void QurickS(int a[], int p, int r) {
        if (p < r) {
            int q = PartitionTheArrays(a, p, r);
            QurickS(a, p, q - 1);
            QurickS(a, q + 1, r);
        }
    }

    //其中的i 和j之间的间隔数据表示为大于给定值x的元素个数   i表示所有小于给定值的序列下标   j表示当前循环到的元素下标
    public static int PartitionTheArrays(int a[], int p, int r) {
        int x = a[r];
        int i = p - 1;
        int tempt;
        for (int j = p; j <= r - 1; j++) {
            if (a[j] <= x) {
                i = i + 1;
                //交换a[i]和a[j]
                if (i != j) {
                    tempt = a[i];
                    a[i] = a[j];
                    a[j] = tempt;
                }
            }
        }
        tempt = a[i + 1];
        a[i + 1] = a[r];
        a[r] = tempt;
        return i + 1;
    }

    public static void main(String... args) {
        int a[] = new int[]{-1, 4, 6, 2, 87, 56, 34, 37};
        QurickS(a, 1, a.length - 1);
        System.out.println(a);
    }


}
