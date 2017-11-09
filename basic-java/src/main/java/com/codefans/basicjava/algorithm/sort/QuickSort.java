package com.codefans.basicjava.algorithm.sort;

/**
 * @author: caishengzhi
 * @date: 2017-11-07 21:36
 *
 * 它的基本思想是：
 *    通过一趟排序将要排序的数据分割成独立的两部分，
 *    其中一部分的所有数据都比另外一部分的所有数据都要小，然后再按此方法对这两部分数据分别进行快速排序，
 * 整个排序过程可以递归进行，以此达到整个数据变成有序序列。
 *
 **/
public class QuickSort extends SortBase {

    /**
     * 设置两个变量i,j排序开始的时候: i=0，j=N-1;
     * 以第一个数组元素作为关键数据, 赋值给key, 即key=A[0];
     * 设置一个flag遍历, 控制i, j的移动. flag=false时移动j;flag=true时移动i;
     * 先从j开始向前搜索, 即由后开始向前搜索(j--), 找到第一个小于key的值A[j], 将A[j]和A[i]互换, i++, flag=!flag;
     * 然后从i开始向后搜索, 即由前开始向后搜索(i++), 找到第一个大于key的A[i], 将A[i]和A[j]互换, j--, flag=!flag;
     * 重复第3,4步, 直到i=j;
     *
     * @param arr
     * @param lowIndex
     * @param highIndex
     */
    public void sortAsc(int[] arr, int lowIndex, int highIndex) {

        int l = lowIndex;
        int h = highIndex;
        int temp = arr[lowIndex];

        if(l < h) {

            boolean flag = false;
            while(l < h) {

                if(!flag) {
                    if (arr[h] < temp) {
                        this.swap(arr, l, h);
                        l++;
                        flag = !flag;
                    } else {
                        h--;
                    }
                }

                if(flag) {
                    if (arr[l] > temp) {
                        this.swap(arr, l, h);
                        h--;
                        flag = !flag;
                    } else {
                        l++;
                    }

                }

            }

            if(l > lowIndex) {
                sortAsc(arr, lowIndex, l - 1);
            }

            if(h < highIndex) {
                sortAsc(arr, l + 1, highIndex);
            }


        }

    }

    public void sortDesc(int[] arr, int lowIndex, int highIndex) {
        int l = lowIndex;
        int h = highIndex;
        int temp = arr[lowIndex];

        if (l < h) {

            boolean flag = false;
            while (l < h) {

                if (!flag) {
                    if (arr[h] > temp) {
                        this.swap(arr, l, h);
                        l++;
                        flag = !flag;
                    } else {
                        h--;
                    }
                }

                if (flag) {
                    if (arr[l] < temp) {
                        this.swap(arr, l, h);
                        h--;
                        flag = !flag;
                    } else {
                        l++;
                    }

                }

            }

            if (l > lowIndex) {
                sortDesc(arr, lowIndex, l - 1);
            }

            if (h < highIndex) {
                sortDesc(arr, h + 1, highIndex);
            }
        }
    }


}
