package com.lifeStudy.algorith;
/**
 * 堆排序
 */
public class heapOrder {

    public static void main(String... args) {
//        构造数据  a[0]不使用 排序数据从1开始 (大顶堆)
        int a[] = new int[]{-1, 4, 6, 2, 87, 56, 34, 37};
        int n = a.length - 1;
        ArraNodes arraNodes = new ArraNodes();
        arraNodes.setA(a);
        arraNodes.setLength(a.length - 1);
        for (int i = n / 2; i >= 1; i--) {//从最后一个非叶节点开始，由下往上维护大顶堆的性质
            KeepHeap(arraNodes, i);
        }
        int tempt;
        for (int i = arraNodes.length; i >= 2; i--) {//输出排好序的元素
            tempt = arraNodes.a[1];
            arraNodes.a[1] = arraNodes.a[i];
            arraNodes.a[i] = tempt;
            arraNodes.length = arraNodes.length - 1;
            KeepHeap(arraNodes, 1);
            System.out.print(arraNodes.a[i]+" ");
        }
        System.out.println(arraNodes.a[1]);
    }

    private static void KeepHeap(ArraNodes arraNodes, int i) {//调增a[i]为根的堆的位置,从下往下下降
        int a[] = arraNodes.getA();
        int left = 2 * i;
        int right = 2 * i + 1;
        int largest;
        if (left <= arraNodes.getLength() && a[left] > a[i]) {
            largest = left;
        } else largest = i;

        if (right <= arraNodes.getLength() && a[right] > a[largest]) {
            largest = right;
        }
        if (largest != i) {//交换数据  并且深入到子节点递归调用
            int tempt = a[i];
            a[i] = a[largest];
            a[largest] = tempt;
            KeepHeap(arraNodes, largest);
        }
        if (right == arraNodes.length || (left == arraNodes.length)) {
            return;
        }
    }

    private static class ArraNodes {

        int a[];
        int length = 0;

        public void setA(int[] a) {
            this.a = a;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public int[] getA() {
            return a;
        }

        public int getLength() {
            return length;
        }
    }
}
