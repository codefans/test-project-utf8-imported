package com.codefans.reusablecode.distributed;

import java.util.UUID;

/**
 * @author: codefans
 * @date: 2019-01-02 16:10:43
 *
 * 动态扩容路由算法要求：
 *    1.扩容后不影响原来数据的读取
 *    2.尽可能均匀的分布到各个数据库中
 *
 */
public class DynamicExpansion {

    public static void main(String[] args) {
        DynamicExpansion de = new DynamicExpansion();
        de.expansion();
    }

    public void expansion() {

        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replaceAll("-", "");

        System.out.println(uuid.hashCode());

        int[] userIds = new int[]{
            110221, 110220, 110222, 110223, 110224,
            110225, 110226, 110227, 110228, 110229
        };

//        userId:110221,index:1
//        userId:110220,index:0
//        userId:110222,index:2
//        userId:110223,index:3
//        userId:110224,index:0
//        userId:110225,index:1
//        userId:110226,index:2
//        userId:110227,index:3
//        userId:110228,index:0
//        userId:110229,index:1
        int[] machines = new int[8];

        int machineCount = machines.length;

        int userId = 0;
        int index = 0;
        int itemCount = 0;
        for(int i = 0; i < userIds.length; i ++) {
            userId = userIds[i];
            index = userId%machineCount;
            System.out.println("userId:" + userId + ",index:" + index);
            itemCount = machines[index];
            machines[index] = itemCount + 1;
        }

        this.print(machines);






    }

    public void print(int[] arr) {
        for(int i = 0; i < arr.length; i ++) {
            if(i == 0) {
                System.out.print(arr[i]);
            } else {
                System.out.print("," + arr[i]);
            }
        }
        System.out.println();
    }


}
