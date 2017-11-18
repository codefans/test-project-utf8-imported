package com.codefans.basicjava.algorithm.sort;

/**
 * @author: caishengzhi
 * @date: 2017-11-07 14:13
 *
 *
 **/
public class SortBase {

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void print(int[] arr) {
        if(arr != null) {
            for(int i = 0; i < arr.length; i ++) {
                if(i != 0) {
                    System.out.print(", ");
                }
                System.out.print(arr[i]);
            }
            System.out.println();
        }
    }



}
