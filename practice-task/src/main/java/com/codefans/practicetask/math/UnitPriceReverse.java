package com.codefans.practicetask.math;

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

//        this.executeMethod01();
//        this.executeNoForNest();
        this.executeNoForNestNew();
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

        int index = 0;
        BigDecimal weight01 = weights[index++];
        BigDecimal weight02 = weights[index++];

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
    public void executeNoForNest() {

        long startTime = System.currentTimeMillis();

//        totalPrice = new BigDecimal("88800.00");
        totalPrice = new BigDecimal("30408.46");
        System.out.println("totalPrice:" + totalPrice);

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
        BigDecimal[] weights = new BigDecimal[]{
                new BigDecimal("46.10"),
                new BigDecimal("49.80"),
                new BigDecimal("27.72"),
                new BigDecimal("15.30")
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
                new BigDecimal("186.2"),
                new BigDecimal("186.2"),
                new BigDecimal("264.6"),
                new BigDecimal("164.64")
        };

        defaultUnitPriceList = Arrays.asList(defaultUnitPrices);
        this.calculate(totalPrice, weightList, defaultUnitPriceList);

        long endTime = System.currentTimeMillis();
        System.out.println("total cost:[" + (endTime - startTime) / 1000 + "s]");


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

//        totalPrice = new BigDecimal("88800.00");
//        totalPrice = new BigDecimal("30408.46");
        totalPrice = new BigDecimal("29903.96");

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

        defaultUnitPriceList = Arrays.asList(defaultUnitPrices);
        this.calculate(totalPrice, weightList, defaultUnitPriceList);

        long endTime = System.currentTimeMillis();
        System.out.println("total cost:[" + (endTime - startTime) / 1000 + "s]");


    }

    public Map<String, BigDecimal[]> getSortByWeight(BigDecimal[] unitPriceArr, BigDecimal[] weightArr) {
        Map<String, BigDecimal[]> resMap = new HashMap<String, BigDecimal[]>();



        return resMap;
    }

    /**
     * 总价
     * @param totalPrice
     * 重量列表
     * @param weightList
     * 参考单价列表
     * @param defaultUnitPriceList
     */
    public void calculate(BigDecimal totalPrice, List<BigDecimal> weightList, List<BigDecimal> defaultUnitPriceList) {

        List<Integer> beginIndexList = new ArrayList<Integer>(defaultUnitPriceList.size());
        List<Integer> endIndexList = new ArrayList<Integer>(defaultUnitPriceList.size());

        List<Integer> lengthList = new ArrayList<Integer>();
        List<Integer> indexList = new ArrayList<Integer>();

        int beginIndex = 0;
        int endIndex = 0;
        int length = 0;
        long totalLoop = 1L;

        BigDecimal defaultUnitPrice = null;
        List<List<Integer>> unitPriceList = new ArrayList<List<Integer>>();
        List<Integer> itemList = null;
        for(int i = 0; i < defaultUnitPriceList.size(); i ++) {
            defaultUnitPrice = defaultUnitPriceList.get(i);
            //将默认单价上下浮动一定步长(unitPriceStep)，作为穷举的开始和结束下标
            beginIndex = generateBeginIndex(defaultUnitPrice, unitPriceStep);
            endIndex = generateEndIndex(defaultUnitPrice, unitPriceStep);
            length = endIndex - beginIndex + 1;
            totalLoop *= length;

            itemList = new ArrayList<Integer>();
            for(int j = beginIndex; j <= endIndex; j ++) {
                itemList.add(j);
            }

            beginIndexList.add(beginIndex);
            endIndexList.add(endIndex);
            lengthList.add(length);
            indexList.add(new Integer("0"));
            unitPriceList.add(itemList);
        }

        if(beginIndexList.size() != endIndexList.size()) {
            throw new RuntimeException("beginIndexList和endIndexList大小不一致!");
        }

        boolean process = true;
        BigDecimal weight = null;
        int index = 1;
        int commonSize = unitPriceList.size();
        itemList = null;
        int firstLen = 0;
        int firstIndex = 0;
        int currentLen = 0;
        int currentIndex = 0;
        BigDecimal total = new BigDecimal(0);
        int item = 0;
        BigDecimal singlePrice = null;
        BigDecimal singleTotalPrice = null;

        List<BigDecimal> singlePriceList = new ArrayList<BigDecimal>();
        List<BigDecimal> singleTotalPriceList = new ArrayList<BigDecimal>();

        System.out.println("遍历总次数:" + totalLoop);
        long loopTimes = 0;

        while(process) {
            loopTimes++;
            if(loopTimes % 10000000 == 0) {
                System.out.println("当前已遍历次数为:" + loopTimes);
            }

            total = new BigDecimal(0);
            for(int i = 0; i < commonSize; i ++) {
                if(i == 0) {
                    firstLen = lengthList.get(i);
                    firstIndex = indexList.get(i);
                    currentIndex = firstIndex;
                    //如果第一行的下标等于第一行的长度减1,那么说明遍历结束了,退出循环
                    if(currentIndex == firstLen - 1) {
                        process = false;
                    }
                } else {
                    currentLen = lengthList.get(i);
                    currentIndex = indexList.get(i);

                    //从第二行开始, 如果当前行的下标走到最大值, 则：
                    //1.将上一行的下标加1;
                    //2.将本行的下标置为0;
                    if(currentIndex == currentLen - 1) {
                        int lastIndex = indexList.get(i - 1);
                        indexList.set(i - 1, lastIndex + 1);
                        indexList.set(i, 0);
                        break;
                    }
                }

                itemList = unitPriceList.get(i);
                item = itemList.get(currentIndex);

                //由于item是价格乘以priceIndexRadio后的作为索引下标使用的整数，所以这里要再除以priceIndexRadio
                singlePrice = this.devide(new BigDecimal(item), priceIndexRadio);
                //单价乘以重量
                singleTotalPrice = this.multiply(singlePrice, weightList.get(i));
                singlePriceList.add(singlePrice);
                singleTotalPriceList.add(singleTotalPrice);

                total = total.add(singleTotalPrice);

                //从最后一行的下标开始加
                if(i == commonSize - 1) {
                    indexList.set(i, currentIndex + 1);
                }


            }

            if(totalPrice.compareTo(total) == 0) {

                System.out.println("================result:[" + (index) + "]=================");
                for(int j = 0; j < singlePriceList.size(); j ++) {
                    singlePrice = singlePriceList.get(j);
                    weight = weightList.get(j);
                    singleTotalPrice = singleTotalPriceList.get(j);
                    System.out.println(singlePrice + " * " + weight + " = " + singleTotalPrice);
                }

                for(int k = 0; k < singleTotalPriceList.size(); k ++) {
                    singleTotalPrice = singleTotalPriceList.get(k);
                    if(k == singleTotalPriceList.size() - 1) {
                        System.out.println(singleTotalPrice + " = " + total);
                    } else {
                        System.out.print(singleTotalPrice + " + ");
                    }
                }
                index++;

            }

            //不管有没有找到匹配的，都需要清空这两个列表。否则当找到匹配的数据，会把不匹配的数据也打印出来。
            singlePriceList.clear();
            singleTotalPriceList.clear();

        }


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


}
