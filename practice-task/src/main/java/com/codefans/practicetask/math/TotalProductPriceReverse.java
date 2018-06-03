package com.codefans.practicetask.math;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: ShengzhiCai
 * @Date: 2018-06-02 23:34
 *
 * 功能描述：
 *
 * 单价   重量    商品总价
 * x01  1.20      y01
 * x02  2.40      y02
 * x03  3.60      y03
 *               100.00
 *
 * x01、x02、x03、y01、y02、y03都是两位小数的浮点数
 * 已知的只有重量和所有产品的总价,求所有符合条件的单价和商品总价。
 * 如果只有这些已知条件，会有很多组符合条件的数据，而且程序执行也需要很长时间。
 * 所以最好给出一个参考的单价列表，然后找出与参考单价最接近的数据。
 *
 * 求解方法：
 *    遍历所有可能的结果，单价以步长0.01增长去遍历。
 *
 *    步长要尽可能小，才能尽可能多的匹配符合条件的数据。但是也会是遍历次数增长100倍。
 * 得出结果的时间会比较长。
 *
 * 解决这个问题的方法：
 *    可以给出参考单价，然后以(单价-10)*100作为遍历的起点，以(单价+10)*100作为遍历的终点
 *
 * 也可以自动计算参考单价，单价从1开始，以步长1开始增长，算出最大的单价列表。
 * 这种方法有个问题:可能有点单价很高，有的单价很低，所以最好还是给出参考单价，这样最精确。
 *
 */

public class TotalProductPriceReverse {

    private BigDecimal totalPrice;
    private int productAmount;

    /**
     * 单价列表
     */
    private List<BigDecimal> unitPriceList = new ArrayList<BigDecimal>();

    /**
     * 参考单价列表
     */
    private List<BigDecimal> defaultUnitPriceList = new ArrayList<BigDecimal>();

    /**
     * 重量列表
     */
    private List<BigDecimal> weightList = new ArrayList<BigDecimal>();

    /**
     * 单件商品总价列表
     */
    private List<BigDecimal> unitTotalPriceList = new ArrayList<BigDecimal>();

    public static void main(String[] args) {
        TotalProductPriceReverse task = new TotalProductPriceReverse();
        task.execute();

    }

    public void execute() {

        this.executeMethod01();

        this.executeMethod02();

    }

