package com.codefans.practicetask.http;

import org.junit.Test;

import java.math.BigDecimal;
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

        BigDecimal[] defaultUnitPrices = new BigDecimal[]{
                new BigDecimal("186.2"),
                new BigDecimal("186.2"),
                new BigDecimal("264.6"),
                new BigDecimal("164.64"),
                new BigDecimal("127.4"),
                new BigDecimal("66.64"),
                new BigDecimal("88.2"),
                new BigDecimal("196"),
                new BigDecimal("88.2"),
                new BigDecimal("88.2"),
                new BigDecimal("35.28"),
                new BigDecimal("1225"),
                new BigDecimal("29.4"),
                new BigDecimal("63.7"),
                new BigDecimal("27.44"),
                new BigDecimal("112.7"),
                new BigDecimal("25.48"),
                new BigDecimal("4.9"),
                new BigDecimal("93.1"),
                new BigDecimal("31.36"),
                new BigDecimal("156.8"),
                new BigDecimal("56.84"),
                new BigDecimal("22.54"),
                new BigDecimal("352.8")
        };

        BigDecimal total = new BigDecimal("2698.18");
        for(int i = 0; i < defaultUnitPrices.length; i ++) {
            System.out.println(devide(total, defaultUnitPrices[i]));
        }


    }

    public BigDecimal devide(BigDecimal num01, BigDecimal num02) {
        return num01.divide(num02, 2, BigDecimal.ROUND_HALF_UP);
//        return num01.divide(num02);
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
