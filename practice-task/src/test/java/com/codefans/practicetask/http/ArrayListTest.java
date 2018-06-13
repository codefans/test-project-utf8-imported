package com.codefans.practicetask.http;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: mpif
 * @date: 2018-06-13 14:52
 */
public class ArrayListTest {

    @Test
    public void test() {

        List<Integer> itemlist = new ArrayList<Integer>();
        itemlist.add(new Integer(1));
        itemlist.add(new Integer(2));
        itemlist.add(new Integer(3));
        itemlist.add(new Integer(4));
        itemlist.add(new Integer(5));

        this.print(itemlist);

        itemlist.set(2, new Integer(11));

        this.print(itemlist);

    }

    public void print(List<Integer> list) {
        for(int i = 0; i < list.size(); i ++) {
            if(i != 0) {
                System.out.print(", ");
            }
            System.out.print(list.get(i));
        }
        System.out.println();
    }




}
