package com.codefans.practicetask.math;

import com.codefans.reusablecode.common.CommonUtils;
import org.apache.commons.collections.CollectionUtils;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: caishengzhi
 * @date: 2018-06-12 11:27
 *
 *  1.首先均匀的为单价加上0.01，然后找出绝对值最小的值，并记录此时单价的增加值=n*0.01， 例如：n=431，则增加值为4.31.
    2.然后将数组中的每个单价都加上4.31
    3.对重量进行降序排序，单价数组也相应的调整顺序，以保证两个数组相同下标下的单价和重量是对应的
    4.对单价数组最后四个数据进行穷举遍历(步长为:0.2,，上下0.2，乘以100，就是41个元素)，其他数组元素就按照步骤2中得出的新单价，且不参与穷举
    是否参与穷举，有个布尔型的列表来表示。true参加全排列，false不参与全排列

 *
 */
public class ProductPriceReverseMultiThread extends AbstractUnitPriceReverse {

    BigDecimal totalPrice;
    BigDecimal[] weights;
    BigDecimal[] defaultUnitPrices;

    BigDecimal downPriceStep = new BigDecimal("0.5");
    BigDecimal upPriceStep = new BigDecimal("0.5");
    List<Integer> beginIndexList;
    List<Integer> endIndexList;

    BigDecimal minUnitPriceStep;

    public static void main(String[] args) {
        ProductPriceReverseMultiThread reverseMultiThread = new ProductPriceReverseMultiThread();
        reverseMultiThread.startup();
    }

    public void startup() {

        this.init();

        this.cleanup();

        this.multiTask();

    }

