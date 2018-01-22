package com.codefans.opensource.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * @author caishengzhi
 * @date 2018/1/22 10:53
 * 参考资料：
 *
 * Jedis官方测试代码
 * https://github.com/xetorthio/jedis.git
 * jedis/src/test/java/redis.clients.jedis.tests包
 *
 * http://www.runoob.com/redis/redis-tutorial.html
 *
 */
public class RedisBasicTest extends RedisBaseTest {


    @Before
    public void before() {
        host = "10.60.58.165";
        port = 7000;
        password = "Lsjrxd";
        connectionTimeout = 10000;
        soTimeout = 10000;

        jedis = new Jedis(host, port, connectionTimeout, soTimeout);
        jedis.auth(password);


        maxAttempts = 3;
        poolConfig = new JedisPoolConfig();
        //最小空闲连接数
        poolConfig.setMinIdle(minIdle);
        //最大空闲连接数
        poolConfig.setMaxIdle(maxIdle);
        //最大连接数
        poolConfig.setMaxTotal(maxTotal);


        HostAndPort hostAndPort = new HostAndPort(host, port);
        Set<HostAndPort> hostAndPortSet = new HashSet<HostAndPort>();
        hostAndPortSet.add(hostAndPort);

        jedisCluster = new JedisCluster(hostAndPortSet, connectionTimeout, soTimeout, maxAttempts, password, poolConfig);

    }

    @Test
    public void listAllKeys() {
        // 获取数据并输出
        Set<String> keys = jedis.keys("*");
        this.print(keys);
    }

    @Test
    public void redisDataSringTest() {

        jedis.set("hello", "world");

        String val = jedis.get("hello");
        assertEquals("world", val);

//        SetParams setParams = new SetParams();
//
//        jedis.set()


    }

    @Test
    public void redisDataSringExpireTest() {

        try {
            String key = "hello";
            String value = "world";

            //method 01
//            String result = jedis.setex("hello", 10, "world");
//            System.out.println("result:" + result);

            //method 02
            String result = jedis.set(key, value);
            Long integerReply = jedis.expire(key, 10);
            System.out.println("integerReply:" + integerReply);
            System.out.println("result:" + result);


            Thread.sleep(12 * 1000);
            String val = jedis.get(key);
            System.out.println(value.equals(val));

//            jedis.set
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void hashDataTest() {

        String key = "hashDataTestKey";
        Map<String, String> map = new HashMap<String, String>();
        map.put("hashDataTestKey01", "hashDataTestValue01");
        map.put("hashDataTestKey02", "hashDataTestValue02");
        map.put("hashDataTestKey03", "hashDataTestValue03");
        String result = jedisCluster.hmset(key, map);
        System.out.println("result:" + result);

        List<String> valueList = jedisCluster.hmget(key, "hashDataTestKey01", "hashDataTestKey02");
        this.print(valueList);

    }

    /**
     *  List（列表）
     *  BLPOP,BRPOP,BRPOPLPUSH,LINDEX,LINSERT,LLEN,LPOP,LPUSH,LPUSHX,
     *  LRANGE,LREM,LSET,LTRIM, RPOP,RPOPLPUSH,RPUSH,RPUSHX
     *
     */
    @Test
    public void listDataTest() {

        String key = "listDataTestKey";
        long index = -1L;
        String value = "listDataTestValue01";

        /**
         * LPUSH key value [value ...]  将一个或多个值 value 插入到列表 key 的表头
         * 如果有多个 value 值，那么各个 value 值按从左到右的顺序依次插入到表头： 比如说，对空列表 mylist 执行命令 LPUSH mylist a b c ，列表的值将是 c b a ，
         * 这等同于原子性地执行 LPUSH mylist a 、 LPUSH mylist b 和 LPUSH mylist c 三个命令。
         * 如果 key 不存在，一个空列表会被创建并执行 LPUSH 操作。
         * <p/>
         * LPUSHX key value
         * 将值 value 插入到列表 key 的表头，当且仅当 key 存在并且是一个列表。
         * 和 LPUSH 命令相反，当 key 不存在时， LPUSHX 命令什么也不做。
         * <p/>
         * RPUSH key value [value ...] 将一个或多个值 value 插入到列表 key 的表尾(最右边)。
         * <p/>
         * RPUSHX key value
         * 将值 value 插入到列表 key 的表尾，当且仅当 key 存在并且是一个列表。
         * 和 RPUSH 命令相反，当 key 不存在时， RPUSHX 命令什么也不做。
         */


        index = jedisCluster.lpush(key, value);

        //如何key不存在, 则什么都不做
//        index = jedisCluster.lpushx(key, value);
        System.out.println("lpushx-->index:" + index);

//        String lsetResult = jedis.lset(key, index, value);

    }

    @Test
    public void setDataTest() {

    }

    @Test
    public void sortedSetDataTest() {

    }

}
