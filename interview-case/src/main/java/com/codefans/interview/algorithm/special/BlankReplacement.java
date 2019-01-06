package com.codefans.interview.algorithm.special;

/**
 * @Author: codefans
 * @Date: 2018-12-11 7:12
 * 1.从前往后替换,时间复杂度O(n~2)
 *      (1)遇到空格字符,替换为%20,需要把1个字符替换为3个字符,就必须把空格后面的所有字符都后移两个字节,否则两个字符就被覆盖了。
 * 2.从后往前替换,时间复杂度O(n)
 *      (1)先遍历一次字符串,计算空格数量,计算替换之后字符串的长度
 *      (2)从字符串后面开始复制和替换,准备两个下标P1和P2,P1指向原始字符串的末尾,P2指向替换之后的字符串的末尾。
 *      (3)向前移动P1,逐个把它指向的字符复制到P2指向的位置,直到碰到第一个空格为止.
 *      (4)碰到第一个空格之后,把P1向前移动1格,在P2之前插入字符串"%20".由于"%20"长度为3,同时要把P2向前移动3格.
 *      (5)重复步骤(2)、步骤(3),直到P1和P2下标相等,表明所有空格都已经替换完毕.
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
