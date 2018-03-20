package com.lifeStudy.algorith;


//求解最小子数组问题  分治法
public class TheMaxSubArray {


    /**
     * @param A    待求数组
     * @param low  最小的位置序列
     * @param mid  中间的位置序列
     * @param high 最大的位置序列
     * @return 返回数组A 中跨越中间位置的最大数组和  包括起始和结束边界、最大的和值
     */

    //求出数组A跨越中点mid的最大数组和
    public static ArrarTypes find_max_crossing_subArray(int[] A, int low, int mid, int high) {


        //求出左边数据的最大和
        int left_sum = Integer.MIN_VALUE;
        int max_left = mid;//记录左边最大和的边界位置
        int sum = 0;
        for (int i = mid; i >= low; i--) {
            sum += A[i];
            if (sum > left_sum) {
                left_sum = sum;
                max_left = i;
            }
        }
        //求出右边数据的最大和

        int right_sum = Integer.MIN_VALUE;
        int max_right = mid + 1;
        sum = 0;
        for (int j = mid + 1; j <= high; j++) {

            sum += A[j];
            if (sum > right_sum) {
                right_sum = sum;
                max_right = j;
            }
        }
        return new ArrarTypes(max_left, max_right, left_sum + right_sum);
    }

    /**
     * @param A    目标数组
     * @param low  数组最小位置
     * @param high 数组最大位置
     * @return
     */
    //查找出最大和数组  包含三种情况 在数组集合的左边；在数组集合的右边；跨越数组中心元素集合
    public static ArrarTypes find_max_subArray(int[] A, int low, int high) {

        if (low > high) {
            return null;
        }
        if (low == high) {
            return new ArrarTypes(low, high, A[low]);
        } else {
            int mid = (low + high) / 2;
            ArrarTypes max_subArrayleft = find_max_subArray(A, low, mid);
            ArrarTypes max_subArrayright = find_max_subArray(A, mid + 1, high);
            ArrarTypes max_crossing_subArray = find_max_crossing_subArray(A, low, mid, high);
            if (max_crossing_subArray.getMax_sum() > max_subArrayleft.getMax_sum() && max_crossing_subArray.getMax_sum() > max_subArrayright.getMax_sum()) {
                return max_crossing_subArray;
            } else if (max_subArrayleft.getMax_sum() > max_crossing_subArray.getMax_sum() && max_subArrayleft.getMax_sum() > max_subArrayright.getMax_sum()) {
                return max_subArrayleft;
            } else{
                return max_subArrayright;
            }
        }

    }


    public static void main(String... args) {
        int a[] = new int[]{13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7,0};
        int b[] = new int[]{-1, -5, -7, -12};
        ArrarTypes max_subArray = find_max_subArray(b, 0, b.length - 1);
        System.out.println(max_subArray.toString());
    }


}
