package com.codefans.practicetask.math;

import io.jsonwebtoken.lang.Collections;
import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: caishengzhi
 * @date: 2018-06-12 11:28
 *
 *
 *
 *
 */
public class ReverseTask extends AbstractUnitPriceReverse implements Runnable {

    BigDecimal totalPrice;
    List<BigDecimal> weightList;
    BigDecimal totalWeight = new BigDecimal(0);

    List<BigDecimal> defaultUnitPriceList;

    BigDecimal downPriceStep;
    BigDecimal upPriceStep;
    List<Integer> beginIndexList;
    List<Integer> endIndexList;
    List<Boolean> ifSortList;


    /**
     * 在默认单价基础上向上或向下浮动的步长
     */
    private BigDecimal defaultUnitPriceStep = new BigDecimal("5");

    /**
     * 价格与下标转换的系数，由于价格是保留两位小数，所以乘以100后，就是整数，就可以作为数组下标进行遍历
     * 由价格保留几位小数决定
     */
    private BigDecimal priceIndexRadio = new BigDecimal("100");

    private BigDecimal Zero = new BigDecimal("0");


    public ReverseTask(BigDecimal totalPrice, List<BigDecimal> defaultUnitPriceList, List<BigDecimal> weightList) {
        this.totalPrice = totalPrice;
        this.defaultUnitPriceList = defaultUnitPriceList;
        this.weightList = weightList;
    }

