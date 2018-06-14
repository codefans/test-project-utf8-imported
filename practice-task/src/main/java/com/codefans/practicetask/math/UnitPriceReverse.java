package com.codefans.practicetask.math;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Author: ShengzhiCai
 * @Date: 2018-06-13 20:45
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
 * 2018-06-13
 * 新增非嵌套for循环算法实现
 *
 */

public class UnitPriceReverse {

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
     * 在默认单价基础上向上或向下浮动的步长
     */
    private BigDecimal unitPriceStep = new BigDecimal("0.1");

    /**
     * 价格与下标转换的系数，由于价格是保留两位小数，所以乘以100后，就是整数，就可以作为数组下标进行遍历
     * 由价格保留几位小数决定
     */
    private BigDecimal priceIndexRadio = new BigDecimal("100");

    /**
     * 重量列表
     */
    private List<BigDecimal> weightList = new ArrayList<BigDecimal>();

    /**
     * 单件商品总价列表
     */
    private List<BigDecimal> unitTotalPriceList = new ArrayList<BigDecimal>();

    public static void main(String[] args) {
        UnitPriceReverse task = new UnitPriceReverse();
        task.execute();

    }

    public void execute() {

//        this.readLinesFromFile();
        this.executeNoForNestNew();

    }


    /**
     * 非嵌套for循环-总金额差额分配到部分订单上
     *
     * 10.00 1.00 10.00
     * 20.00 2.00 40.00
     * 30.00 3.00 90.00
     *
     * 差额：23
     * 最小值：1.01 2.00 3.00
     * 最大值：x从1开始，步长为1向上增长，第一个x使下列等式成立：1.00*x+2.00*x+3.00*x>23
     *
     */
    public void executeNoForNestNew() {

        long startTime = System.currentTimeMillis();

        totalPrice = new BigDecimal("88800.00");

        //运费：2700
        System.out.println("totalPrice:" + totalPrice);

        BigDecimal[] weights = new BigDecimal[]{
                new BigDecimal("46.10"),
                new BigDecimal("49.80"),
                new BigDecimal("27.72"),
                new BigDecimal("15.30"),
                new BigDecimal("18.00"),
                new BigDecimal("30.60"),
                new BigDecimal("36.30"),
                new BigDecimal("15"),
                new BigDecimal("40.3"),
                new BigDecimal("29.40"),
                new BigDecimal("33"),
                new BigDecimal("1.00"),
                new BigDecimal("72"),
                new BigDecimal("30.00"),
                new BigDecimal("20"),
                new BigDecimal("14.5"),
                new BigDecimal("10"),
                new BigDecimal("10"),
                new BigDecimal("11"),
                new BigDecimal("10"),
                new BigDecimal("3.45"),
                new BigDecimal("3"),
                new BigDecimal("13.3"),
                new BigDecimal("86.50")
        };

        weightList = Arrays.asList(weights);

//        BigDecimal[] defaultUnitPrices = new BigDecimal[]{
//            new BigDecimal("186.2"),
//            new BigDecimal("186.2"),
//            new BigDecimal("264.6"),
//            new BigDecimal("164.64"),
//            new BigDecimal("127.4"),
//            new BigDecimal("66.64"),
//            new BigDecimal("88.2"),
//            new BigDecimal("196"),
//            new BigDecimal("88.2"),
//            new BigDecimal("88.2"),
//            new BigDecimal("35.28"),
//            new BigDecimal("1225"),
//            new BigDecimal("29.4"),
//            new BigDecimal("63.7"),
//            new BigDecimal("27.44"),
//            new BigDecimal("112.7"),
//            new BigDecimal("25.48"),
//            new BigDecimal("4.9"),
//            new BigDecimal("93.1"),
//            new BigDecimal("31.36"),
//            new BigDecimal("156.8"),
//            new BigDecimal("56.84"),
//            new BigDecimal("22.54"),
//            new BigDecimal("352.8")
//        };

        BigDecimal[] defaultUnitPrices = new BigDecimal[]{
            new BigDecimal("190.51"),
            new BigDecimal("190.51"),
            new BigDecimal("268.91"),
            new BigDecimal("168.95"),
            new BigDecimal("131.71"),
            new BigDecimal("70.95"),
            new BigDecimal("92.51"),
            new BigDecimal("200.31"),
            new BigDecimal("92.51"),
            new BigDecimal("92.51"),
            new BigDecimal("39.59"),
            new BigDecimal("1229.37"), //1229.31
            new BigDecimal("33.71"),
            new BigDecimal("68.01"),
            new BigDecimal("31.75"),
            new BigDecimal("117.01"),
            new BigDecimal("29.68"), //29.79
            new BigDecimal("9.21"),
            new BigDecimal("97.41"),
            new BigDecimal("35.67"),
            new BigDecimal("161.11"),
            new BigDecimal("61.15"),
            new BigDecimal("26.85"),
            new BigDecimal("357.11")
        };

//        进行升序排序并打印数组
//        this.sortAndPrint(defaultUnitPrices, weights, false);
//        System.exit(0);

        BigDecimal total = new BigDecimal("0");
        BigDecimal step = new BigDecimal("4.31");
        BigDecimal multiSteps = new BigDecimal("0");

//        this.printAddedUnitPrice(step, defaultUnitPrices);
        this.printResult(null, defaultUnitPrices, weights);
        System.exit(0);

//        System.out.println("total before:");
//        BigDecimal totalPrice = new BigDecimal("0");
//        for(int i = 0; i < defaultUnitPrices.length; i ++) {
//            totalPrice = this.add(totalPrice, this.multiply(defaultUnitPrices[i], weights[i]));
//        }
//        System.out.println(totalPrice);
//
//        System.out.println("total after:");
//        totalPrice = new BigDecimal("0");
//        for(int i = 0; i < defaultUnitPrices.length; i ++) {
//            totalPrice = this.add(totalPrice, this.multiply(this.add(defaultUnitPrices[i], step), weights[i]));
//        }
//        System.out.println(totalPrice);
//        System.exit(0);

        BigDecimal maxUnitPrice = this.add(this.add(totalPrice, new BigDecimal("2700")), new BigDecimal("626.27"));
        BigDecimal unitPrice = null;
        BigDecimal weight = null;
        BigDecimal addedWeight = null;
        step = new BigDecimal("0.01");
        BigDecimal minAbs = totalPrice;
        BigDecimal minTotal = new BigDecimal("0");
        String msg = "";

        boolean run = true;
        int index = 1;
//        whileLoop:
        while(run) {
//            System.out.print(".");
            multiSteps = this.add(multiSteps, step);
            for (int i = 0; i < defaultUnitPrices.length; i++) {
                unitPrice = defaultUnitPrices[i];
                weight = weights[i];
                total = this.add(total, this.multiply(weight, this.add(unitPrice, multiSteps)));
//                if (total.compareTo(totalPrice) > 0 && total.compareTo(maxUnitPrice) < 0) {
                if (this.subtract(total, totalPrice).abs().compareTo(minAbs) <= 0) {
//                    System.out.println("total: " + total + ", addedUnitPrice:" + this.add(defaultUnitPrices[i], multiSteps) + ", multiSteps:" + multiSteps + ", finalSum:" + this.getTotalPriceAfterAddedUnitPrice(multiSteps, i, defaultUnitPrices, weights) + ", index:" + index + ", i:" + i);
                    minAbs = this.subtract(total, totalPrice).abs();
                    minTotal = total;
                    msg = "total: " + total + ", addedUnitPrice:" + this.add(defaultUnitPrices[i], multiSteps) + ", multiSteps:" + multiSteps + ", finalSum:" + this.getTotalPriceAfterAddedUnitPrice(multiSteps, i, defaultUnitPrices, weights) + ", index:" + index + ", i:" + i;

                } else if(total.compareTo(maxUnitPrice) > 0) {
                    System.out.println("已经超出最大值, 最大值为:[" + maxUnitPrice + "], total:[" + total + "]");
                    System.out.println("total: " + total + ", addedUnitPrice:" + this.add(defaultUnitPrices[i], multiSteps) + ", multiSteps:" + multiSteps + ", finalSum:" + this.getTotalPriceAfterAddedUnitPrice(multiSteps, i, defaultUnitPrices, weights) + ", index:" + index + ", i:" + i);
                    run = false;
                }
            }
            total = new BigDecimal("0");
            index++;
        }

        System.out.println("mintotal:" + minTotal);
        System.out.println(msg);

        long endTime = System.currentTimeMillis();
        System.out.println("total cost:[" + (endTime - startTime) / 1000 + "s]");


    }

