package com.codefans.interview.algorithm.leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ShengzhiCai
 * @Date: 2018-12-10 7:07
 *
 * 这一题主要是记错containsKey函数了，记成了contains，一遍就Accepted
 *
 */

public class No3LongestSubString {

    /**
     * leetcode上显示花的时间为188ms, 跑了几百个测试用例的时间，时间有点长
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int maxLen = 0;
        int tmpLen = 0;
        Map<String, Object> dataMap = null;
        for(int i = 0; i < chars.length; i ++) {
            dataMap = new HashMap<String, Object>();
            for(int j = i; j < chars.length; j ++) {
                char c = chars[j];
                if(!dataMap.containsKey(""+c)) {
                    dataMap.put(""+c, c);
                } else {
                    break;
                }
            }
            tmpLen = dataMap.size();
            if(tmpLen > maxLen) {
                maxLen = tmpLen;
            }
        }

        return maxLen;

    }

//    public String stringToString(String input) {
//        return JsonArray.readFrom("[" + input + "]").get(0).asString();
//    }

    public void runCode() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {

//            String s = stringToString(line);

            long beginTime = System.currentTimeMillis();
            int ret = lengthOfLongestSubstring(line);
            long endTime = System.currentTimeMillis();
            System.out.println("cost:[" + (endTime - beginTime) + "]ms");

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }

    public static void main(String[] args) throws IOException {
        No3LongestSubString no3LongestSubString = new No3LongestSubString();
        no3LongestSubString.runCode();
    }



}