    @Override
    public void run() {
        try {
            long beginTime = System.currentTimeMillis();
            System.out.println("currentThreadName:" + Thread.currentThread().getName());

            this.calculate(totalPrice, weightList, defaultUnitPriceList);
            long endTime = System.currentTimeMillis();
            System.out.println("calculate cost:[" + (endTime - beginTime) / 1000 + "s]");


        } catch (Throwable e) {
            e.printStackTrace();
        }
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
        List<Integer> currentIndexList = new ArrayList<Integer>();

        int beginIndex = 0;
        int endIndex = 0;
        int length = 0;
        BigDecimal defaultUnitPrice = null;
        List<List<Integer>> unitPriceList = new ArrayList<List<Integer>>();
        List<Integer> itemList = null;
        for(int i = 0; i < defaultUnitPriceList.size(); i ++) {
            defaultUnitPrice = defaultUnitPriceList.get(i);
            //将默认单价上下浮动一定步长(unitPriceStep)，作为穷举的开始和结束下标
            if(CollectionUtils.isNotEmpty(this.beginIndexList)) {
                beginIndex = this.beginIndexList.get(i);
            } else {
                beginIndex = getBeginIndex(defaultUnitPrice, i);
            }
            if(CollectionUtils.isNotEmpty(this.endIndexList)) {
                endIndex = this.endIndexList.get(i);
            } else {
                endIndex = getEndIndex(defaultUnitPrice, i);
            }
            length = endIndex - beginIndex + 1;

            itemList = new ArrayList<Integer>();
            for(int j = beginIndex; j <= endIndex; j ++) {
                itemList.add(j);
            }

            beginIndexList.add(beginIndex);
            endIndexList.add(endIndex);
            lengthList.add(length);
            currentIndexList.add(new Integer("0"));
            unitPriceList.add(itemList);
        }

        if(beginIndexList.size() != endIndexList.size()) {
            throw new RuntimeException("beginIndexList和endIndexList大小不一致!");
        }



        boolean process = true;
        BigDecimal weight = null;
        int index = 1;
        int commonSize = unitPriceList.size();

        int weightLen = weightList.size();
        int priceLen = defaultUnitPriceList.size();
        if(commonSize == weightLen && commonSize == priceLen) {
            System.out.println("commonSize:" + commonSize + ", weightLen:" + weightLen + ", priceLen:" + priceLen);
        } else {
            throw new RuntimeException("commonSize、weightLen和priceLen三者长度不一致!");
        }

        //查找第一个和最后一个参与排序的下标
        //第一个就是下标从0开始,往后遍历第一个遇到true的下标就是起始下标
        //最后一个为true的，就是最后的下标
        int firstSortIndex = 0;
        int lastSortIndex = 0;
        boolean findFirst = false;
        if(CollectionUtils.isNotEmpty(ifSortList)) {
            for(int i = 0; i < ifSortList.size(); i ++) {
                if(ifSortList.get(i)) {
                    if(!findFirst) {
                        firstSortIndex = i;
                        findFirst = true;
                    }
                    lastSortIndex = i;
                }
            }
        } else {
            firstSortIndex = 0;
            lastSortIndex = commonSize - 1;
        }


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

        BigDecimal minAbs = totalPrice;
        String lastMsg = "";
        int successCount = 0;
        boolean breakFor = false;

        while(process) {

            //for test
            boolean foundFirst = false;
            boolean foundSecond = false;
            boolean foundThird = false;
            boolean foundForth = false;

            total = new BigDecimal(0);
            for(int i = 0; i < commonSize; i ++) {
                if(i == firstSortIndex) {
                    firstLen = lengthList.get(i);
                    firstIndex = currentIndexList.get(i);
                    currentIndex = firstIndex;
                    //如果第一行的下标等于第一行的长度减1,那么说明遍历结束了,退出循环
                    if(currentIndex == firstLen - 1) {
                        process = false;
                    }
                } else if (i > firstSortIndex) {
                    currentLen = lengthList.get(i);
                    currentIndex = currentIndexList.get(i);

                    //从第二行开始, 如果当前行的下标走到最大值, 则：
                    //1.将上一行的下标加1;
                    //2.将本行的下标置为0;
                    if(currentIndex == currentLen - 1) {
                        //由于是否参与排序，是由一个boolean型列表决定的，且有可能不连续，所以要找上一个为true的元素
                        int previousIndex = this.getPreviousSortIndex(currentIndexList, i);
                        int lastIndex = currentIndexList.get(previousIndex);
                        currentIndexList.set(previousIndex, lastIndex + 1);
                        currentIndexList.set(i, 0);
                        breakFor = true;
//                        break;
                    }
                } else {
                    currentLen = lengthList.get(i);
                    currentIndex = currentIndexList.get(i);
                }

                itemList = unitPriceList.get(i);
                item = itemList.get(currentIndex);

                //由于item是价格乘以priceIndexRadio后的作为索引下标使用的整数，所以这里要再除以priceIndexRadio
                singlePrice = this.devide(new BigDecimal(item), priceIndexRadio);
                //单价乘以重量
                singleTotalPrice = this.multiply(singlePrice, weightList.get(i));
                singlePriceList.add(singlePrice);
                singleTotalPriceList.add(singleTotalPrice);

                // for test
                if(item == 2968) {
                    foundFirst = true;
                }
                if(item == 16111) {
                    foundSecond = true;
                }
                if(item == 6115) {
                    foundThird = true;
                }
                if(item == 122935) {
                    foundForth = true;
                }
                if(foundFirst && foundSecond && foundThird && foundForth) {
                    System.out.println("singlePrice:" + singlePrice);
                }

                //这种使用BigDecimal.add的方法，会有精度问题
//                total = total.add(singleTotalPrice);
                total = this.add(total, singleTotalPrice);

                //必须在判断i==lastSortIndex之前
                if(breakFor) {
                    breakFor = false;
                    break;
                }

                //从最后一行的下标开始加
                if(i == lastSortIndex) {
                    currentIndexList.set(i, currentIndex + 1);
                }

            }

            BigDecimal subAbs = this.subtract(total, totalPrice).abs();
            if(subAbs.compareTo(new BigDecimal(0)) == 0) {
//                System.out.println("subAbs:" + subAbs);
                successCount++;
                if(commonSize != singlePriceList.size()) {
                    System.out.println("第[" + successCount + "]组数据个数和原数据个数不符!");
                }
            }

            if(subAbs.compareTo(minAbs) <= 0) {
                minAbs = subAbs;

                StringBuilder sb = new StringBuilder();
                sb.append("================result:[" + (successCount) + "], priceList.size():[" + singlePriceList.size() + "]=================").append("\n");
                for(int j = 0; j < singlePriceList.size(); j ++) {
                    singlePrice = singlePriceList.get(j);
                    weight = weightList.get(j);
                    singleTotalPrice = singleTotalPriceList.get(j);
                    sb.append(singlePrice + " * " + weight + " = " + singleTotalPrice).append("\n");
                }

                for(int k = 0; k < singleTotalPriceList.size(); k ++) {
                    singleTotalPrice = singleTotalPriceList.get(k);
                    if(k == singleTotalPriceList.size() - 1) {
                        sb.append(singleTotalPrice + " = " + total).append("\n");
                    } else {
                        sb.append(singleTotalPrice + " + ");
                    }
                }
                lastMsg = sb.toString();

                if(subAbs.compareTo(new BigDecimal(0)) == 0) {
                    System.out.println(lastMsg);
                }

                index++;
            }


//            if(totalPrice.compareTo(total) == 0) {
//
//                System.out.println("================result:[" + (index) + "]=================");
//                for(int j = 0; j < singlePriceList.size(); j ++) {
//                    singlePrice = singlePriceList.get(j);
//                    weight = weightList.get(j);
//                    singleTotalPrice = singleTotalPriceList.get(j);
//                    System.out.println(singlePrice + " * " + weight + " = " + singleTotalPrice);
//                }
//
//                for(int k = 0; k < singleTotalPriceList.size(); k ++) {
//                    singleTotalPrice = singleTotalPriceList.get(k);
//                    if(k == singleTotalPriceList.size() - 1) {
//                        System.out.println(singleTotalPrice + " = " + total);
//                    } else {
//                        System.out.print(singleTotalPrice + " + ");
//                    }
//                }
//                index++;
//
//            } else {
//                BigDecimal maxPrice = this.getMaxTotalPrice();
//                if(total.compareTo(maxPrice) == 1) {
//                    break;
//                }
//            }

            //不管有没有找到匹配的，都需要清空这两个列表。否则当找到匹配的数据，会把不匹配的数据也打印出来。
            singlePriceList.clear();
            singleTotalPriceList.clear();

        }

        System.out.println("符合条件的数据总共有:[" + successCount + "]条");
        System.out.println("minAbs:" + minAbs);
        System.out.println("最后一条符合条件的数据详情如下:");
        System.out.println(lastMsg);
        System.out.println("task finish!!!!");

    }

