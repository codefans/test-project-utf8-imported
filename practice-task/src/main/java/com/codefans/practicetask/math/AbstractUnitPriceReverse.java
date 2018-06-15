package com.codefans.practicetask.math;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: caishengzhi
 * @date: 2018-06-15 10:15
 */
public abstract class AbstractUnitPriceReverse {

    protected static final int ASC = 1;
    protected static final int DESC = -1;

    /**
     * 将defaultUnitPrices和weights进行排序
     * @param defaultUnitPrices
     * @param weights
     * @param sortType
     * @param print
     */
    public void sortAndPrint(BigDecimal[] defaultUnitPrices, BigDecimal[] weights, int sortType, boolean print) {
        if(print) {
            System.out.println("before sorted:");
            System.out.println("weight array:");
            this.print(weights);
            System.out.println("price array:");
            this.print(defaultUnitPrices);
        }
        Map<String, BigDecimal[]> resMap = this.getSortByWeight(defaultUnitPrices, weights, sortType);
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

    public Map<String, BigDecimal[]> getSortByWeight(BigDecimal[] unitPriceArr, BigDecimal[] weightArr, int sortType) {
        Map<String, BigDecimal[]> resMap = new HashMap<String, BigDecimal[]>();

        for(int i = 0; i < weightArr.length - 1; i ++) {
            for(int j = i + 1; j < weightArr.length; j ++) {
                if(weightArr[i].compareTo(weightArr[j]) == sortType) {
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

    public BigDecimal[] getNewUnitPrices(BigDecimal unitPriceStep, BigDecimal[] defaultUnitPrices) {
        BigDecimal unitPrice = null;
        BigDecimal[] newUnitPrices = new BigDecimal[defaultUnitPrices.length];
        for(int i = 0; i < defaultUnitPrices.length; i ++) {
            if(unitPriceStep != null) {
                unitPrice = this.add(defaultUnitPrices[i], unitPriceStep);
            } else {
                unitPrice = defaultUnitPrices[i];
            }
            newUnitPrices[i] = unitPrice;
        }
        return newUnitPrices;
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

    public void printAddedUnitPrice(BigDecimal unitPriceStep, BigDecimal[] defaultUnitPrices, BigDecimal[] weights) {
        BigDecimal unitPrice = null;
        for(int i = 0; i < defaultUnitPrices.length; i ++) {
            unitPrice = this.add(defaultUnitPrices[i], unitPriceStep);
            System.out.println(unitPrice + ", " + weights[i]);

        }
    }

    public int generateBeginIndex(BigDecimal defaultPrice, BigDecimal downPriceStep, BigDecimal priceIndexRadio) {
        int beginIndex = 0;
        beginIndex = this.multiply(subtract(defaultPrice, downPriceStep),priceIndexRadio).intValue();
        return beginIndex;
    }

    public int generateEndIndex(BigDecimal defaultPrice, BigDecimal upPriceStep, BigDecimal priceIndexRadio) {
        int beginIndex = 0;
        beginIndex = defaultPrice.add(upPriceStep).multiply(priceIndexRadio).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
        return beginIndex;
    }

    public BigDecimal devide(BigDecimal num01, BigDecimal num02) {
        return devide(num01, num02, 2);
    }
    public BigDecimal devide(BigDecimal num01, BigDecimal num02, int scale) {
        return num01.divide(num02, scale, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal multiply(BigDecimal num01, BigDecimal num02) {
        return multiply(num01, num02, 2);
    }
    public BigDecimal multiply(BigDecimal num01, BigDecimal num02, int scale) {
        return num01.multiply(num02).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal add(BigDecimal num01, BigDecimal num02) {
        return add(num01, num02, 2);
    }
    public BigDecimal add(BigDecimal num01, BigDecimal num02, int scale) {
        return num01.add(num02).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal subtract(BigDecimal num01, BigDecimal num02) {
        return subtract(num01, num02, 2);
    }
    public BigDecimal subtract(BigDecimal num01, BigDecimal num02, int scale) {
        return num01.subtract(num02).setScale(scale, BigDecimal.ROUND_HALF_UP);
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

    public void print(BigDecimal[] arr, boolean forceReturn) {
        for(int i = 0; i < arr.length; i ++) {
            if(forceReturn) {
                System.out.println(arr[i]);
            } else {
                if (i != 0) {
                    System.out.print(", ");
                }
                System.out.print(arr[i]);
            }
        }
        System.out.println();
    }

}
