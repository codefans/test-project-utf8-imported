package com.codefans.basicjava.algorithm;

import com.codefans.basicjava.util.JodaDateUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author: ShengzhiCai
 * @date: 2018-06-21 14:19
 * 流量统计-单位之间内被访问的次数
 *
 *
 * 如果单位时间是5秒,那么只需要截取当前时间的秒数,然后对5取整,再乘以5即可:
 * 0/5*5=0
 * 1/5*5=0
 * 2/5*5=0
 * 3/5*5=0
 * 4/5*5=0
 * 5/5*5=5
 * 6/5*5=5
 * 7/5*5=5
 * 8/5*5=5
 * 9/5*5=5
 * 10/5*5=10
 *
 * 如果是时间的话,如下所示:
 * 时间：
 *      2018-06-21 16:00:01
 *      2018-06-21 16:00:03
 * 会落在:
 *      2018-06-21 16:00:00上
 *------------------------------
 * 时间:
 *      2018-06-21 16:00:05
 *      2018-06-21 16:00:08
 * 会落在:
 *      2018-06-21 16:00:05上
 *------------------------------
 * 时间:
 *      2018-06-21 16:00:10
 *      2018-06-21 16:00:14
 * 会落在:
 *      2018-06-21 16:00:10上
 *
 */
public class NetflowStatistics {

    public static void main(String[] args) {
        NetflowStatistics netflowStatistics = new NetflowStatistics();
        netflowStatistics.statistics();
    }

    public void statistics() {

        long time = System.currentTimeMillis();
        System.out.println(time);

        System.out.println(new Date());

        System.out.println(JodaDateUtils.format(new Date(), JodaDateUtils.yyyyMMddHHmmssSSS));

        System.out.println(0/5*5);
        System.out.println(1/5*5);
        System.out.println(2/5*5);
        System.out.println(3/5*5);
        System.out.println(4/5*5);
        System.out.println(5/5*5);
        System.out.println(System.currentTimeMillis());
        System.out.println(JodaDateUtils.formatMillis(System.currentTimeMillis()/5*5));


        this.statisticsPerSecond();
        this.statisticsPerFiveSec();

    }

    /**
     * 统计时间单位-每秒
     */
    public void statisticsPerSecond() {

        String[] accessTimes = new String[]{
            "2018-06-21 16:00:01 001",
            "2018-06-21 16:00:01 181",
            "2018-06-21 16:00:02 334",
            "2018-06-21 16:00:02 233",
            "2018-06-21 16:00:03 656",
            "2018-06-21 16:00:03 988",
            "2018-06-21 16:00:03 999"
        };

        String[] statisticsWindows = new String[]{
            "2018-06-21 16:00:01",
            "2018-06-21 16:00:02",
            "2018-06-21 16:00:03"
        };

        Map<String, Integer> accessStatistics = new HashMap<String, Integer>();

        String accessTimeStr = "";
        String accessSecondTimeStr = "";
        long accessTime = 0L;
        long statisticsTime = 0L;
        for(int i = 0; i < accessTimes.length; i ++) {
            accessTimeStr = accessTimes[i];
            accessSecondTimeStr = accessTimeStr.substring(0, 19);
//            System.out.println(accessSecondTimeStr);

            if(accessStatistics.containsKey(accessSecondTimeStr)) {
                Integer oldVal = accessStatistics.get(accessSecondTimeStr);
                accessStatistics.put(accessSecondTimeStr, oldVal + 1);
            } else {
                accessStatistics.put(accessSecondTimeStr, 1);
            }
        }

        System.out.println("每秒访问量:");
        this.print(accessStatistics);






    }

    /**
     * 统计时间单位-每5秒
     */
    public void statisticsPerFiveSec() {

        String[] accessTimes = new String[]{
                "2018-06-21 16:00:01 001",
                "2018-06-21 16:00:03 181",
                "2018-06-21 16:00:06 232",
                "2018-06-21 16:00:08 334",
                "2018-06-21 16:00:09 233",
                "2018-06-21 16:00:11 656",
                "2018-06-21 16:00:13 987",
                "2018-06-21 16:00:12 988",
                "2018-06-21 16:00:14 999"
        };

        String[] statisticsWindows = new String[]{
                "2018-06-21 16:00:00",
                "2018-06-21 16:00:05",
                "2018-06-21 16:00:10"
        };

        int timeStep = 5;

        Map<String, Integer> accessStatistics = new HashMap<String, Integer>();

        String accessTimeStr = "";
        int accessTime = 0;
        int statisticsTime = 0;
        String statisticsTimeStr = "";
        int resultTime = 0;

        for(int i = 0; i < accessTimes.length; i ++) {
            accessTimeStr = accessTimes[i];
            accessTime = JodaDateUtils.parseSecondOfMinute(accessTimeStr);
//            System.out.println("accessTime:" + accessTime);

            for(int j = 0; j < statisticsWindows.length; j ++) {
                statisticsTimeStr = statisticsWindows[j];
                statisticsTime = JodaDateUtils.parseSecondOfMinute(statisticsTimeStr + " 000");
                resultTime = (accessTime / timeStep) * timeStep;
                if(resultTime == statisticsTime) {
//                    System.out.println("最终时间:" + resultTimeStr + ",访问时间:[" + accessTimeStr + "]落在了:[" + statisticsWindows[j] + "]时间点上.");

                    if(accessStatistics.containsKey(statisticsTimeStr)) {
                        Integer oldVal = accessStatistics.get(statisticsTimeStr);
                        accessStatistics.put(statisticsTimeStr, oldVal + 1);
                    } else {
                        accessStatistics.put(statisticsTimeStr, 1);
                    }



                }




            }

        }

        System.out.println("每5秒访问量:");
        this.print(accessStatistics);

    }

    public void print(Map<String, Integer> dataMap) {
        String key = "";
        Integer val = null;
        Iterator<String> iter = dataMap.keySet().iterator();
        while(iter.hasNext()) {
            key = iter.next();
            val = dataMap.get(key);
            System.out.println("时间节点:[" + key + "], 访问次数:[" + val + "]");
        }
    }




















}
