package com.codefans.opensource.redis;

import redis.clients.jedis.Jedis;

/**
 * @author: codefans
 * @date: 2018-06-22 09:45
 * redis单机模式
 */
public class JedisSingleClient extends JedisClientBase {

    Jedis jedis;

    public JedisSingleClient(String host, int port) {
        this.host = host;
        this.port = port;
        jedis = new Jedis(host, port);
    }

    public void setAuth(String password) {
        jedis.auth(password);
    }

    public String set(String key, String value) {
        return jedis.set(key, value);
    }





}
