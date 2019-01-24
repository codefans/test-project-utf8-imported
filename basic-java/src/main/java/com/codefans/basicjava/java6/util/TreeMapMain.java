package com.codefans.basicjava.java6.util;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author: codefans
 * @date: 2019-01-23 16:39:26
 */
public class TreeMapMain {

    public static void main(String[] args) {
        TreeMapMain treeMapMain = new TreeMapMain();
        treeMapMain.test();
    }


//    private void initialize(List<S> shards) {
//        nodes = new TreeMap<Long, S>();
//
//        for (int i = 0; i != shards.size(); ++i) {
//            final S shardInfo = shards.get(i);
//            if (shardInfo.getName() == null) for (int n = 0; n < 160 * shardInfo.getWeight(); n++) {
//                nodes.put(this.algo.hash("SHARD-" + i + "-NODE-" + n), shardInfo);
//            }
//            else for (int n = 0; n < 160 * shardInfo.getWeight(); n++) {
//                nodes.put(this.algo.hash(shardInfo.getName() + "*" + shardInfo.getWeight() + n), shardInfo);
//            }
//            resources.put(shardInfo, shardInfo.createResource());
//        }
//    }
//
//    public R getShard(byte[] key) {
//        return resources.get(getShardInfo(key));
//    }
//
//    public R getShard(String key) {
//        return resources.get(getShardInfo(key));
//    }
//
//    public S getShardInfo(byte[] key) {
//        SortedMap<Long, S> tail = nodes.tailMap(algo.hash(key));
//        if (tail.isEmpty()) {
//            return nodes.get(nodes.firstKey());
//        }
//        return tail.get(tail.firstKey());
//    }
//
//

    public void test() {

        TreeMap<Long, String> data = new TreeMap<Long, String>();
        data.put(100L, "hello");
        data.put(103L, "world");
        data.put(106L, "zhangsan");
        data.put(109L, "lisi");

        //顺时针, 通过101这个key, 得到103的key
        SortedMap<Long, String> subMap = data.tailMap(101L);
        //逆时针, 通过101这个key, 得到100的key
//        SortedMap<Long, String> subMap = data.headMap(101L);
        Long key = subMap.firstKey();
        System.out.println(key + ", " + subMap.get(key));


    }

}
