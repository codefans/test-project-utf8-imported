package com.codefans.practicetask.math;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author: caishengzhi
 * @date: 2018-06-15 17:13
 */
public class BigDecimalTest extends AbstractUnitPriceReverse {

    @Test
    public void multiplyTest() {

        //sorted
        BigDecimal[] weights = new BigDecimal[]{
            new BigDecimal("86.50"),
            new BigDecimal("72"),
            new BigDecimal("49.80"),
            new BigDecimal("46.10"),
            new BigDecimal("40.3"),
            new BigDecimal("36.30"),
            new BigDecimal("33"),
            new BigDecimal("30.60"),
            new BigDecimal("30.00"),
            new BigDecimal("29.40"),
            new BigDecimal("27.72"),
            new BigDecimal("20"),
            new BigDecimal("18.00"),
            new BigDecimal("15.30"),
            new BigDecimal("15"),
            new BigDecimal("14.5"),
            new BigDecimal("13.3"),
            new BigDecimal("11"),
            new BigDecimal("10"),
            new BigDecimal("10"),
            new BigDecimal("10"),
            new BigDecimal("3.45"),
            new BigDecimal("3"),
            new BigDecimal("1.00")
        };

        BigDecimal[] defaultUnitPrices = new BigDecimal[] {
            new BigDecimal("357.11"),
            new BigDecimal("33.71"),
            new BigDecimal("190.51"),
            new BigDecimal("190.51"),
            new BigDecimal("92.51"),
            new BigDecimal("92.51"),
            new BigDecimal("39.59"),
            new BigDecimal("70.95"),
            new BigDecimal("68.01"),
            new BigDecimal("92.51"),
            new BigDecimal("268.91"),
            new BigDecimal("31.75"),
            new BigDecimal("131.71"),
            new BigDecimal("168.95"),
            new BigDecimal("200.31"),
            new BigDecimal("117.01"),
            new BigDecimal("26.85"),
            new BigDecimal("97.41"),
            new BigDecimal("35.67"),
            new BigDecimal("9.21"),
//            new BigDecimal("29.68"),
//            new BigDecimal("161.11"),
//            new BigDecimal("61.15"),
//            new BigDecimal("1229.35")
                new BigDecimal("29.81"),
                new BigDecimal("160.92"),
                new BigDecimal("60.95"),
                new BigDecimal("1229.31")
        };

        //unsorted
//        BigDecimal[] weights = new BigDecimal[]{
//                new BigDecimal("46.10"),
//                new BigDecimal("49.80"),
//                new BigDecimal("27.72"),
//                new BigDecimal("15.30"),
//                new BigDecimal("18.00"),
//                new BigDecimal("30.60"),
//                new BigDecimal("36.30"),
//                new BigDecimal("15"),
//                new BigDecimal("40.3"),
//                new BigDecimal("29.40"),
//                new BigDecimal("33"),
//                new BigDecimal("1.00"),
//                new BigDecimal("72"),
//                new BigDecimal("30.00"),
//                new BigDecimal("20"),
//                new BigDecimal("14.5"),
//                new BigDecimal("10"),
//                new BigDecimal("10"),
//                new BigDecimal("11"),
//                new BigDecimal("10"),
//                new BigDecimal("3.45"),
//                new BigDecimal("3"),
//                new BigDecimal("13.3"),
//                new BigDecimal("86.50")
//        };

//        BigDecimal[] defaultUnitPrices = new BigDecimal[]{
//            new BigDecimal("190.51"),
//            new BigDecimal("190.51"),
//            new BigDecimal("268.91"),
//            new BigDecimal("168.95"),
//            new BigDecimal("131.71"),
//            new BigDecimal("70.95"),
//            new BigDecimal("92.51"),
//            new BigDecimal("200.31"),
//            new BigDecimal("92.51"),
//            new BigDecimal("92.51"),
//            new BigDecimal("39.59"),
//            new BigDecimal("1229.35"), //1229.31
//            new BigDecimal("33.71"),
//            new BigDecimal("68.01"),
//            new BigDecimal("31.75"),
//            new BigDecimal("117.01"),
//            new BigDecimal("29.68"), //29.79
//            new BigDecimal("9.21"),
//            new BigDecimal("97.41"),
//            new BigDecimal("35.67"),
//            new BigDecimal("161.11"),
//            new BigDecimal("61.15"),
//            new BigDecimal("26.85"),
//            new BigDecimal("357.11")
//        };

//        super.sortAndPrint(defaultUnitPrices, weights, DESC, false);
//
//        System.out.println("打印单价:");
//        this.print(defaultUnitPrices, true);
//        System.out.println("打印重量:");
//        this.print(weights, true);

        BigDecimal unitPrice = null;
        BigDecimal weight = null;
        BigDecimal itemAmount = null;
        BigDecimal totalPrice = new BigDecimal(0);

        for(int i = 0; i < defaultUnitPrices.length; i ++) {
            unitPrice = defaultUnitPrices[i];
            weight = weights[i];
            itemAmount = multiply(unitPrice, weight, 4);
            System.out.println(itemAmount);
            totalPrice = add(totalPrice, itemAmount);
//            totalPrice = totalPrice.add(itemAmount);
//            System.out.println(unitPrice + " * " + weight + " = " + itemAmount);
        }
        System.out.println("totalPrice:" + totalPrice);


        BigDecimal[] totalPriceArr = new BigDecimal[] {
            new BigDecimal("30890.0150"),
            new BigDecimal("2427.1200"),
            new BigDecimal("9487.3980"),
            new BigDecimal("8782.5110"),
            new BigDecimal("3728.1530"),
            new BigDecimal("3358.1130"),
            new BigDecimal("1306.4700"),
            new BigDecimal("2171.0700"),
            new BigDecimal("2040.3000"),
            new BigDecimal("2719.7940"),
            new BigDecimal("7454.1852"),
            new BigDecimal("635.0000"),
            new BigDecimal("2370.7800"),
            new BigDecimal("2584.9350"),
            new BigDecimal("3004.6500"),
            new BigDecimal("1696.6450"),
            new BigDecimal("357.1050"),
            new BigDecimal("1071.5100"),
            new BigDecimal("356.7000"),
            new BigDecimal("92.1000"),
            new BigDecimal("296.8000"),
            new BigDecimal("555.8295"),
            new BigDecimal("183.4500"),
            new BigDecimal("1229.3700")
        };
        BigDecimal total = new BigDecimal(0);
        for(int i = 0; i < totalPriceArr.length; i ++) {
            total = add(total, totalPriceArr[i]);
        }
        System.out.println("total:" + total);


    }



}