    /**
     * 几条数据，就几个for循环，只能处理特定少量的数据
     */
    public void executeMethod01() {

        long startTime = System.currentTimeMillis();

        totalPrice = new BigDecimal("21191.60");
        System.out.println("totalPrice:" + totalPrice);

        BigDecimal[] weights = new BigDecimal[]{
                new BigDecimal("27.50"),
                new BigDecimal("219.60")
//            new BigDecimal("6.00")
        };
        weightList = Arrays.asList(weights);

        BigDecimal defaultUnitPrice01 = new BigDecimal("191.1");
        BigDecimal defaultUnitPrice02 = new BigDecimal("66.64");

        int beginIndex01 = generateBeginIndex(defaultUnitPrice01, new BigDecimal("10"));
        int beginIndex02 = generateBeginIndex(defaultUnitPrice02, new BigDecimal("10"));
        System.out.println("beginIndex01:" + beginIndex01);
        System.out.println("beginIndex02:" + beginIndex02);

        int endIndex01 = generateEndIndex(defaultUnitPrice01, new BigDecimal("10"));
        int endIndex02 = generateEndIndex(defaultUnitPrice02, new BigDecimal("10"));
        System.out.println("endIndex01:" + endIndex01);
        System.out.println("endIndex02:" + endIndex02);

        productAmount = weightList.size();
        System.out.println("productAmount:" + productAmount);

        unitPriceList = generateUnitPrice(productAmount);

        int maxLoop = generateMaxLoop(totalPrice, productAmount);
        System.out.println("maxLoop:" + maxLoop);

//        System.out.println(addUnitPrice(new BigDecimal("0.01")));

        int index = 0;
        BigDecimal weight01 = weights[index++];
        BigDecimal weight02 = weights[index++];
//        BigDecimal weight03 = weights[index++];

        BigDecimal unitPrice01 = null;
        BigDecimal unitPrice02 = null;
        BigDecimal unitPrice03 = null;

        BigDecimal total01 = null;
        BigDecimal total02 = null;
        BigDecimal total03 = null;
        BigDecimal total = null;

        int resultIndex = 1;

        forLoop:
        for(int i = beginIndex01; i <= endIndex01; i ++) {
            for(int j = beginIndex02; j <= endIndex02; j ++) {
//                for(int k = 1; k <= maxLoop; k ++) {
                unitPrice01 = this.multiply(new BigDecimal("0.01"), new BigDecimal(i));
                unitPrice02 = this.multiply(new BigDecimal("0.01"), new BigDecimal(j));
//                    unitPrice03 = this.multiply(new BigDecimal("0.01"), new BigDecimal(k));

                total01 = this.multiply(weight01, unitPrice01);
                total02 = this.multiply(weight02, unitPrice02);
//                    total03 = this.multiply(weight03, unitPrice03);
//                    total = this.add(this.add(total01, total02), total03);
                total = this.add(total01, total02);

//                    System.out.println("unitPrice01:[" + unitPrice01 + "], unitPrice02:[" + unitPrice02 + "], unitPrice03:[" + unitPrice03 + "], total:[" + total + "]");

                if(totalPrice.compareTo(total) == 0) {
//                        System.out.println("i:" + i + ", j:" + j + ", k:" + k);

                    System.out.println("================result:[" + (resultIndex++) + "]=================");
                    System.out.println(unitPrice01 + " * " + weight01 + " = " + total01);
                    System.out.println(unitPrice02 + " * " + weight02 + " = " + total02);
//                        System.out.println(unitPrice03 + " * " + weight03 + " = " + total03);
//                        System.out.println(total01 + " + " + total02 + " + " + total03 + " = " + total);
                    System.out.println(total01 + " + " + total02 + " = " + total);

//                        break forLoop;
                }

//                }
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("total cost:[" + (endTime - startTime) / 1000 + "s]");

    }

    /**
     * 非嵌套for循环
     */
    public void executeMethod02() {

        long startTime = System.currentTimeMillis();

        totalPrice = new BigDecimal("21191.60");
        System.out.println("totalPrice:" + totalPrice);

        BigDecimal[] weights = new BigDecimal[]{
                new BigDecimal("27.50"),
                new BigDecimal("219.60")
//            new BigDecimal("6.00")
        };
        weightList = Arrays.asList(weights);

//        BigDecimal defaultUnitPrice01 = new BigDecimal("191.1");
//        BigDecimal defaultUnitPrice02 = new BigDecimal("66.64");
        BigDecimal[] defaultUnitPrices = new BigDecimal[]{
            new BigDecimal("191.1"),
            new BigDecimal("66.64")
        };

        defaultUnitPriceList = Arrays.asList(defaultUnitPrices);
        this.calculate(totalPrice, weightList, defaultUnitPriceList);

        long endTime = System.currentTimeMillis();
        System.out.println("total cost:[" + (endTime - startTime) / 1000 + "s]");


    }

    public void calculate(BigDecimal totalPrice, List<BigDecimal> weightList, List<BigDecimal> defaultUnitPriceList) {

//        int beginIndex01 = generateBeginIndex(defaultUnitPrice01, new BigDecimal("10"));
//        int beginIndex02 = generateBeginIndex(defaultUnitPrice02, new BigDecimal("10"));
//        System.out.println("beginIndex01:" + beginIndex01);
//        System.out.println("beginIndex02:" + beginIndex02);
//
//        int endIndex01 = generateEndIndex(defaultUnitPrice01, new BigDecimal("10"));
//        int endIndex02 = generateEndIndex(defaultUnitPrice02, new BigDecimal("10"));
//        System.out.println("endIndex01:" + endIndex01);
//        System.out.println("endIndex02:" + endIndex02);
//
//        productAmount = weightList.size();
//        System.out.println("productAmount:" + productAmount);
//
//        unitPriceList = generateUnitPrice(productAmount);
//
//        int maxLoop = generateMaxLoop(totalPrice, productAmount);
//        System.out.println("maxLoop:" + maxLoop);
//
////        System.out.println(addUnitPrice(new BigDecimal("0.01")));
//
//        int index = 0;
//        BigDecimal weight01 = weights[index++];
//        BigDecimal weight02 = weights[index++];
////        BigDecimal weight03 = weights[index++];
//
//        BigDecimal unitPrice01 = null;
//        BigDecimal unitPrice02 = null;
//        BigDecimal unitPrice03 = null;
//
//        BigDecimal total01 = null;
//        BigDecimal total02 = null;
//        BigDecimal total03 = null;
//        BigDecimal total = null;
//
//        int resultIndex = 1;
//
//        forLoop:
//        for(int i = beginIndex01; i <= endIndex01; i ++) {
//            for(int j = beginIndex02; j <= endIndex02; j ++) {
////                for(int k = 1; k <= maxLoop; k ++) {
//                unitPrice01 = this.multiply(new BigDecimal("0.01"), new BigDecimal(i));
//                unitPrice02 = this.multiply(new BigDecimal("0.01"), new BigDecimal(j));
////                    unitPrice03 = this.multiply(new BigDecimal("0.01"), new BigDecimal(k));
//
//                total01 = this.multiply(weight01, unitPrice01);
//                total02 = this.multiply(weight02, unitPrice02);
////                    total03 = this.multiply(weight03, unitPrice03);
////                    total = this.add(this.add(total01, total02), total03);
//                total = this.add(total01, total02);
//
////                    System.out.println("unitPrice01:[" + unitPrice01 + "], unitPrice02:[" + unitPrice02 + "], unitPrice03:[" + unitPrice03 + "], total:[" + total + "]");
//
//                if(totalPrice.compareTo(total) == 0) {
////                        System.out.println("i:" + i + ", j:" + j + ", k:" + k);
//
//                    System.out.println("================result:[" + (resultIndex++) + "]=================");
//                    System.out.println(unitPrice01 + " * " + weight01 + " = " + total01);
//                    System.out.println(unitPrice02 + " * " + weight02 + " = " + total02);
////                        System.out.println(unitPrice03 + " * " + weight03 + " = " + total03);
////                        System.out.println(total01 + " + " + total02 + " + " + total03 + " = " + total);
//                    System.out.println(total01 + " + " + total02 + " = " + total);
//
////                        break forLoop;
//                }
//
////                }
//            }
//        }

    }

    private List<BigDecimal> generateUnitPrice(int productAmount) {
        List<BigDecimal> unitPriceList = new ArrayList<BigDecimal>();
        for(int i = 0; i < productAmount; i ++) {
            unitPriceList.add(new BigDecimal("0.01"));
        }
        return unitPriceList;
    }

    public int generateMaxLoop(BigDecimal totalPrice, int productAmount) {
        int maxLoop = 0;
        int scale = 2;
        maxLoop = totalPrice.divide(new BigDecimal(productAmount), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
        return maxLoop;
    }

    public int generateBeginIndex(BigDecimal defaultPrice, BigDecimal priceStep) {
        int beginIndex = 0;
        beginIndex = defaultPrice.subtract(priceStep).multiply(new BigDecimal("100")).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
        return beginIndex;
    }

    public int generateEndIndex(BigDecimal defaultPrice, BigDecimal priceStep) {
        int beginIndex = 0;
        beginIndex = defaultPrice.add(priceStep).multiply(new BigDecimal("100")).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
        return beginIndex;
    }

    public BigDecimal addUnitPrice(BigDecimal unitPrice) {
        BigDecimal addedUnitPrice = unitPrice.add(new BigDecimal("0.01")).setScale(2, BigDecimal.ROUND_HALF_UP);
        return addedUnitPrice;
    }

    public BigDecimal devide(BigDecimal num01, BigDecimal num02) {
        return num01.divide(num02, 2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal multiply(BigDecimal num01, BigDecimal num02) {
        return num01.multiply(num02).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal add(BigDecimal num01, BigDecimal num02) {
        return num01.add(num02).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal subtract(BigDecimal num01, BigDecimal num02) {
        return num01.subtract(num02).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

}
