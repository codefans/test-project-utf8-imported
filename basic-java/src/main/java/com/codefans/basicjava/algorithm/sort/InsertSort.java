package com.codefans.basicjava.algorithm.sort;

import sun.misc.Sort;

/**
 * @author: caishengzhi
 * @date: 2017-11-07 15:20
 *
 *  将数组分为有序部分和无序部分, 第一个元素作为默认有序部分, 第二个元素开始为无序部分
 *  从第二个元素开始, 将待排序元素存入一个临时变量, 依次与有序部分从后往前(有序部分的下标从大到小)进行比较,
 *  如果待排序元素比有序部分的元素小, 则将有序部分往后移动一位, 并将当前有序部分元素的下标赋值给index;
 *  当待排序元素与有序部分都进行一轮比较后, 将当前待排序元素插入下标index处;
 *  以此类推, 直到所有排序完成.
 *
 **/
public class InsertSort extends SortBase {

    public void sortAsc(int[] arr) {
        System.out.println("插入排序--->升序：");
        for(int i = 1; i < arr.length; i ++) {
            int index = i;
            int temp = arr[i];
            for(int j = i - 1; j >= 0; j --) {
                if(arr[j] > temp) {
                    arr[j + 1] = arr[j];
                    index = j;
                }
            }
            arr[index] = temp;
//            this.print(arr);
        }
    }

    public void sortDesc(int[] arr) {
        System.out.println("插入排序--->降序：");
        for(int i = 1; i < arr.length; i ++) {
            int index = i;
            int temp = arr[i];
            for(int j = i - 1; j >= 0; j --) {
                if(arr[j] < temp) {
                    arr[j + 1] = arr[j];
                    index = j;
                }
            }
            arr[index] = temp;
        }
    }

}
