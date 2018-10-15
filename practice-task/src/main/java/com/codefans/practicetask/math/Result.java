package com.codefans.practicetask.math;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author: ShengzhiCai
 * @date: 2018-09-22 00:47
 */
public class Result {

    BigDecimal totalPrice;
    List<BigDecimal> weightList;
    List<BigDecimal> unitPriceList;
    Map<String, BigDecimal> weightPriceMap;

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<BigDecimal> getWeightList() {
        return weightList;
    }

    public void setWeightList(List<BigDecimal> weightList) {
        this.weightList = weightList;
    }

    public List<BigDecimal> getUnitPriceList() {
        return unitPriceList;
    }

    public void setUnitPriceList(List<BigDecimal> unitPriceList) {
        this.unitPriceList = unitPriceList;
    }

    public Map<String, BigDecimal> getWeightPriceMap() {
        return weightPriceMap;
    }

    public void setWeightPriceMap(Map<String, BigDecimal> weightPriceMap) {
        this.weightPriceMap = weightPriceMap;
    }

}
