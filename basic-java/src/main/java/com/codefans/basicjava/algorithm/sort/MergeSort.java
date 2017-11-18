package com.codefans.basicjava.algorithm.sort;

/**
 * @author: caishengzhi
 * @date: 2017-11-08 14:24
 **/
public class MergeSort extends SortBase {

    /**
     * 　*　<pre>
     * 　*　二路归并
     * 　*　原理：将两个有序表合并和一个有序表
     * 　*　</pre>
     * 　*
     * 　*　@param　a
     * 　*　@param　s
     * 　*　第一个有序表的起始下标
     * 　*　@param　m
     * 　*　第二个有序表的起始下标
     * 　*　@param　t
     * 　*　第二个有序表的结束小标
     * 　*
     *   * 功能是:
     *        将有序的两部分排序合并为一个有序的数组
     *     例如:
     *        int[] a = new int[]{1, 3, 5, 7, 9, 0, 2, 4, 6, 8};
     *        merge(a, 0, 5, 9);
     *        merge之后数组变为:0,1,2,3,4,5,6,7,8,9
     */
    private static void merge(int[] a, int s, int m, int t) {
        int[] tmp = new int[t - s + 1];
        int i = s, j = m, k = 0;
        while (i < m && j <= t) {
            if (a[i] <= a[j]) {
                tmp[k] = a[i];
                k++;
                i++;
            } else {
                tmp[k] = a[j];
                j++;
                k++;
            }
        }
        while (i < m) {
            tmp[k] = a[i];
            i++;
            k++;
        }
        while (j <= t) {
            tmp[k] = a[j];
            j++;
            k++;
        }
        System.arraycopy(tmp, 0, a, s, tmp.length);
    }

    /**
     * 　*
     * 　*　@param　a
     * 　*　@param　s
     * 　*　@param　len
     * 　*　每次归并的有序集合的长度
     */
    public static void mergeSort(int[] a, int s, int len) {
        int size = a.length;
        int mid = size / (len << 1);
//        int c = size & ((len << 1) - 1);

//        int mid = size / (len * 2);
////        int c = size & ((len * 2) - 1);
        int c = size / ((len * 2) - 1);
        System.out.println("s:" + s + ", len:" + len + ", size:" + size + ", mid:" + mid + ", c:" + c);
        //　-------归并到只剩一个有序集合的时候结束算法-------//
        if (mid == 0) {
            return;
        }
        //　------进行一趟归并排序-------//
        for (int i = 0; i < mid; ++i) {
            s = i * 2 * len;
//            merge(a, s, s + len, (len << 1) + s - 1);
            merge(a, s, s + len, (len * 2) + s - 1);
        }
        //　-------将剩下的数和倒数一个有序集合归并-------//
        if (c != 0) {
            merge(a, size - c - 2 * len, size - c, size - 1);
        }
        //　-------递归执行下一趟归并排序------//
        mergeSort(a, 0, 2 * len);
    }


    //将有二个有序数列a[first...mid]和a[mid...last]合并。
    void mergearray(int a[], int first, int mid, int last, int temp[])
    {
        int i = first, j = mid + 1;
        int m = mid,   n = last;
        int k = 0;

        while (i <= m && j <= n)
        {
            if (a[i] > a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }

        while (i <= m) {
            temp[k++] = a[i++];
        }

        while (j <= n) {
            temp[k++] = a[j++];
        }

//        for (i = 0; i < k; i++) {
//            a[first + i] = temp[i];
//        }
//        System.out.println(a + ", " + first + ", " + mid);
        System.arraycopy(temp, 0, a, first, last - first + 1);

    }


    void mergesort(int a[], int first, int last, int temp[])
    {
        if (first < last) {
            int mid = (first + last) / 2;
            mergesort(a, first, mid, temp);    //左边有序
            mergesort(a, mid + 1, last, temp); //右边有序
            System.out.println("first:" + first + ", mid:" + mid + ", last:" + last);
            mergearray(a, first, mid, last, temp); //再将二个有序数列合并
        }
    }

    public static void main(String[] args) {

        MergeSort ms = new MergeSort();
//        int[] a = new int[]{4, 3, 6, 1, 2, 5};
//        int[] a = new int[]{1, 5, 2, 7, 3, 8, 4, 9, 6, 0};
        int[] a = new int[]{5, 1, 2, 7, 8, 3, 4, 9, 6, 0};
//        int[] a = new int[]{1, 3, 5, 7, 9, 0, 2, 4, 6, 8};
//        mergeSort(a, 0, 1);
        ms.mergesort(a, 0, a.length - 1, new int[a.length]);
//        ms.sortArr(a);
//        merge(a, 0, 5, 9);
        ms.print(a);

//        if (c != 0) {
//            merge(a, size - c - 2 * len, size - c, size - 1);
//        }

    }

    public void sortArr(int[] arr) {

//        int[] a = new int[]{5, 1, 2, 7, 8, 3, 4, 9, 6, 0};


        int arrLen = arr.length;
        int[] temp = new int[arrLen];

        int loopCount = arrLen / 2;
        int step = 2;
        for(int i = 0; i < loopCount; i ++) {
//            mergearray(arr, i, (i + (i + 1))/2, (i + 1), temp);
//            if(i * 2 + 1 <= arrLen) {
//                mergearray(arr, i * 2, i * 2 + 1, i * 2 + 1, temp);
                mergeArr(arr, i * 2, i * 2 + 1, i * 2 + 1);
//            }
        }

    }

    private void mergeArr(int[] a, int start, int secStart, int last) {
        int[] tmp = new int[last - start + 1];
        int i = start, j = secStart, k = 0;
        while (i < secStart && j <= last) {
            if (a[i] <= a[j]) {
                tmp[k] = a[i];
                k++;
                i++;
            } else {
                tmp[k] = a[j];
                j++;
                k++;
            }
        }
        while (i < secStart) {
            tmp[k] = a[i];
            i++;
            k++;
        }
        while (j <= last) {
            tmp[k] = a[j];
            j++;
            k++;
        }
        System.arraycopy(tmp, 0, a, start, tmp.length);
    }


    public void sortAsc() {

        System.out.println("归并排序->升序: ");

    }

    public void sortDesc() {

        System.out.println("归并排序->降序: ");

    }

}
