package com.codefans.basicjava.algorithm.sort;

/**
 * @author: caishengzhi
 * @date: 2017-11-07 14:57
 **/
public class SelectSort extends SortBase {

    /**
     * 从小到大排序, 将第1个元素和剩余的n-1个元素比较, 将最小的放到第1个位置;
     * 然后将第2个元素和剩余的n-2个元素比较, 将第二小的元素放在第2个位置,
     * 以此类推, 直到所有排序完成.
     * @param arr
     */
    public void sortAsc(int[] arr) {
        System.out.println("选择排序--->升序：");
//        for(int i = 0; i < arr.length - 1; i ++) {
//            for(int j = i + 1; j < arr.length; j ++) {
//                if(arr[i] > arr[j]) {
//                    this.swap(arr, i, j);
//                }
//            }
//        }

        //改进的算法，只交换1次
        for(int i = 0; i < arr.length - 1; i ++) {
            int minIndex = i;
            for(int j = i + 1; j < arr.length; j ++) {
                if(arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            if(minIndex != i) {
                this.swap(arr, i, minIndex);
            }
        }

    }

    public void sortDesc(int[] arr) {

        System.out.println("选择排序--->降序：");
//        for(int i = 0; i < arr.length - 1; i ++) {
//            for(int j = i + 1; j < arr.length; j ++) {
//                if(arr[i] < arr[j]) {
//                    this.swap(arr, i, j);
//                }
//            }
//        }

        //改进的算法, 只交换1次
        for(int i = 0; i < arr.length - 1; i ++) {
            int minIndex = i;
            for(int j = i + 1; j < arr.length; j ++) {
                if(arr[minIndex] < arr[j]) {
                    minIndex = j;
                }
            }
            if(minIndex != i) {
                this.swap(arr, i, minIndex);
            }
        }



    }

}
