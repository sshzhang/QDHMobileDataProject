package com.lifeStudy.algorith;

//分田地问题 网易
import java.util.Scanner;
/**
 * 通过找出每一个块的取值范围 0-sum
 * 采用二分法 每次取方位内的数据判断是否存在相应的划分
 * 对于给定的最大值std 如何判断是否存在划分呢？
 * 可用采用贪心算法 先选择任意的三横，然后遍历所有的列，查看是否存在相应划分
 */
public class niukewang_fentiandi {
    //存储输入的数据
    public static int InputData[][];
    //表示的是某个区域块的数据和
    public static int area[][];
    public static int m;
    public static int n;

    public static void main(String... args) {

        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        InputData = new int[n + 1][m + 1];
        area = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            String line = scanner.next();
            for (int j = 1; j <= m; j++) {
                InputData[i][j] = line.charAt(j - 1) - '0';
            }
        }
        scanner.close();

        area[1][1] = InputData[1][1];
        for (int i = 2; i <= n; i++) //求出区域块和
            area[i][1] = area[i - 1][1] + InputData[i][1];

        for (int j = 2; j <= m; j++)
            area[1][j] = area[1][j - 1] + InputData[1][j];

        for (int i = 2; i <=n; i++) {
            for (int j = 2; j <= m; j++) {
                area[i][j] = InputData[i][j] + area[i - 1][j] + area[i][j - 1] - area[i - 1][j - 1];
            }
        }

        int l = 0, h = 75 * 75 * 9;
        int mid = 0;
        while (l+1< h) {
             mid = (l + h) / 2;
            boolean overGivenValue = isOverGivenValue(mid);
            if (overGivenValue) {
                l = mid ;
            }else{
                h = mid;
            }
        }
//        System.out.println(l);
        int maxValue = 0;
        if (isOverGivenValue(h))
            maxValue = h;
         else
            maxValue = l;
        System.out.println(maxValue);
    }

    public static boolean isOverGivenValue(int mid) {

        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (ReallyOverTheValues(i, j, k, mid)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static int getA(int i, int j, int k, int q) {
        return area[k][q] - area[i - 1][q] - area[k][j - 1] + area[i - 1][j - 1];
    }

    private static boolean ReallyOverTheValues(int i, int j, int k, int mid) {

        int pre = 0, count = 0;
        for (int p = 1; p <= m; p++) {
            int x1 = getA(1, pre + 1, i, p);
            int x2 = getA(i + 1, pre + 1, j, p);
            int x3 = getA(j + 1, pre + 1, k, p);
            int x4 = getA(k + 1, pre + 1, n, p);
            if (x1 >= mid && x2 >= mid && x3 >= mid && x4 >= mid) {
                pre = p;
                count++;
                if (count == 4) return true;
            }
        }
        return false;
    }
}
