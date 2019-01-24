package com.codefans.reusablecode.consistent;

import redis.clients.util.Hashing;
import redis.clients.util.MurmurHash;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author: codefans
 * @date: 2019-01-24 18:12:23
 */
public class ConsistentHashUseTreeMap {

    private Hashing hashing = new MurmurHash();

    private TreeMap<Long, String> nodes = new TreeMap<Long, String>();

    public static void main(String[] args) {
        ConsistentHashUseTreeMap consistentHashUseTreeMap = new ConsistentHashUseTreeMap();
        consistentHashUseTreeMap.consistentHash();
    }

    public void consistentHash() {

        this.initConsistentHashCycle();

        this.routing();

    }

    public void initConsistentHashCycle() {

        List<String> nameList = new ArrayList<String>();
        nameList.add("nodeOne");
        nameList.add("nodeTwo");
        nameList.add("nodeThree");
        nameList.add("nodeFourth");

        Long weight = 1L;

        for (int i = 0; i != nameList.size(); ++i) {
            String name = nameList.get(i);
            for (int n = 0; n < 160 * weight ; n++) {
                nodes.put(hashing.hash(name + "*" + weight + n), nameList.get(i));
            }
        }
    }

    public void routing() {

        String[] keys = new String[]{
            "zhangsan", "lisi", "wangwu", "zhaoliu", "qianqi", "luoba", "chenjiu", "sunshi", "yaner", "caiyi"
        };
        String key = "";
        String nodeName = "";
        for(int i = 0; i < keys.length; i ++) {
            key = keys[i];
            nodeName = this.routeNode(key);
            System.out.println("key:[" + key + "], local in nodeName:[" + nodeName + "]");
        }

    }


    public String routeNode(String key) {
        String nodeName = "";

        SortedMap<Long, String> tail = nodes.tailMap(hashing.hash(key));
        if (tail.isEmpty()) {
            nodeName = nodes.get(nodes.firstKey());
        }
        nodeName = tail.get(tail.firstKey());

        return nodeName;
    }

    public void consistentHashTest() {

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
