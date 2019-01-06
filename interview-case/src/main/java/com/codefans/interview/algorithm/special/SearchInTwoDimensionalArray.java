package com.codefans.interview.algorithm.special;

/**
 * @Author: codefans
 * @Date: 2018-12-30 21:55
 * 一、自己实现的方法
 * 首先要弄清楚二维数组横坐标和纵坐标怎么表示的
 *      (0,0),(0,1),(0,2),(0,3)
 *      (1,0),(1,1),(1,2),(1,3)
 *      (2,0),(2,1),(2,2),(2,3)
 *      (3,0),(3,1),(3,2),(3,3)
 *
 * 算法描述：
 *      1.从起点(0,0)开始,先从横向扫描,如果数字小于目标数data,则纵坐标加1;如果数字等于data,循环结束;如果数字大于data,则改为纵向扫描。
 *      2.改为纵向扫描后,如果数字小于目标数data,则横坐标加1;如果数字等于data,循环结束;如果数字大于data,则将起点沿着右下对角线移动.
 *      3.起点(0,0)沿右下对角线移动后为(1,1),然后又转向横向扫描,及循环步骤1.
 *
 * 算法实现：
 *      1.定义三个下标(originX,originY),(xIndexX,xIndexY),(yIndexX,yIndexY)
 *          (originX,originY)表示从起点沿右下对角线移动的下标,
 *          (xIndexX,xIndexY)表示横向移动的下标,
 *          (yIndexX,yIndexY)表示纵向移动的下标,
 *          (xIndexX,xIndexY)=(originX,originY);
 *          (yIndexX,yIndexY)=(originX,originY);
 *
 *      2.定义三个变量loopFlag、found、operation
 *          loopFlag用来控制是否继续循环,初始值为true,
 *          found用来表示是否找到目标数字,初始值为false,
 *          operation表示当前执行什么操作,有1,2,3三个值,初始值为1
 *              1:执行横向操作
 *              2:执行纵向操作
 *              3:起点沿右下对角线移动一格
 *      3.先横向扫描,如果arr[xIndexX][xIndexY]小于目标数data,则纵坐标xIndexY加1;如果arr[xIndexX][xIndexY]等于data,循环结束;如果arr[xIndexX][xIndexY]大于data,则改为纵向扫描,即设置operation=2。
 *      4.改为纵向扫描后,如果arr[yIndexX][yIndexY]小于目标数data,则横坐标yIndexX加1;如果arr[yIndexX][yIndexY]等于data,循环结束;如果arr[yIndexX][yIndexY]大于data,则将起点沿着右下对角线移动,即设置operation=3.
 *      5.起点沿右下对角线移动一格, 即originX++, originY++; 然后横向移动和纵向移动的起点重新设置为新的起点,即:
 *          (xIndexX,xIndexY)=(originX,originY);
 *          (yIndexX,yIndexY)=(originX,originY);
 *          设置完后再转向横向扫描,即设置operation=1.也就是继续从步骤3开始循环,直到找到目标数字或者数组遍历结束.
 *
 * 二、《Offer面试指南》解法
 *      1.选取数组右上角的数字
 *      2.如果该数字等于要查找的数字，查找过程结束；
 *        如果该数字大于要查找的数字，剔除这个数字所在的列
 *        如果该数字小于要查找的数字，剔除这个数字所在的行
 *
 */

public class SearchInTwoDimensionalArray {

    public static void main(String[] args) {
        SearchInTwoDimensionalArray searchInTwoDimensionalArray = new SearchInTwoDimensionalArray();
        searchInTwoDimensionalArray.startup();
    }

