package com.codefans.basicjava.algorithm.sort;

import org.junit.Test;

/**
 * @author: caishengzhi
 * @date: 2017-11-07 17:04
 *
 *
 *
 **/
public class ShellSort extends SortBase {

    /**
     *
     * 希尔排序, 又叫缩小增量排序
     * 假如待排序数组包含n个元素, 那么先以一个小于n的正整数step(例如将数组长度n对2向下取整, 即step=n/2)作为步长,
     * 把所有序号相隔step的数组元素放一组，组内进行直接插入排序;
     * 然后取一个小于step的step(例如step=step/2)，重复上述分组和排序操作;
     * 直至step=1，即所有记录放进一个组中, 组内进行插入排序后, 整个排序结束.
     *
     * @param arr
     */
    public void sortAsc(int[] arr) {

        System.out.println("希尔排序->升序排序:");
        int len = arr.length;
        int step = len;

        while(true) {

            step = step / 2;
            System.out.println("step:" + step);
            for(int i = 0; i < step; i ++) {

                for(int j = i + step; j < len; j = j + step) {
                    int index = j;
                    int temp = arr[j];
                    for(int k = j - step; k >= i; k = k - step) {
                        if(arr[k] > temp) {
                            arr[k + step] = arr[k];
                            index = k;
                        }
                    }
                    arr[index] = temp;
                }

            }

            if(step == 1) {
                break;
            }

        }

    }

    public void sortDesc(int[] arr) {

        System.out.println("希尔排序->降序排序:");

        int len = arr.length;
        int step = len;

        while(true) {

            step = step / 2;
            for(int i = 0; i < step; i ++) {

                for(int j = i + step; j < len; j = j + step) {
                    int index = j;
                    int temp = arr[j];
                    for(int k = j - step; k >= i; k = k - step) {
                        if(arr[k] < temp) {
                            arr[k + step] = arr[k];
                            index = k;
                        }
                    }
                    arr[index] = temp;
                }

            }

            if(step == 1) {
                break;
            }

        }




    }


}