    public BigDecimal getTotalPriceAfterAddedUnitPrice(BigDecimal added, int index, BigDecimal[] defaultUnitPrice, BigDecimal[] weights) {
        BigDecimal total = new BigDecimal("0");

        BigDecimal unitPrice = null;
        BigDecimal weight = null;

        for(int i = 0; i < defaultUnitPrice.length; i ++) {
            unitPrice = defaultUnitPrice[i];
            weight = weights[i];
            if(i <= index) {
                unitPrice = this.add(unitPrice, added);
            }
            total = this.add(total, this.multiply(unitPrice, weight));

        }

        return total;
    }

    public Map<String, BigDecimal[]> getSortByWeight(BigDecimal[] unitPriceArr, BigDecimal[] weightArr) {
        Map<String, BigDecimal[]> resMap = new HashMap<String, BigDecimal[]>();

        BigDecimal weightTemp = null;
        BigDecimal priceTemp = null;

        for(int i = 0; i < weightArr.length - 1; i ++) {
            for(int j = i + 1; j < weightArr.length; j ++) {
                if(weightArr[j].compareTo(weightArr[i]) == -1) {
//                    weightTemp = weightArr[j];
//                    weightArr[j] = weightArr[i];
//                    weightArr[i] = weightTemp;
                    this.swap(weightArr, i, j);
                    this.swap(unitPriceArr, i, j);
                }
            }
        }
        resMap.put("weightArr", weightArr);
        resMap.put("priceArr", unitPriceArr);

        return resMap;
    }

