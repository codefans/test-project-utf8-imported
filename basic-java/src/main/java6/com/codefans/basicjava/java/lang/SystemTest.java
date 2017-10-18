package com.codefans.basicjava.java.lang;

import org.junit.Test;

/**
 * @author: caishengzhi
 * @date: 2017-10-10 18:09
 **/
public class SystemTest {

    @Test
    public void systemTest() {
        this.arraycopyTest();
    }

    public void arraycopyTest() {

        Object[] elementData = new Object[]{1,2,3,4,5,6,7,8,9,10};
        int size = elementData.length;
        int index = 2;
        this.print(elementData);

        int numMoved = size - index - 1;
        // 从"index+1"开始，用后面的元素替换前面的元素。
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index,
                    numMoved);
        }
        // 将最后一个元素设为null
        elementData[--size] = null;

        this.print(elementData);

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

    public void print(Object[] arr) {
        for(int i = 0; i < arr.length; i ++) {
            if(i != 0) {
                System.out.print(", ");
            }
            System.out.print(arr[i]);
        }
        System.out.println();
    }


}