    public void startup() {
        int[][] arr = new int[][] {
            {1,2,8,9},
            {2,4,9,12},
            {4,7,10,13},
            {6,8,11,15}
        };

        System.out.println(arr[0][3]);
        System.out.println(arr[3][0]);
        System.out.println(arr.length);
        System.out.println(arr[0].length);

        for(int i = 0; i < arr.length; i ++) {
            for(int j = 0; j < arr[i].length; j ++) {
                System.out.println("(" + i + "," + j + ")");
            }
        }

        int data = 6;
//        int data = 9;
//        int data = 10;
//        int data = 12;
//        int data = 15;
//        int data = 16;

        int[] unitTestData = new int[]{
            6,9,10,12,15,16
        };

        boolean isFound = false;
        for(int i = 0; i < unitTestData.length; i ++) {
            data = unitTestData[i];
//          isFound = findBySelf(arr, data);
          isFound = findByOfficial(arr, data);
//            isFound = findInterviewTemp(arr, data);
            System.out.println("数字[" + data + "]" + (isFound ? "已找到" : "未找到"));
        }

    }

    public boolean findBySelf(int[][] arr, int data) {

        int originX = 0;
        int originY = 0;
        int xIndexX = originX;
        int xIndexY = originY;
        int yIndexX = originX;
        int yIndexY = originY;

        boolean loopFlag = true;
        boolean found = false;

        /**
         * 1横向找
         * 2纵向找
         * 3初始位置右下角移动
         */
        int operation = 1;
        int loopTimes = 0;
        while(loopFlag) {
            loopTimes ++;

            if(operation == 1) {
                if(xIndexX <= arr.length - 1 && xIndexY <= arr[0].length - 1) {
                    if (arr[xIndexX][xIndexY] < data) {
                        xIndexX++;
                    } else if (arr[xIndexX][xIndexY] == data) {
                        System.out.println("found[" + data + "],location[" + xIndexX + "," + xIndexY + "]");
                        loopFlag = false;
                        found = true;
                    } else if (arr[xIndexX][xIndexY] > data) {
                        operation = 2;
                    }
                } else {
                    operation = 2;
//                    loopFlag = false;
                }
            } else if(operation == 2) {
                if(yIndexX <= arr.length - 1 && yIndexY <= arr[0].length - 1) {
                    if (arr[yIndexX][yIndexY] < data) {
                        yIndexY++;
                    } else if (arr[yIndexX][yIndexY] == data) {
                        System.out.println("found[" + data + "],location[" + yIndexX + "," + yIndexY + "]");
                        loopFlag = false;
                        found = true;
                    } else if (arr[yIndexX][yIndexY] > data) {
                        operation = 3;
                    }
                } else {
//                    loopFlag = false;
                    operation = 3;
                }
            } else if(operation == 3) {
                originX ++;
                originY ++;
                xIndexX = originX;
                xIndexY = originY;
                yIndexX = originX;
                yIndexY = originY;
                operation = 1;
                if(yIndexX > arr.length - 1 && yIndexY > arr[0].length - 1) {
                    loopFlag = false;
                }
            } else {
                System.out.println("unknown operation");
                loopFlag = false;
            }
        }

        System.out.println("loopTimes:" + loopTimes);

        return found;
    }

    /**
     *  二、《Offer面试指南》解法
     *      1.选取数组右上角的数字
     *      2.如果该数字等于要查找的数字，查找过程结束；
     *        如果该数字大于要查找的数字，剔除这个数字所在的列
     *        如果该数字小于要查找的数字，剔除这个数字所在的行
     * @param arr
     * @param data
     * @return
     */
    public boolean findByOfficial(int[][] arr, int data) {

        boolean found = false;

        int indexX = 0;
        int indexY = arr[0].length - 1;
        boolean loopFlag = true;
        while(indexX <= arr.length - 1 && indexY >= 0 && loopFlag) {
            if(arr[indexX][indexY] > data) {
                indexY--;
            } else if(arr[indexX][indexY] == data) {
                System.out.println("found[" + data + "],location[" + indexX + "," + indexY + "]");
                found = true;
                loopFlag = false;
            } else if(arr[indexX][indexY] < data){
                indexX++;
            }
        }

        return found;
    }

    public boolean findInterviewTemp(int[][] arr, int data) {

        boolean found = false;



        return found;

    }























}
