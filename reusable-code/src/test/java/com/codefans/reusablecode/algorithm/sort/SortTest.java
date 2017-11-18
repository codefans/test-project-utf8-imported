package com.codefans.reusablecode.algorithm.sort;

import org.junit.Test;

/**
 * @author: caishengzhi
 * @date: 2017-10-09 14:36
 **/
public class SortTest {

    private int[] arr = new int[]{4, 7, 8, 5, 1, 9, 2, 6, 0, 3};

    @Test
    public void sortTest() {
//        this.insertSortTest();
//        this.insertSortWhileTest();
//        this.shellSortTest();

        this.quickSortTest();

    }





    public void quickSortTest() {

        this.quickSort(arr, 0, arr.length - 1);
        System.out.println("快速排序后:");
        this.print(arr);


    }

    public void quickSort(int[] arr, int low, int high) {

        int l = low;
        int h = high;
        int tmp = arr[low];

        while(l < h) {

            while(l < h && arr[h] >= tmp) {
                h--;
                if(l < h) {
                    int data = arr[h];
                    arr[h] = arr[l];
                    arr[l] = data;
                    l++;
                }
            }

            while(l < h && arr[l] <= tmp) {
                l++;
                if(l < h) {
                    int data = arr[h];
                    arr[h] = arr[l];
                    arr[l] = data;
                    h--;
                }

            }

            if(l > low) {
                quickSort(arr, low, l - 1);
            }

            if(h < high) {
                quickSort(arr, l + 1, high);
            }


        }

    }


    public void print(int[] arr) {
        for(int i = 0; i < arr.length; i ++) {
            if(i != 0) {
                System.out.print(", ");
            }
            System.out.print(arr[i]);
        }
        System.out.println();
    }




}