    public void init() {
//        totalPrice = new BigDecimal("88800.00");
//        totalPrice = new BigDecimal("76200.84");
//        totalPrice = new BigDecimal("5150804.08");
//        totalPrice = new BigDecimal("18694.52");
//        totalPrice = new BigDecimal("33924.8");
//        totalPrice = new BigDecimal("38357.65");
//        totalPrice = new BigDecimal("25001.36");
//        totalPrice = new BigDecimal("36595.38");
//        totalPrice = new BigDecimal("17447.10");

        //运费：2700
//        System.out.println("totalPrice:" + totalPrice);

//        weights = new BigDecimal[]{
//                new BigDecimal("46.10"),
//                new BigDecimal("49.80"),
//                new BigDecimal("27.7"), //原27.72
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
//                new BigDecimal("3.5"), //原3.45
//                new BigDecimal("3"),
//                new BigDecimal("13.3"),
//                new BigDecimal("86.50")
//        };

        //No.2
//        weights = new BigDecimal[]{
//            new BigDecimal("50"),
//            new BigDecimal("81.8"),
//            new BigDecimal("35.4"),
//            new BigDecimal("49.9"),
//            new BigDecimal("65"),
//            new BigDecimal("50.1"),
//            new BigDecimal("146.1"),
//            new BigDecimal("129.2"),
//            new BigDecimal("113.4"),
//            new BigDecimal("58"),
//            new BigDecimal("3.6")
//        };

        //No.3
//        weights = new BigDecimal[]{
//                new BigDecimal("49000"),
//                new BigDecimal("40000"),
//                new BigDecimal("46000"),
//                new BigDecimal("18000")
//        };

        //No.4
//        weights = new BigDecimal[]{
//                new BigDecimal("33.1"),
//                new BigDecimal("23.3"),
//                new BigDecimal("18.9"),
//                new BigDecimal("62")
//        };

        //No.5
//        weights = new BigDecimal[]{
////                new BigDecimal("23.1"),
////                new BigDecimal("33"),
////                new BigDecimal("37.7"),
////                new BigDecimal("31.2"),
////                new BigDecimal("31.4"),
////                new BigDecimal("31.4"),
////                new BigDecimal("29.4"),
////                new BigDecimal("17")
////        };

        //No.6
//        weights = new BigDecimal[]{
//                new BigDecimal("39.1"),
//                new BigDecimal("35.7"),
//                new BigDecimal("110.3"),
//                new BigDecimal("35"),
//                new BigDecimal("32"),
//                new BigDecimal("19.9"),
//                new BigDecimal("33.3")
//        };

        //No.7
//        weights = new BigDecimal[]{
//                new BigDecimal("46.7"),
//                new BigDecimal("74.8"),
//                new BigDecimal("60.9"),
//                new BigDecimal("56.8"),
//                new BigDecimal("29.8"),
//                new BigDecimal("20.5")
//        };

//        No.8
//        weights = new BigDecimal[]{
//                new BigDecimal("56.3")
//        };

//        No.9
//        weights = new BigDecimal[]{
//                new BigDecimal("1020"),
//                new BigDecimal("50")
//        };

        //No.10
//        weights = new BigDecimal[]{
//                new BigDecimal("41.6"),
//                new BigDecimal("37.1"),
//                new BigDecimal("36.3"),
//                new BigDecimal("17.7"),
//                new BigDecimal("11.5")
//        };

        //No.1
//        defaultUnitPrices = new BigDecimal[]{
//                new BigDecimal("186.2"),
//                new BigDecimal("186.2"),
//                new BigDecimal("264.6"),
//                new BigDecimal("164.64"),
//                new BigDecimal("127.4"),
//                new BigDecimal("66.64"),
//                new BigDecimal("88.2"),
//                new BigDecimal("196"),
//                new BigDecimal("88.2"),
//                new BigDecimal("88.2"),
//                new BigDecimal("35.28"),
//                new BigDecimal("1225"),
//                new BigDecimal("29.4"),
//                new BigDecimal("63.7"),
//                new BigDecimal("27.44"),
//                new BigDecimal("112.7"),
//                new BigDecimal("25.48"),
//                new BigDecimal("4.9"),
//                new BigDecimal("93.1"),
//                new BigDecimal("31.36"),
//                new BigDecimal("156.8"),
//                new BigDecimal("56.84"),
//                new BigDecimal("22.54"),
//                new BigDecimal("352.8")
//        };

        //No.2
//        defaultUnitPrices = new BigDecimal[] {
//            new BigDecimal("58"),
//            new BigDecimal("290"),
//            new BigDecimal("130"),
//            new BigDecimal("68"),
//            new BigDecimal("36"),
//            new BigDecimal("190"),
//            new BigDecimal("48"),
//            new BigDecimal("75"),
//            new BigDecimal("68"),
//            new BigDecimal("46"),
//            new BigDecimal("250")
//        };

        //No.3
//        defaultUnitPrices = new BigDecimal[] {
//                new BigDecimal("31"),
//                new BigDecimal("31"),
//                new BigDecimal("35"),
//                new BigDecimal("35")
//        };

        //No.4
//        defaultUnitPrices = new BigDecimal[] {
//                new BigDecimal("310"),
//                new BigDecimal("85"),
//                new BigDecimal("195"),
//                new BigDecimal("38")
//        };

        //No.5
//        defaultUnitPrices = new BigDecimal[] {
//                new BigDecimal("186.2"),
//                new BigDecimal("191.1"),
//                new BigDecimal("74.48"),
//                new BigDecimal("68.6"),
//                new BigDecimal("91.14"),
//                new BigDecimal("252.84"),
//                new BigDecimal("161.7"),
//                new BigDecimal("122.5")
//        };

        //No.6
//        defaultUnitPrices = new BigDecimal[] {
//                new BigDecimal("202.86"),
//                new BigDecimal("44.1"),
//                new BigDecimal("68.6"),
//                new BigDecimal("72.52"),
//                new BigDecimal("284.2"),
//                new BigDecimal("181.3"),
//                new BigDecimal("127.4")
//        };

        //No.7
//        defaultUnitPrices = new BigDecimal[] {
//                new BigDecimal("45"),
//                new BigDecimal("74"),
//                new BigDecimal("76"),
//                new BigDecimal("76"),
//                new BigDecimal("68"),
//                new BigDecimal("270")
//        };

//        No.8
//        defaultUnitPrices = new BigDecimal[] {
//                new BigDecimal("210")
//        };


//        No.9
//        defaultUnitPrices = new BigDecimal[] {
//                new BigDecimal("34.5"),
//                new BigDecimal("43")
//        };


        //No.10
//        defaultUnitPrices = new BigDecimal[] {
//                new BigDecimal("227.76"),
//                new BigDecimal("47.95"),
//                new BigDecimal("81.91"),
//                new BigDecimal("99.89"),
//                new BigDecimal("125.94")
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
//            new BigDecimal("1229.37"), //1229.31
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

        String rootDir = CommonUtils.getModuleMainJavaRoot("practice-task");
        String filePath = rootDir + File.separator + "com/codefans/practicetask/math/math.xlsx";
        System.out.println(rootDir);
        System.out.println(filePath);

        MathExcelReader mathExcelReader = new MathExcelReader(filePath);
        mathExcelReader.xssfRead();
        mathExcelReader.print();

        List<String> weightList = mathExcelReader.getWeightList();
        List<String> priceList = mathExcelReader.getPriceList();

        weights = this.getBigDecimalArray(weightList);
        defaultUnitPrices = this.getBigDecimalArray(priceList);
        totalPrice = new BigDecimal(mathExcelReader.getTotalPrice());

    }