    /**
     * 从下往上找，如果参与排序，且第一个索引小于当前索引的，即为上一个参与排序的索引
     * @param currentIndexList
     * @param currentIndex
     * @return
     */
    public int getPreviousSortIndex(List<Integer> currentIndexList, int currentIndex) {
        int previousSortIndex = 0;
        if(CollectionUtils.isNotEmpty(ifSortList)) {
            for(int i = currentIndexList.size() - 1; i > 0; i --) {
                if(ifSortList.get(i) && i < currentIndex) {
                    previousSortIndex = i;
                    break;
                }
            }
        } else {
            previousSortIndex = currentIndex - 1;
        }
        return previousSortIndex;
    }

    public BigDecimal getMaxTotalPrice() {
        if(weightList == null) {
            throw new IllegalArgumentException("weightList can't be null.");
        }
        BigDecimal maxTotalPrice = new BigDecimal(0);
        for(int i = 0; i < weightList.size(); i ++) {
            totalWeight = super.add(totalWeight, weightList.get(i));
        }
        totalWeight = super.devide(totalWeight, new BigDecimal(100));
        maxTotalPrice = super.add(totalPrice, totalWeight);
        return maxTotalPrice;
    }

    /**
     * 如果index未知的价格不参与排序，则index就是根据unitPrice得来的
     * @param unitPrice
     * @param index
     * @return
     */
    public int getBeginIndex(BigDecimal unitPrice, int index) {
        if(downPriceStep != null) {
            if(CollectionUtils.isNotEmpty(ifSortList)) {
                boolean ifSort = ifSortList.get(index);
                if(ifSort) {
                    //如果参与排序，则下标就是单价-向下步长
                    return generateBeginIndex(unitPrice, downPriceStep, priceIndexRadio);
                } else {
                    //如果不参与排序, 则下标就是单价本身
                    return generateBeginIndex(unitPrice, Zero, priceIndexRadio);
                }
            } else {
                return generateBeginIndex(unitPrice, downPriceStep, priceIndexRadio);
            }

        } else {
            //如果没有设置向上步长,则下标为：单价+默认步长
            return generateBeginIndex(unitPrice, defaultUnitPriceStep, priceIndexRadio);
        }
    }

    public int getEndIndex(BigDecimal unitPrice, int index) {
        if(upPriceStep != null) {
            if(CollectionUtils.isNotEmpty(ifSortList)) {
                boolean ifSort = ifSortList.get(index);
                if(ifSort) {
                    //如果参与排序，则下标就是单价+向下步长
                    return generateEndIndex(unitPrice, upPriceStep, priceIndexRadio);
                } else {
                    //如果不参与排序, 则下标就是单价本身
                    return generateBeginIndex(unitPrice, Zero, priceIndexRadio);
                }
            } else {
                return generateEndIndex(unitPrice, upPriceStep, priceIndexRadio);
            }
        } else {
            //如果没有设置向上步长,则下标为：单价+默认步长
            return generateEndIndex(unitPrice, defaultUnitPriceStep, priceIndexRadio);
        }
    }

    public BigDecimal getDownPriceStep() {
        return downPriceStep;
    }

    public void setDownPriceStep(BigDecimal downPriceStep) {
        this.downPriceStep = downPriceStep;
    }

    public BigDecimal getUpPriceStep() {
        return upPriceStep;
    }

    public void setUpPriceStep(BigDecimal upPriceStep) {
        this.upPriceStep = upPriceStep;
    }

    public List<Integer> getBeginIndexList() {
        return beginIndexList;
    }

    public void setBeginIndexList(List<Integer> beginIndexList) {
        this.beginIndexList = beginIndexList;
    }

    public List<Integer> getEndIndexList() {
        return endIndexList;
    }

    public void setEndIndexList(List<Integer> endIndexList) {
        this.endIndexList = endIndexList;
    }

    public List<Boolean> getIfSortList() {
        return ifSortList;
    }

    public void setIfSortList(List<Boolean> ifSortList) {
        this.ifSortList = ifSortList;
    }
}
