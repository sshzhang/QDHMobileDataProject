package com.lifeStudy.algorith;

public class MatrixDefault {//通过Straseen实现矩阵相乘


    private static void StrassenProcess(int N, int MatrixA[][], int MatrixB[][], int MatrixResult[][]) {

        if (N == 0) {
            return;
        } else if (N == 1) {
            MatrixResult[0][0] = MatrixA[0][0] * MatrixB[0][0];
        } else {
            int A11[][] = new int[N / 2][N / 2];
            int A12[][] = new int[N / 2][N / 2];
            int A21[][] = new int[N / 2][N / 2];
            int A22[][] = new int[N / 2][N / 2];

            int B11[][] = new int[N / 2][N / 2];
            int B12[][] = new int[N / 2][N / 2];
            int B21[][] = new int[N / 2][N / 2];
            int B22[][] = new int[N / 2][N / 2];

            int C11[][] = new int[N / 2][N / 2];
            int C12[][] = new int[N / 2][N / 2];
            int C21[][] = new int[N / 2][N / 2];
            int C22[][] = new int[N / 2][N / 2];

            for (int i = 0; i < N / 2; i++) {
                for (int j = 0; j < N / 2; j++) {
                    A11[i][j] = MatrixA[i][j];
                    A12[i][j] = MatrixA[i][j + N / 2];
                    A21[i][j] = MatrixA[i + N / 2][j];
                    A22[i][j] = MatrixA[i + N / 2][j + N / 2];

                    B11[i][j] = MatrixB[i][j];
                    B12[i][j] = MatrixB[i][j + N / 2];
                    B21[i][j] = MatrixB[i + N / 2][j];
                    B22[i][j] = MatrixB[i + N / 2][j + N / 2];
                }
            }

            //M1....M7矩阵的计算
            //递归求解M1
            int halfSize = N / 2;
            int AResult1[][] = new int[N / 2][N / 2];
            int BResult[][] = new int[N / 2][N / 2];
            int M1[][] = new int[N / 2][N / 2];
            for (int i = 0; i < N / 2; i++) {
                for (int j = 0; j < N / 2; j++) {
                    AResult1[i][j] = A11[i][j] + A22[i][j];
                    BResult[i][j] = B11[i][j] + B22[i][j];
                }
            }
            StrassenProcess(halfSize, AResult1, BResult, M1);

            //求M2
            int AResult2[][] = new int[N / 2][N / 2];
            int M2[][] = new int[N / 2][N / 2];
            for (int i = 0; i < N / 2; i++) {
                for (int j = 0; j < N / 2; j++) {
                    AResult2[i][j] = A21[i][j] + A22[i][j];
                }
            }
            StrassenProcess(halfSize, AResult2, B11, M2);

            int BResult3[][] = new int[N / 2][N / 2];
            int M3[][] = new int[N / 2][N / 2];
            for (int i = 0; i < N / 2; i++) {
                for (int j = 0; j < N / 2; j++) {
                    BResult3[i][j] = B12[i][j] - B22[i][j];
                }
            }
            StrassenProcess(halfSize, A11, BResult3, M3);

            int BResult4[][] = new int[N / 2][N / 2];
            int M4[][] = new int[N / 2][N / 2];
            for (int i = 0; i < N / 2; i++) {
                for (int j = 0; j < N / 2; j++) {
                    BResult4[i][j] = B21[i][j] - B11[i][j];
                }
            }
            StrassenProcess(halfSize, A22, BResult4, M4);

            int AResult5[][] = new int[N / 2][N / 2];
            int M5[][] = new int[N / 2][N / 2];
            for (int i = 0; i < N / 2; i++) {
                for (int j = 0; j < N / 2; j++) {
                    AResult5[i][j] = A11[i][j] + A12[i][j];
                }
            }
            StrassenProcess(halfSize, AResult5, B22, M5);

            int AResult6[][] = new int[N / 2][N / 2];
            int BResult6[][] = new int[N / 2][N / 2];
            int M6[][] = new int[N / 2][N / 2];
            for (int i = 0; i < N / 2; i++) {
                for (int j = 0; j < N / 2; j++) {
                    AResult6[i][j] = A21[i][j] - A11[i][j];
                    BResult6[i][j] = B11[i][j] + B12[i][j];
                }
            }
            StrassenProcess(halfSize, AResult6, BResult6, M6);

            int AResult7[][] = new int[N / 2][N / 2];
            int BResult7[][] = new int[N / 2][N / 2];
            int M7[][] = new int[N / 2][N / 2];
            for (int i = 0; i < N / 2; i++) {
                for (int j = 0; j < N / 2; j++) {
                    AResult7[i][j] = A12[i][j] - A22[i][j];
                    BResult7[i][j] = B21[i][j] + B22[i][j];
                }
            }
            StrassenProcess(halfSize, AResult7, BResult7, M7);

            //计算结果子矩阵
            for (int i = 0; i < N / 2; i++) {
                for (int j = 0; j < N / 2; j++) {
                    C11[i][j] = M1[i][j] + M4[i][j] - M5[i][j] + M7[i][j];
                    C12[i][j] = M3[i][j] + M5[i][j];
                    C21[i][j] = M2[i][j] + M4[i][j];
                    C22[i][j] = M1[i][j] + M3[i][j] - M2[i][j] + M6[i][j];
                }
            }

            for (int i = 0; i < N / 2; i++) {
                for (int j = 0; j < N / 2; j++) {
                    MatrixResult[i][j] = C11[i][j];
                    MatrixResult[i][j + N / 2] = C12[i][j];
                    MatrixResult[i + N / 2][j] = C21[i][j];
                    MatrixResult[i + N / 2][j + N / 2] = C22[i][j];
                }
            }


        }
    }

    private static void CangeText(int te[][]) {
        for (int i = 0; i < te.length; i++) {
            for (int j = 0; j < te[i].length; j++) {
                te[i][j] = 1;
            }
        }
    }

    public static void main(String... args) {

        int MatrixA[][] = new int[4][5];
        int MatrixB[][] = new int[5][4];
        int MatrixResult[][] = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                MatrixA[i][j] = MatrixB[i][j] = 2;
            }
        }
        StrassenProcess(5, MatrixA, MatrixB, MatrixResult);

        System.out.println(MatrixResult);
    }
}