    public void cleanup() {

        long startTime = System.currentTimeMillis();

//        进行升序排序并打印数组
//        this.sortAndPrint(defaultUnitPrices, weights, ASC,false);
//        this.sortAndPrint(defaultUnitPrices, weights, DESC,false);
//        System.exit(0);

        BigDecimal total = new BigDecimal("0");
        BigDecimal step = new BigDecimal("3.39");
        BigDecimal multiSteps = new BigDecimal("0");

//        this.printAddedUnitPrice(step, defaultUnitPrices);
//        this.printResult(null, defaultUnitPrices, weights);
//        System.exit(0);

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

        BigDecimal minWeightSum = this.getMinWeightSum();
        BigDecimal maxUnitPrice = this.add(totalPrice, minWeightSum);
        System.out.println("最小总量和minWeightSum=" + minWeightSum);
        System.out.println("最大总价maxUnitPrice=" + maxUnitPrice);

        BigDecimal unitPrice = null;
        BigDecimal weight = null;
        BigDecimal addedWeight = null;
        step = new BigDecimal("0.01");
        BigDecimal minAbs = totalPrice;
        BigDecimal minTotal = new BigDecimal("0");
        String msg = "";

        BigDecimal minMultiSteps = null;

        boolean run = true;
        int loopTimes = 1;
//        whileLoop:
        while(run) {
            multiSteps = this.add(multiSteps, step);
            for (int i = 0; i < defaultUnitPrices.length; i++) {
                unitPrice = defaultUnitPrices[i];
                weight = weights[i];
                total = this.add(total, this.multiply(weight, this.add(unitPrice, multiSteps, 4), 4), 4);
            }

            //                if (total.compareTo(totalPrice) > 0 && total.compareTo(maxUnitPrice) < 0) {
            if (this.subtract(total, totalPrice).abs().compareTo(minAbs) <= 0) {
//                    System.out.println("total: " + total + ", addedUnitPrice:" + this.add(defaultUnitPrices[i], multiSteps) + ", multiSteps:" + multiSteps + ", finalSum:" + this.getTotalPriceAfterAddedUnitPrice(multiSteps, i, defaultUnitPrices, weights) + ", index:" + index + ", i:" + i);
                minAbs = this.subtract(total, totalPrice).abs();
                minTotal = total;
//                msg = "total: " + total + ", addedUnitPrice:" + this.add(defaultUnitPrices[i], multiSteps) + ", multiSteps:" + multiSteps + ", finalSum:" + this.getTotalPriceAfterAddedUnitPrice(multiSteps, i, defaultUnitPrices, weights) + ", loopTimes:" + loopTimes + ", i:" + i;
                msg = "total: " + total + ", multiSteps:" + multiSteps;

                minMultiSteps = multiSteps;

            } else if(total.compareTo(maxUnitPrice) > 0) {
                System.out.println("已经超出最大值, 最大值为:[" + maxUnitPrice + "], 当前total:[" + total + "]" + ", multiSteps:" + multiSteps);
//                System.out.println("total: " + total + ", addedUnitPrice:" + this.add(defaultUnitPrices[i], multiSteps) + ", multiSteps:" + multiSteps + ", finalSum:" + this.getTotalPriceAfterAddedUnitPrice(multiSteps, i, defaultUnitPrices, weights) + ", loopTimes:" + loopTimes + ", i:" + i);
                run = false;
            }

            total = new BigDecimal("0");
            loopTimes++;
        }

        System.out.println("minMultiSteps:" + minMultiSteps);
        System.out.println("minAbs:" + minAbs);
        System.out.println("minTotal:" + minTotal);
        System.out.println("this.subtract(" + minTotal + ", " + totalPrice + ").abs():" + this.subtract(minTotal, totalPrice).abs());
//        System.out.println(msg);

        System.out.println("价格增加[" + minMultiSteps + "]后：");

//        this.printAddedUnitPrice(minMultiSteps, defaultUnitPrices);
//        super.printAddedUnitPrice(minMultiSteps, defaultUnitPrices, weights);
//        super.printResult(minMultiSteps, defaultUnitPrices, weights);
        defaultUnitPrices = super.getNewUnitPrices(minMultiSteps, defaultUnitPrices);
        minUnitPriceStep = minMultiSteps;

        long endTime = System.currentTimeMillis();
        System.out.println("total cost:[" + (endTime - startTime) / 1000 + "s]");



    }

