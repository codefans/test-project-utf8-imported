package com.codefans.basicjava.java6.math;

import java.math.BigDecimal;

/**
 * @author: ShengzhiCai
 * @date: 2018-08-11 11:05
 */
public class BigDecimalUtils {

    public BigDecimal divide(BigDecimal num01, BigDecimal num02) {
        return divide(num01, num02, 2);
    }
    public BigDecimal divide(BigDecimal num01, BigDecimal num02, int scale) {
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

    public BigDecimal setScale(BigDecimal num, int scale) {
        return num.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

}
