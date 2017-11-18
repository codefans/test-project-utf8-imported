package com.codefans.basicjava.algorithm.sort;

import org.junit.Test;

/**
 * @author: caishengzhi
 * @date: 2017-11-07 14:44
 *
 *  冒择入希快归堆
 *
 **/
public class SortTest extends SortBase {

    int[] arr = new int[]{1, 5, 2, 7, 3, 8, 4, 9, 6, 0};

    @Test
    public void bubbleSortTest() {
//        new BubbleSort().sortAsc(arr);
        new BubbleSort().sortDesc(arr);
        this.print(arr);
    }

    @Test
    public void selectSortTest() {
//        new SelectSort().sortAsc(arr);
        new SelectSort().sortDesc(arr);
        this.print(arr);
    }

    @Test
    public void insertSortTest() {
//        new InsertSort().sortAsc(arr);
        new InsertSort().sortDesc(arr);
        this.print(arr);
    }

    @Test
    public void shellSortTest() {
        new ShellSort().sortAsc(arr);
//        new ShellSort().sortDesc(arr);
        this.print(arr);
    }

    @Test
    public void quickSortTest() {
//        System.out.println("快速排序--->升序：");
//        new QuickSort().sortAsc(arr, 0, arr.length - 1);
        System.out.println("快速排序--->降序: ");
        new QuickSort().sortDesc(arr, 0, arr.length - 1);
        this.print(arr);
    }

    @Test
    public void mergeSortTest() {
        System.out.println("归并排序->降序: ");
        new MergeSort().sortAsc();
        this.print(arr);
    }






































    /**
     * 14:21
     * 从小到大, 最小的从后往前冒
     * 从后往前, 相邻的两个数两两相比较, 将较小值换到前面的位置;
     *
     * 从小到大, 最大的从前往后冒
     * 从大到小, 最大的从后往前冒
     * 从大到小, 最小的从前往后冒
     *
     */
    public void bubbleSort() {
        //从小到大, 最小的从后往前冒
        for(int i = 0; i < arr.length - 1; i ++) {
            for(int j = arr.length - 1; j > i; j --) {
                if(arr[j] < arr[j - 1]) {
                    swap(arr, j, j -1);
                }
            }
            this.print(arr);
        }
        System.out.println("bubble sort complete, 从小到大, 最小的从后往前冒");

        //从小到大, 最大的从前往后冒
//        for(int i = arr.length - 1; i > 0; i --) {
//            for(int j = 0; j < i; j ++) {
//                if(arr[j] > arr[j + 1]) {
//                    swap(arr, j, j + 1);
//                }
//            }
//            this.print(arr);
//        }
//        System.out.println("bubble sort complete, 从小到大, 最大的从前往后冒");

        //从大到小, 最大的从后往前冒
//        for(int i = 0; i < arr.length - 1; i ++) {
//            for(int j = arr.length - 1; j > i ; j --) {
//                if(arr[j] > arr[j - 1]) {
//                    swap(arr, j, j -1);
//                }
//            }
//            this.print(arr);
//        }
//        System.out.println("bubble sort complete, 从大到小, 最大的从后往前冒");

        //从大到小, 最小的从前往后冒
//        for(int i = arr.length - 1; i > 0; i --) {
//            for(int j = 0; j < i; j ++) {
//                if(arr[j] < arr[j + 1]) {
//                    swap(arr, j, j + 1);
//                }
//            }
//            this.print(arr);
//        }
//        System.out.println("bubble sort complete, 从大到小, 最小的从前往后冒");

    }

    /**
     * 从小到大排序, 将第1个元素和剩余的n-1个元素比较, 将最小的放到第1个位置;
     * 然后将第2个元素和剩余的n-2个元素比较, 将第二小的元素放在第2个位置,
     * 以此类推, 直到所有排序完成.
     */
    public void selectSort() {
        for(int i = 0; i < arr.length - 1; i ++) {
            for(int j = i + 1; j < arr.length; j ++) {
                if(arr[j] < arr[i]) {
                    swap(arr, j, i);
                }
            }
            this.print(arr);
        }
        System.out.println("select sort complete");
    }

    /**
     *
     */
    public void insertSort() {
        for(int i = 1; i < arr.length; i ++) {
            int j = 0;
            int temp = arr[i];
            int insertPosition = i;
            while(arr[j] < arr[i] && j <= i - 1) {
//                swap(arr, j, i);
                arr[j + 1] = arr[j];
                insertPosition  = j;
                j++;
            }
            arr[insertPosition] = temp;
            this.print(arr);
        }
        System.out.println("insert sort complete");
    }


    /**
     * 将数组分为有序部分和无序部分，有序部分最开始默认为第一个元素，第2到第n-1个元素为待排序部分
     * 取出待排序部分的第一个元素作为待插入数，与有序部分从后往前依次比较(下标从从高到底)，
     * 如果当前有序数据比待插入数据大，则将该有序数据后移一位，有序部分待排序下标前移一位；
     * 以此类推，直到第一个待排序数和所有有序部分数据都进行过比较，最后将待排序插入到有序部分或原位置；以此类推, 直到排序结束
     */
    public void insertSortTestOld() {
        for(int i = 1; i < arr.length; i ++) {
            int end = i - 1;
            int j = end;
            int tmp = arr[i];

            for(; j >= 0 && arr[j] > tmp; j --) {
                arr[j + 1] = arr[j];
            }
            //如果有序部分没有比待排序数大的，那么就直接将待排序数插入它原来所在位置
            //如果有序部分有比待排序数大的，则将待排序数插入当前有序数据的位置。正常应该是将tmp赋值给arr[j]的，但是for循环最后有个j--，所以这里赋值给的是arr[j+1]
            arr[j + 1] = tmp;

        }

        this.print(arr);

    }

    public void insertSortWhileTest() {

        for(int i = 1; i < arr.length; i ++) {
            int end = i - 1;
            int j = end;
            int tmp = arr[i];

            while(j >= 0 && arr[j] > tmp) {
                arr[j + 1] = arr[j];
                j--;
            }
            //如果有序部分没有比待排序数大的，那么就直接将待排序数插入它原来所在位置
            //如果有序部分有比待排序数大的，则将待排序数插入当前有序数据的位置。正常应该是将tmp赋值给arr[j]的，但是for循环最后有个j--，所以这里赋值给的是arr[j+1]
            arr[j + 1] = tmp;

        }

        this.print(arr);

    }


    public void shellSortTestOld() {

        int stepLen = arr.length;

        while(true) {
            stepLen = stepLen / 2;

            for(int i = 0; i < stepLen; i ++) {

                for(int j = i + stepLen; j < arr.length; j = j + stepLen) {

                    int tmp = arr[j];
                    int k = j - stepLen;

                    for(; k >= 0 && arr[k] > tmp; k = k - stepLen) {
                        arr[k + stepLen] = arr[k];
                    }
                    arr[k + stepLen] = tmp;

                }

            }
            if(stepLen == 1) {
                break;
            }

        }

        System.out.println("希尔排序后:");
        this.print(arr);


    }








}
