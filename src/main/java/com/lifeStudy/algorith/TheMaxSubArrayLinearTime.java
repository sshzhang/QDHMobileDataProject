package com.lifeStudy.algorith;

//求线性时间的最大子数组问题
public class TheMaxSubArrayLinearTime {
    //原理:例如A[1.....j]的最大子数组已知，那么A[1......j+1]的最大子数组要么是前一个元素的最大子数组，或者是A[i....j+1]为最大子数组
    private static int[] boundryMax;//边界最大子数组   当前元素的最大子数组等于前一个元素的最大子数组或者前一个元素的边界子数组加当前元素或者等于当前元素
    private static int[] valueMax;//最大子数组
    private static int[][] pos;//最大子数组的边界位置信息

    public static void InitDatas(int vales[]) {

        boundryMax = new int[vales.length];
        valueMax = new int[vales.length];
        //初始化第一个元素的边界最大子数组和最大子数组
        boundryMax[0] = vales[0];
        valueMax[0] = vales[0];
        pos = new int[vales.length][2];
        pos[0][1] = pos[0][0] = 0;
        //求出每一个子数组的最大子数组
        for (int i = 1; i < vales.length; i++) {
            int beforeValueMax = valueMax[i - 1];//前一个最大子数组
            int currentValue = vales[i];
            int beforeBoundryMax = boundryMax[i - 1] + currentValue;
            int max = beforeValueMax > currentValue ? (beforeValueMax > beforeBoundryMax ? beforeValueMax : beforeBoundryMax) : (currentValue > beforeBoundryMax ? currentValue : beforeBoundryMax);
            valueMax[i] = max;
            boundryMax[i] = beforeBoundryMax > currentValue ? beforeBoundryMax : currentValue;
        }
    }

    public static void main(String... args) {
        int a[] = new int[]{13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7, 0};
        int b[] = new int[]{-3, -5, -1, -12};
        InitDatas(b);
        System.out.println(valueMax[valueMax.length - 1]);
    }
}
