package com.codefans.interview.algorithm.special;

/**
 * @Author: ShengzhiCai
 * @Date: 2018-12-11 7:12
 *
 */

public class BlankReplacement {

    public static void main(String[] args) {
        BlankReplacement blankReplacement = new BlankReplacement();
        String originStr = "We are happy.";
        String resultStr = blankReplacement.replace(originStr);
        System.out.println("resultStr:");
        System.out.println(resultStr);
    }

    public String replace(String originStr) {
        StringBuilder sb = new StringBuilder(originStr.length() * 2);
        char[] chars = originStr.toCharArray();
        for(int i = 0; i < chars.length; i ++) {
            if(chars[i] != ' ') {
                sb.append(chars[i]);
            } else {
                sb.append("%20");
            }
        }
        return sb.toString();
    }

}
