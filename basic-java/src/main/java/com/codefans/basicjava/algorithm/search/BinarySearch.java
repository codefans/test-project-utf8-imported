package com.codefans.basicjava.algorithm.search;

/**
 * @Author: codefans
 * @Date: 2019-01-06 9:06
 */

public class BinarySearch {

    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();

        int[] arr = new int[]{
            1, 2, 3, 4, 5, 6, 8, 9, 12
        };
//        int data = 0;
        int data = 13;
//        int index = binarySearch.search(arr, data);
        int index = binarySearch.searchTemp(arr, data);
        System.out.println("数字[" + data + "]下标为:[" + index + "]");

        for(int i = 0; i < arr.length; i ++) {
            data = arr[i];
//            index = binarySearch.search(arr, data);
            index = binarySearch.searchTemp(arr, data);
            System.out.println("数字[" + data + "]下标为:[" + index + "]");
        }
    }

    /**
     * 用二分查找法从数组arr中查找数字data, 并返回数字data在数组里下标
     * 遇到的问题：
     *    (1)查找不存在的一个数字时,死循环了. 2019-01-06
     * @param arr
     * @param data
     * @return
     */
    public int searchWrongAnswer(int[] arr, int data) {
        int index = -1;

        int min = 0;
        int mid = 0;
        int max = arr.length - 1;
        //错误点(0),这里应该是min<=max
        while(min < max) {
            //错点(1),这样当data大于数组最大值时,会导致死循环。2019-01-06
            mid = (min + max) / 2;
            //错点(2),这样当data小于数组最小值时,会导致死循环。2019-01-06
//            mid = (min + max + 1) / 2;
            if(arr[mid] > data) {
                max = mid;
            } else if(arr[mid] == data) {
                index = mid;
                break;
            } else if(arr[mid] < data) {
                min = mid;
            }
        }
        return index;
    }

    /**
     * 用二分查找法从数组arr中查找数字data, 并返回数字data在数组里下标
     * 遇到的问题：
     *    如果arr[mid] != data, 那么下标mid也不需要再包含在内了
     * @param arr
     * @param data
     * @return
     */
    public int search(int[] arr, int data) {
        int index = -1;

        int min = 0;
        int mid = 0;
        int max = arr.length - 1;
        while(min <= max) {
            mid = (min + max) / 2;
            if(arr[mid] > data) {
                max = mid - 1;
            } else if(arr[mid] == data) {
                index = mid;
                break;
            } else if(arr[mid] < data) {
                min = mid + 1;
            }
        }
        return index;
    }









    public int searchTemp(int[] arr, int data) {
        int index = -1;

        

        return index;
    }
























}
