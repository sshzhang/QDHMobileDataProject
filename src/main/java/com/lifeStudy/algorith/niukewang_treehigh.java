package com.lifeStudy.algorith;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class niukewang_treehigh {


    public static void main(String... args) {

        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int datas[][] = new int[N][2];
        int newdatas[][] = new int[N][2];
        Node root = null, tempt = null;
        for (int j = 1; j < N; j++) {
            int parentsId = scanner.nextInt();
            int childrenId = scanner.nextInt();
            datas[j][0] = parentsId;
            datas[j][1] = childrenId;
        }
        scanner.close();
        newdatas[1][0] = datas[1][0];
        newdatas[1][1] = datas[1][1];
        for (int j = 2, i; j < N; j++) {
            for (i = j - 1; i >= 1 && datas[j][0] < newdatas[i][0]; i--) {
                newdatas[i + 1][0] = newdatas[i][0];
                newdatas[i + 1][1] = newdatas[i][1];
            }
            newdatas[i + 1][0] = datas[j][0];
            newdatas[i + 1][1] = datas[j][1];
        }
        System.out.println();
        for (int i = 1; i < N; i++) {
            System.out.println(newdatas[i][0] + " " + newdatas[i][1]);
        }

        for (int i = 1; i < N; i++) {
            if (root == null) {
                tempt = root = new Node(newdatas[i][0]);
                Node leftNode = new Node(newdatas[i][1]);
                root.setLeft(leftNode);
            } else {
//              添加头节点到优先队列中
                PriorityQueue nods = new PriorityQueue<Node>();
                nods.add(tempt);
                Node parentNpde = traverOpenVisit(nods, newdatas[i][0]);
//                对多叉树 和数据输入有误处理
                if (parentNpde == null)
                    return;
                if (parentNpde.left != null && parentNpde.right != null) return;
                if (parentNpde.left != null) {
                    parentNpde.right = new Node(newdatas[i][1]);
                } else {
                    parentNpde.left = new Node(newdatas[i][1]);
                }
            }
        }

        System.out.println(deepTheValue(tempt));
    }


    static class Node implements Comparable<Node> {
        private Node left;
        private Node right;
        private int values;

        public Node(int values) {
            this.left = null;
            this.right = null;
            this.values = values;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public void setValues(int values) {
            this.values = values;
        }

        @Override
        public int compareTo(Node o) {
            return this.values - o.values;
        }
    }

    public static Node traverOpenVisit(Queue<Node> nodes, int parents) {

        if (!nodes.isEmpty()) {

            Node poll = nodes.poll();
            if (poll.values == parents) return poll;

            if (poll.left != null) {
                nodes.add(poll.left);
            }
            if (poll.right != null) {
                nodes.add(poll.right);
            }
            return traverOpenVisit(nodes, parents);
        }
        return null;
    }

    public static int deepTheValue(Node root) {
        if (root == null)
            return 0;
        int leftDepth = deepTheValue(root.left) + 1;
        int rightDepth = deepTheValue(root.right) + 1;
        return leftDepth > rightDepth ? leftDepth : rightDepth;
    }
}
