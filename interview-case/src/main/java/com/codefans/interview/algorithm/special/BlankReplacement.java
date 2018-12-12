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
//        String resultStr = blankReplacement.replace(originStr);
        String resultStr = blankReplacement.replaceInOriginStr(originStr);
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

    /**
     *
     * @param originStr
     * @return
     */
    public String replaceInOriginStr(String originStr) {
        char[] originChars = originStr.toCharArray();
        int blankCount = 0;
        for(int i = 0; i < originChars.length; i ++) {
            if(originChars[i] == ' ') {
                blankCount ++;
            }
        }
        //将一个空格字符替换为%20, 相当于多了两个字符
        char[] destChars = new char[originStr.length() + 2 * blankCount];
        for(int i = 0; i < originChars.length; i ++) {
            destChars[i] = originChars[i];
        }

        int index1 = originStr.length() -1;
        int index2 = destChars.length - 1;
        char tmp = ' ';
        while(index1 > 0 && index2 > 0 && index1 != index2) {
            if(destChars[index1] != ' ') {
                tmp = destChars[index2];
                destChars[index2] = destChars[index1];
                destChars[index1] = tmp;
                index1--;
                index2--;
            } else {
                index1--;
                destChars[index2--] = '0';
                destChars[index2--] = '2';
                destChars[index2--] = '%';
            }
        }

        return new String(destChars);
    }

}
