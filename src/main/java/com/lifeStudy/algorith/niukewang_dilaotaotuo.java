package com.lifeStudy.algorith;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//网易地牢逃脱

/**
 * 主要通过广度优先遍历算法  求出最长的步骤个数
 * 此处x表示纵轴    y表示横轴
 *
 */
public class niukewang_dilaotaotuo {

    static class NodePoint {
        int x;
        int y;
        int stepSize;
        //次点是否可以通过
        boolean isPass = false;
        //是否访问过
        boolean isVisited = false;

        public NodePoint(int x, int y,int stepSize) {
            this.x = x;
            this.y = y;
            this.stepSize = stepSize;
        }

        public void setVisited(boolean visited) {
            isVisited = visited;
        }

        public void setPass(boolean pass) {
            isPass = pass;
        }
    }


    public static void main(String ...args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
//        System.out.println(n + " " + m);
        NodePoint points[][] = new NodePoint[n][m];
//        scanner.nextLine();//去掉空格

        for (int i=0;i<n;i++) {
            String strline = scanner.next();
//            System.out.println(strline);
//            char[] charstr = strline.toCharArray();
            for (int j = 0; j < m; j++) {
                NodePoint nodePoint = new NodePoint(i, j,0);
                if (strline.charAt(j) == '.') {
                    nodePoint.setPass(true);
                } //默认是false
                points[i][j] = nodePoint;
            }
        }

        //起始坐标
        int startX = scanner.nextInt();
        int startY = scanner.nextInt();

        //步长的限制个数
        int K = scanner.nextInt();
        int stX[] = new int[K];
        int stY[] = new int[K];
        for (int p=0;p<K;p++) {
            stX[p] = scanner.nextInt();
            stY[p] = scanner.nextInt();
        }
        scanner.close();
        int stepSize = dfs(points, K, startX, startY, stX, stY, n, m);
        System.out.println(stepSize);
    }

    public static int dfs(NodePoint points[][], int K, int startX, int startY, int stX[], int stY[],int n,int m) {

//        points[startX][startY].isPass = true;
        points[startX][startY].isVisited = true;
        Queue<NodePoint> ques = new LinkedList<>();
        ques.add(points[startX][startY]);
        int maxStepSize = 0;
        while (!ques.isEmpty()) {
            NodePoint poll = ques.poll();
            maxStepSize = maxStepSize < poll.stepSize ? poll.stepSize : maxStepSize;
            for (int i = 0; i < K; i++) {
                int x = poll.x + stX[i];
                int y = poll.y + stY[i];
                if (checkPass(x,y,n,m,points)) {//可通且没有访问过
                    points[x][y].stepSize=poll.stepSize+1;
                    points[x][y].isVisited = true;
                    ques.add(points[x][y]);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (points[i][j].isVisited == false && points[i][j].isPass) {//出现某个节点是通的，但没有访问过，说明没有到次节点的路径。从而此节点作为结束节点时。。。。
                    return -1;
                }
            }

        }
        return  maxStepSize;
    }

    private static boolean checkPass(int x, int y, int n, int m, NodePoint[][] points) {
        if (x>=n||y>=m||x<0||y<0) return false;
        return points[x][y].isPass && points[x][y].isVisited == false;
    }
}
