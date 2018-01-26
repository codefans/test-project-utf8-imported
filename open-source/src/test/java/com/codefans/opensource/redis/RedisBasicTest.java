package com.codefans.opensource.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.*;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static redis.clients.jedis.BinaryClient.LIST_POSITION;

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
     * Jedis操作Redis--List类型
     * https://www.cnblogs.com/Laymen/p/6121297.html
     */
    @Test
    public void listDataTest() {

        String key = "listDataTestKey";
        long index = -1L;
        String value = "listDataTestValue01";
        String value02 = "listDataTestValue02";
        String value03 = "listDataTestValue03";
        String value04 = "listDataTestValue04";
        String value05 = "listDataTestValue05";

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


        //如何key不存在, 则什么都不做
        index = jedisCluster.lpushx(key, value);
        System.out.println("lpushx-->index:" + index);

        //如果key不存在，一个空列表会被创建并执行 LPUSH 操作。
        index = jedisCluster.lpush(key, value, value02, value03, value04, value05);
        System.out.println("lpush-->index:" + index);

        //移除并返回列表 key 的头元素。
        String topElement = jedisCluster.lpop(key);
        System.out.println("topElement:" + topElement);

        //移除并返回列表 key 的尾元素。
        String tailElement = jedisCluster.rpop(key);
        System.out.println("tailElement:" + tailElement);

        //它是LPOP 命令的阻塞版本，当给定列表内没有任何元素可供弹出的时候，
        // 连接将被BLPOP 命令阻塞，直到等待超时或发现可弹出元素为止。
        List<String> blockTopElement = jedisCluster.blpop(1000, key);
        System.out.println("blockTopElement:" + blockTopElement);

        //它是RPOP 命令的阻塞版本，当给定列表内没有任何元素可供弹出的时候，
        // 连接将被BRPOP 命令阻塞，直 到等待超时或发现可弹出元素为止。
        List<String> blockTailElement = jedisCluster.brpop(1000, key);
        System.out.println("blockTailElement:" + blockTailElement);

        //key对应列表的长度
        long len = jedisCluster.llen(key);
        System.out.println("len:" + len);

        this.printList(jedisCluster, key, 0, len);

//        LREM key count value
//        根据参数 count 的值，移除列表中与参数 value 相等的元素。
//        count 的值可以是以下几种：
//    　　 count > 0 : 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count 。
//    　　 count < 0 : 从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝对值。
//    　　 count = 0 : 移除表中所有与 value 相等的值。
//            时间复杂度： O(N)，N 为列表的长度。
//            返回值：
//    　　 被移除元素的数量。
//    　　 因为不存在的 key 被视作空表 (empty list)，所以当 key 不存在时，LREM 命令总是返回 0
        this.lrem(jedisCluster, key, 1, value04);

        len = jedisCluster.llen(key);
        System.out.println("len:" + len);

        jedisCluster.lpush(key, value05, value04);

//        LINSERT key BEFORE|AFTER pivot value
//        将值 value 插入到列表 key 当中，位于值 pivot 之前或之后。
//        当 pivot 不存在于列表 key 时，不执行任何操作。
//        当 key 不存在时，key 被视为空列表，不执行任何操作。
        long insertIndex = jedisCluster.linsert(key, LIST_POSITION.AFTER, "0", "secondValue");
        System.out.println("insertIndex:" + insertIndex);

        this.printList(jedisCluster, key, 0, len);

        String firstElm = jedisCluster.lindex(key, 0);
        System.out.println("firstElm:" + firstElm);

//        将列表 key 下标为 index 的元素的值设置为 value 。
//        当 index 参数超出范围，或对一个空列表 ( key 不存在) 进行LSET 时，返回一个错误。
        jedisCluster.lset(key, 0, "lsetValue");

        len = jedisCluster.llen(key);
        System.out.println("len:" + len);
        this.printList(jedisCluster, key, 0, len);



    }


    @Test
    public void setDataTest() {

        String setKey = "setKey";
        String setVal01 = "setVal01";
        String setVal02 = "setVal02";
        String setVal03 = "setVal03";
        String setVal04 = "setVal04";

        String setKey02 = "setKey02";
        String setVal0201 = "setVal01";
        String setVal0202 = "setVal0202";
        String setVal0203 = "setVal0203";
        String setVal0204 = "setVal0204";

        String setKey03 = "setKey03";
        String setVal0301 = "setVal0301";
        String setVal0302 = "setVal0302";
        String setVal0303 = "setVal0303";
        String setVal0304 = "setVal0304";

//        SADD key member [member …]
//        将一个或多个 member 元素加入到集合 key 当中，已经存在于集合的 member 元素将被忽略。
//        假如 key 不存在，则创建一个只包含 member 元素作成员的集合。
//        当 key 不是集合类型时，返回一个错误。
//        返回值: 被添加到集合中的新元素的数量，不包括被忽略的元素。
        long count = jedisCluster.sadd(setKey, setVal01, setVal02, setVal03, setVal04);
        System.out.println("newElmCount: " + count);

        long set02ElmCount = jedisCluster.sadd(setKey02, setVal0201, setVal0202, setVal0203, setVal0204);
        long set03ElmCount = jedisCluster.sadd(setKey03, setVal0301, setVal0302, setVal0303, setVal0304);
        System.out.println("set02ElmCount: " + set02ElmCount);
        System.out.println("set03ElmCount: " + set03ElmCount);

//        SISMEMBER key member
//        判断 member 元素是否集合 key 的成员。
//        时间复杂度: O(1)
//        返回值:
//    　　如果 member 元素是集合的成员，返回 1 。
//    　　如果 member 元素不是集合的成员，或 key 不存在，返回 0 。
        boolean isMember = jedisCluster.sismember(setKey, setVal01);
        System.out.println(setVal01 + " is " + (isMember ? "in" : "not in") + " this set");

//        SREM key member [member …]
//        移除集合 key 中的一个或多个 member 元素，不存在的 member 元素会被忽略。
//        当 key 不是集合类型，返回一个错误。
//        时间复杂度: O(N)，N 为给定 member 元素的数量。
//        返回值: 被成功移除的元素的数量，不包括被忽略的元素。
        long remCount = jedisCluster.srem(setKey, setVal01);
        System.out.println(setVal01 + " is removed, remove count is " + remCount);

//        SCARD key
//        返回集合 key 的基数 (集合中元素的数量)。
//        时间复杂度: O(1)
//        返回值：
//    　　 集合的基数。
//    　　 当 key 不存在时，返回 0 。
        long membCount = jedisCluster.scard(setKey);
        System.out.println("membCount:" + membCount);

//        SMEMBERS key
//        返回集合 key 中的所有成员。
//        不存在的 key 被视为空集合。
//        时间复杂度: O(N)，N 为集合的基数。
//        返回值: 集合中的所有成员。
        Set<String> members = jedisCluster.smembers(setKey);
        System.out.println("members list:");
        this.printSet(members);


        String diffKey01 = "{diffKey}01";
        String diffKey02 = "{diffKey}02";

//        String prefix = "luffi:lbl";
//        String KEY_SPLIT = ":"; //用于隔开缓存前缀与缓存键值
//        String diffKey01 = "{" + prefix + KEY_SPLIT + "set}abcccdfdfd";
//        String diffKey02 = "{" + prefix + KEY_SPLIT + "set}bcdgfgfhfhfhfhf";

        jedisCluster.del(diffKey01);
        jedisCluster.del(diffKey02);

        jedisCluster.sadd(diffKey01, "diffVal01", "diffVal02", "diffVal03");
        jedisCluster.sadd(diffKey02, "diffVal01", "diffVal04", "diffVal05");

        long memberCount = jedisCluster.scard(diffKey01);
        long memberCount2 = jedisCluster.scard(diffKey02);
        System.out.println("diffMemCount:" + memberCount);
        System.out.println("diffMemCount2:" + memberCount2);

//        SDIFF key [key …]
//        返回一个集合的全部成员，该集合是所有给定集合之间的差集。
//        不存在的 key 被视为空集。
//        时间复杂度: O(N)，N 是所有给定集合的成员数量之和。
//        返回值: 交集成员的列表。
        Set<String> diffSets = jedisCluster.sdiff(diffKey01, diffKey02);
        System.out.println("diffSets is:");
        this.printSet(diffSets);

        String destKey = "{diffKey}destKey";
        long diffStoreCount = jedisCluster.sdiffstore(destKey, diffKey01, diffKey02);
        System.out.println("diffStoreCount:" + diffStoreCount);
        Set<String> diffStoreSets = jedisCluster.smembers(destKey);
        System.out.println("diffstore dest sets is:");
        this.printSet(diffStoreSets);

//        SINTER
//        SINTER key [key …]
//        返回一个集合的全部成员，该集合是所有给定集合的交集。
//        不存在的 key 被视为空集。
//        当给定集合当中有一个空集时，结果也为空集 (根据集合运算定律)。
//        时间复杂度: O(N * M)，N 为给定集合当中基数最小的集合，M 为给定集合的个数。
//        返回值: 交集成员的列表。
        Set<String> interSets = jedisCluster.sinter(diffKey01, diffKey02);
        System.out.println("interSets is:");
        this.printSet(interSets);

        String interDestKey = "{diffKey}destKey";
//        String interDestKey = "interDestKey"; //报错：No way to dispatch this command to Redis Cluster because keys have different slots.
        long interStoreCount = jedisCluster.sinterstore(interDestKey, diffKey01, diffKey02);
        System.out.println("interStoreCount:" + interStoreCount);
        Set<String> interStoreSets = jedisCluster.smembers(interDestKey);
        System.out.println("inter store sets is:");
        this.printSet(interStoreSets);

//        SUNION key [key …]
//        返回一个集合的全部成员，该集合是所有给定集合的并集。
//        不存在的 key 被视为空集。
//        时间复杂度: O(N)，N 是所有给定集合的成员数量之和。
//        返回值: 并集成员的列表。
        Set<String> unionSets = jedisCluster.sunion(diffKey01, diffKey02);
        System.out.println("unionSets is:");
        this.printSet(unionSets);

        String unionDestKey = "{diffKey}destKey";
//        String unionDestKey = "interDestKey"; //报错：No way to dispatch this command to Redis Cluster because keys have different slots.
        long unionStoreCount = jedisCluster.sunionstore(unionDestKey, diffKey01, diffKey02);
        System.out.println("interStoreCount:" + unionStoreCount);
        Set<String> unionStoreSets = jedisCluster.smembers(unionDestKey);
        System.out.println("union store sets is:");
        this.printSet(unionStoreSets);

//        jedisCluster.smove(String srckey, String dstkey, String member);
//        SMOVE source destination member
//        将 member 元素从 source 集合移动到 destination 集合。
//        SMOVE 是原子性操作。如果 source 集合不存在或不包含指定的 member 元素，则SMOVE 命令不执行任何操作，仅返回 0 。否则， member 元素从 source 集合中被移除，并添加到 destination 集合中去。
//        当 destination 集合已经包含 member 元素时，SMOVE 命令只是简单地将 source 集合中的 member 元素删除。
//        当 source 或 destination 不是集合类型时，返回一个错误。
//        返回值:
//    　　如果 member 元素被成功移除，返回 1 。
//    　　如果 member 元素不是 source 集合的成员，并且没有任何操作对 destination 集合执行，那么返回
        String smoveKey01 = "{smoveKey}01";
        String smoveKey02 = "{smoveKey}02";
        jedisCluster.sadd(smoveKey01, "diffVal01", "diffVal02", "diffVal03");
        jedisCluster.sadd(smoveKey02, "diffVal01", "diffVal04", "diffVal05");
        jedisCluster.smove(smoveKey02, smoveKey01, "diffVal04");
        Set<String> smoveSet01 = jedisCluster.smembers(smoveKey01);
        Set<String> smoveSet02 = jedisCluster.smembers(smoveKey02);
        System.out.println("smove sets 01:");
        this.printSet(smoveSet01);
        System.out.println("smove sets 02:");
        this.printSet(smoveSet02);

//        jedisCluster.spop(String key);
//        SPOP key
//        移除并返回集合中的一个随机元素。
//        如果只想获取一个随机元素，但不想该元素从集合中被移除的话，可以使用SRANDMEMBER 命令。
//        时间复杂度: O(1)
//        返回值:
//    　　 被移除的随机元素。
//    　　 当 key 不存在或 key 是空集时，返回 nil 。
        String spopKey = "spopKey";
        jedisCluster.sadd(spopKey, "spopVal01", "spopVal02");
        String spopVal = jedisCluster.spop(spopKey);
        System.out.println(spopVal + " remove from set " + spopKey);
        Set<String> spopSets = jedisCluster.smembers(spopKey);
        System.out.println("spop sets is:");
        this.printSet(spopSets);


//        jedisCluster.srandmember(String key);
//        SRANDMEMBER key [count]
//        如果命令执行时，只提供了 key 参数，那么返回集合中的一个随机元素。
//        SRANDMEMBER 命令接受可选的 count 参数：
//    　　 如果 count 为正数，且小于集合基数，那么命令返回一个包含 count 个元素的数组，数组中的元素各不相同。如果 count 大于等于集合基数，那么返回整个集合。
//    　　 如果 count 为负数，那么命令返回一个数组，数组中的元素可能会重复出现多次，而数组的长度为count 的绝对值。
//        该操作和SPOP 相似，但SPOP 将随机元素从集合中移除并返回，而SRANDMEMBER 则仅仅返回随机元素，而不对集合进行任何改动。
//        时间复杂度:
//    　　 只提供 key 参数时为 O(1) 。
//    　　 如果提供了 count 参数，那么为 O(N) ，N 为返回数组的元素个数。
//        返回值:
//    　　 只提供 key 参数时，返回一个元素；如果集合为空，返回 nil 。
//    　　 如果提供了 count 参数，那么返回一个数组；如果集合为空，返回空数组。
        String srandKey = "srandKey";
        jedisCluster.sadd(srandKey, "srandVal01", "srandVal02");
        String srandVal = jedisCluster.srandmember(srandKey);
        System.out.println(srandVal + " got from set " + srandKey + " randly");
        Set<String> srandSets = jedisCluster.smembers(srandKey);
        System.out.println("srand sets is:");
        this.printSet(srandSets);


        String sscanKey = "sscanKey";
        jedisCluster.sadd(sscanKey, "sscanVal01", "sscanVal02", "helloSsacnVal01", "helloSsacnVal02");
//        jedisCluster.sadd(sscanKey, "sscanVal01", "helloSsacnVal01", "sscanVal02", "helloSsacnVal02");
        String cursor = ScanParams.SCAN_POINTER_START;
        ScanParams scanParams = new ScanParams();
        scanParams.match("he*");
        scanParams.count(2); //这个count的作用？？？好像不能通过这个来严格控制返回元素的个数,只能是个大概的值
        ScanResult<String> scanResult = null;
        boolean cycleIsFinished = false;
        System.out.println("sscan list is:");
        while(!cycleIsFinished){
            scanResult = jedisCluster.sscan(sscanKey, cursor, scanParams);
            List<String> result = scanResult.getResult();
            if(result != null && result.size() > 0) {
                this.printList(result);
            }
            cursor = scanResult.getStringCursor();
            if (cursor.equals("0")){
                cycleIsFinished = true;
            }
        }




    }

    @Test
    public void sortedSetDataTest() {

        String sortedSetKey01 = "sortedSetKey01";
        double score01 = 1.0;
        double score02 = 1.1;
        double score03 = 1.2;
        String member01 = "sortedSetMember01";
        String member02 = "sortedSetMember02";
        String member03 = "sortedSetMember03";
        //添加新元素
        jedisCluster.zadd(sortedSetKey01, score01, member01);
        jedisCluster.zadd(sortedSetKey01, score02, member02);
        jedisCluster.zadd(sortedSetKey01, score03, member03);

        double updateScore = 1.4;
//        添加的元素已存在，则更新 score 值
        jedisCluster.zadd(sortedSetKey01, updateScore, member01);
        //获取分数值在updateScore和updateScore之间的所有元素
        Set<String> sets = jedisCluster.zrangeByScore(sortedSetKey01, updateScore, updateScore);
        System.out.println("分数为[" + updateScore + "]的元素如下:");
        this.printSet(sets);

//        jedisCluster.zcount(String key, double min, double max);
//        jedisCluster.zcount(String key, String min, String max);
//        ZCOUNT key min max
//        返回有序集 key 中，score 值在 min 和 max 之间 (默认包括 score 值等于 min 或 max ) 的成员的数量。
//        时间复杂度: O(log(N)+M)，N 为有序集的基数，M 为值在 min 和 max 之间的元素的数量。
//        返回值: score 值在 min 和 max 之间的成员的数量。

        double min = 1.0;
        double max = 1.1;
        long zcount01 = jedisCluster.zcount(sortedSetKey01, min, max);
        System.out.println("members between [" + min + ", " + max + "] is:" + zcount01);

        String minStr = "1.0";
        String maxStr = "1.2";
        long zcount02 = jedisCluster.zcount(sortedSetKey01, minStr, maxStr);
        System.out.println("members between [" + minStr + ", " + maxStr + "] is:" + zcount02);

//        jedisCluster.zcard(String key);
//        ZCARD key
//        返回有序集 key 的总数。
//        时间复杂度: O(1)
//        返回值:
//    　　 当 key 存在且是有序集类型时，返回有序集的总数。
//    　　 当 key 不存在时，返回 0 。
        long zcard = jedisCluster.zcard(sortedSetKey01);
        System.out.println("the zcard of key[" + sortedSetKey01 + "] is=" + zcard);







    }


    public void lrem(JedisCluster jedisCluster, String key, long count, String value) {
        jedisCluster.lrem(key, count, value);
    }

    public void printList(JedisCluster jedisCluster, String key, long startIndex, long len) {
        List<String> list = jedisCluster.lrange(key, startIndex, len);
        for(int i = 0; i < list.size(); i ++) {
            System.out.println("index:" + (i + 1) + ", data:" + jedisCluster.lpop(key));
        }
    }

    public  void printSet(Set<String> set) {
        Iterator<String> iter = set.iterator();
        boolean isFirst = true;
        while(iter.hasNext()) {
            if(isFirst) {
                isFirst = false;
                System.out.print("[" + iter.next());
            } else {
                System.out.print(", " + iter.next());
            }
        }
        if(set.size() > 0) {
            System.out.println("]");
        }
    }

    public  void printList(List<String> list) {
        for(int i = 0; i < list.size(); i ++) {
            if(i == 0) {
                System.out.print("[" + list.get(i));
            } else {
                System.out.print(", " + list.get(i));
            }
        }
        if(list.size() > 0) {
            System.out.println("]");
        }
    }

}
