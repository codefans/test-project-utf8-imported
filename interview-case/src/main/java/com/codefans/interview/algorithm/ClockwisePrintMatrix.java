package com.codefans.interview.algorithm;

/**
 * @Author: ShengzhiCai
 * @Date: 2018-04-02 20:46
 */

public class ClockwisePrintMatrix {

    //输出1,2,3,4->8,12,16->15,14,13->9,5->6,7->11,10
    public static void main(String[] args) {
//        int[][] matrix = new int[][] {
//            {1}
//        };
//        int[][] matrix = new int[][] {
//                {1,2,3,4}
//        };
        int[][] matrix = new int[][] {
                {1,2,3,4},
                {5,6,7,8}
        };
//        int[][] matrix = new int[][] {
//                {1,2,3,4},
//                {5,6,7,8},
//                {9,10,11,12}
//        };
//        int[][] matrix = new int[][] {
//                {1,2,3,4},
//                {5,6,7,8},
//                {9,10,11,12},
//                {13,14,15,16}
//        };
        ClockwisePrintMatrix cpm = new ClockwisePrintMatrix();
        cpm.print(matrix);
    }

    /**
    (0,0)
    (0,1)
    (0,2)
    (0,3)

    (1,3)
    (2,3)
    (3,3)

    (3,2)
    (3,1)
    (3,0)

    (2,0)
    (1,0)

    (1,1)
    (1,2)

    (2,2)

    (2,1)
    **/
    public void print(int[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        int start = 0;
        while(columns > start * 2 && rows > start * 2) {
            this.printMatrixClockwisely(matrix, columns, rows, start);
            start++;
        }

    }

    public void printMatrixClockwisely(int[][] matrix, int columns, int rows, int start) {
        int endX = rows - 1 - start;
        int endY = columns - 1 - start;
        for(int i = start; i <= endX; i ++) {
            int number = matrix[start][i];
            this.printNumber(number);
        }

        if(start < endY) {
            for(int i = start + 1; i <= endY; i ++) {
                int number = matrix[i][endX];
                this.printNumber(number);
            }
        }

        if(start < endX && start < endY) {
            for(int i = endX - 1; i >= start; --i) {
                int number = matrix[endY][i];
                this.printNumber(number);
            }
        }

        if(start < endX && start < endY - 1) {
            for(int i = endY - 1; i > start + 1; --i) {
                int number = matrix[i][start];
                this.printNumber(number);
            }
        }



    }

    public void printNumber(int number) {
        System.out.print(number + ", ");
    }

}