    public void swap(BigDecimal[] arr, int i, int j) {
        BigDecimal temp = null;
        temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
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
        maxLoop = totalPrice.divide(new BigDecimal(productAmount), 2, BigDecimal.ROUND_HALF_UP).multiply(priceIndexRadio).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
        return maxLoop;
    }

    public int generateBeginIndex(BigDecimal defaultPrice, BigDecimal priceStep) {
        int beginIndex = 0;
        beginIndex = defaultPrice.subtract(priceStep).multiply(priceIndexRadio).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
        return beginIndex;
    }

    public int generateEndIndex(BigDecimal defaultPrice, BigDecimal priceStep) {
        int beginIndex = 0;
        beginIndex = defaultPrice.add(priceStep).multiply(priceIndexRadio).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
        return beginIndex;
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

    public void print(BigDecimal[] arr) {
        for(int i = 0; i < arr.length; i ++) {
            if(i != 0) {
                System.out.print(", ");
            }
            System.out.print(arr[i]);
        }
        System.out.println();
    }

    public void printResult(BigDecimal unitPriceStep, BigDecimal[] defaultUnitPrices, BigDecimal[] weights) {
        BigDecimal total = new BigDecimal("0");
        BigDecimal unitPrice = null;
        BigDecimal itemTotal = null;
        for(int i = 0; i < defaultUnitPrices.length; i ++) {
            if(unitPriceStep != null) {
                unitPrice = this.add(defaultUnitPrices[i], unitPriceStep);
            } else {
                unitPrice = defaultUnitPrices[i];
            }
            itemTotal = this.multiply(unitPrice, weights[i]);
            System.out.println(unitPrice + " * " + weights[i] + " = " + itemTotal);
            total = this.add(total, itemTotal);
        }
        System.out.println("合计:" + total);
    }

    public void printAddedUnitPrice(BigDecimal unitPriceStep, BigDecimal[] defaultUnitPrices) {
        BigDecimal unitPrice = null;
        for(int i = 0; i < defaultUnitPrices.length; i ++) {
            unitPrice = this.add(defaultUnitPrices[i], unitPriceStep);
            System.out.println(unitPrice);

        }
    }

    public void sortAndPrint(BigDecimal[] defaultUnitPrices, BigDecimal[] weights, boolean print) {
        if(print) {
            System.out.println("before sorted:");
            System.out.println("weight array:");
            this.print(weights);
            System.out.println("price array:");
            this.print(defaultUnitPrices);
        }
        Map<String, BigDecimal[]> resMap = this.getSortByWeight(defaultUnitPrices, weights);
        BigDecimal[] weightArr = resMap.get("weightArr");
        BigDecimal[] priceArr = resMap.get("priceArr");
        if(print) {
            System.out.println("after sorted:");
            System.out.println("weight array:");
            this.print(weightArr);
            System.out.println("price array:");
            this.print(priceArr);
        }
    }

    public void readLinesFromFile() {

        try {

            InputStream is = UnitPriceReverse.class.getResourceAsStream("lines.txt");
            Scanner sc = new Scanner(is);

            String line = "";
            while(sc.hasNextLine()) {
                line = sc.nextLine();
                if(line != null && line.trim().length() > 0) {
                    System.out.println("new BigDecimal(\"" + line.trim() + "\"),");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

    }


}
