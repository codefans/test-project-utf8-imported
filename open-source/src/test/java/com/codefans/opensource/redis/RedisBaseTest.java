package com.codefans.opensource.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author codefans
 * @date 2018/1/22 17:29
 */
public class RedisBaseTest {

    protected final String REDIS_RESULT_OK = "OK";

    protected String host = "";
    protected int port = 0;
    protected String password;
    protected int connectionTimeout;
    protected int soTimeout;

    int maxAttempts;
    GenericObjectPoolConfig poolConfig;
    //最小空闲连接数
    int minIdle = 0;
    //最大空闲连接数
    int maxIdle = 8;
    //最大连接数
    int maxTotal = 8;

    protected Jedis jedis;
    JedisCluster jedisCluster;

    void print(List<String> list) {
        for(String str : list) {
            System.out.println(str);
        }
    }

    void print(Set<String> keys) {
        Iterator<String> it=keys.iterator() ;
        while(it.hasNext()){
            String key = it.next();
            System.out.println(key);
        }
    }
}
