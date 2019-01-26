package com.codefans.opensource.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @author: codefans
 * @date: 2018-05-06 16:41
 */
public class JedisClient extends JedisClientBase {

    Jedis jedis;

    @Before
    public void before() {
        host = "127.0.0.1";
        port = 6379;
        password = "123";
        connectionTimeout = 10000;
        soTimeout = 10000;

        jedis = new Jedis(host, port, connectionTimeout, soTimeout);
        jedis.auth(password);
    }

    @Test
    public void setGetTest() {
        String key = "key001";
        String value = "value002";

        jedis.set(key, value);

        String val = jedis.get(key);
        System.out.println("value:[" + value + "], val from redis:[" + val + "]");

    }



}