    public void multiTask() {

        //将defaultUnitPrices和weights进行降序排序
        super.sortAndPrint(defaultUnitPrices, weights, DESC, false);
        this.printResult(null, defaultUnitPrices, weights);

        List<Boolean> toSortList = this.getIfSortList("last", 4);
//        List<Boolean> toSortList = this.getIfSortRandomList();

        int coreSize = Runtime.getRuntime().availableProcessors();

        ExecutorService executorService = Executors.newFixedThreadPool(coreSize, new NamedThreadFactory());
        ReverseTask task = new ReverseTask(totalPrice, Arrays.asList(defaultUnitPrices), Arrays.asList(weights));
        task.setDownPriceStep(downPriceStep);
        task.setUpPriceStep(upPriceStep);
        task.setIfSortList(toSortList);

//        executorService.execute(task);
        executorService.submit(task);


    }



    public List<Boolean> getIfSortList(String direction, int count) {
        List<Boolean> ifSortList = new ArrayList<Boolean>(weights.length);
        if(direction.equals("first")) {
            for(int i = 0; i < weights.length; i ++) {
                if(count > 0) {
                    ifSortList.add(new Boolean(true));
                    --count;
                } else {
                    ifSortList.add(new Boolean(false));
                }
            }
        } else if(direction.equals("last")) {
            for(int i = weights.length - 1; i >= 0; i --) {
                if(count > 0) {
                    ifSortList.add(new Boolean(true));
                    --count;
                } else {
                    ifSortList.add(0, new Boolean(false));
                }
            }
        } else {
            for(int i = 0; i < weights.length; i ++) {
                ifSortList.add(new Boolean(true));
            }
        }
        return ifSortList;
    }

    public List<Boolean> getIfSortRandomList() {
        List<Boolean> ifSortList = new ArrayList<Boolean>(weights.length);
        ifSortList.add(new Boolean(true));
        ifSortList.add(new Boolean(true));
        ifSortList.add(new Boolean(false));
        ifSortList.add(new Boolean(false));
        ifSortList.add(new Boolean(false));
        ifSortList.add(new Boolean(false));
        ifSortList.add(new Boolean(false));
        ifSortList.add(new Boolean(false));
        ifSortList.add(new Boolean(false));
        ifSortList.add(new Boolean(true));
        ifSortList.add(new Boolean(true));
        return ifSortList;
    }

    public BigDecimal getMinWeightSum() {
        BigDecimal weightSum = new BigDecimal(0);
        for(int i = 0; i < weights.length; i ++) {
            weightSum = add(weightSum, this.multiply(weights[i], new BigDecimal(0.01)));
        }
        return weightSum;
    }

    public BigDecimal[] getBigDecimalArray(List<String> strArr) {
        BigDecimal[] bigDecimals = new BigDecimal[strArr.size()];
        for(int i = 0; i < strArr.size(); i ++) {
            bigDecimals[i] = new BigDecimal(strArr.get(i));
        }
        return bigDecimals;
    }

    class NamedThreadFactory implements ThreadFactory {

        private String threadNamePrefix = "ProductPriceReverseThread_";

        AtomicInteger threadIndex = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, threadNamePrefix + threadIndex.getAndIncrement());
        }
    }

}
