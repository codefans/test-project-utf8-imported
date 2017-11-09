package com.codefans.basicjava.algorithm.sort;

/**
 * @author: caishengzhi
 * @date: 2017-11-07 14:39
 *
 *
 *
 **/
public class BubbleSort extends SortBase {

    /**
     * 算法描述：
     *    从后往前, 相邻的两个数两两相比较, 将较小值换到前面的位置;
     * @param arr
     */
    public void sortAsc(int[] arr) {
        System.out.println("冒泡排序--->升序：");
        for(int i = 0; i < arr.length; i ++) {
            for(int j = arr.length - 1; j > i; j --) {
                if(arr[j] < arr[j - 1]) {
                    this.swap(arr, j, j - 1);
                }
            }
        }
    }

    /**
     * 算法描述：
     *    从后往前, 相邻的两个数两两相比较, 将较大值换到前面的位置;
     * @param arr
     */
    public void sortDesc(int[] arr) {
        System.out.println("冒泡排序--->降序：");
        for(int i = 0; i < arr.length; i ++) {
            for(int j = arr.length - 1; j > i; j --) {
                if(arr[j] > arr[j - 1]) {
                    this.swap(arr, j, j - 1);
                }
            }
        }
    }

}
