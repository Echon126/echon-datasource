package com.enchon.mybatis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONPObject;
import com.echon.mybatis.utils.RedisUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 西安中科天塔科技股份有限公司
 * Copyright (c) 2018-2028, tianta All Rights Reserved.<br/>
 * <b>@description:
 *
 * <b>@author: zwj
 *
 * <b>@create: 2020-01-07 14:46
 **/
@SpringBootConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisTest {
    private final static Logger logger = LoggerFactory.getLogger(MyBatisTest.class);

    private Jedis jedis;

    @Before
    public void initJedis() {
        jedis = new Jedis("192.168.160.146", 6379);
        jedis.auth("tiantaDBredis");
        jedis.select(2);
    }

    @Test
    public void testRedisClient() {
        String str = "2020-01-08";
        //指定转换格式
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //进行转换
        LocalDate date = LocalDate.parse(str, fmt);
        boolean b = RedisUtils.doSign(jedis, 2000, date);
        System.out.println(b);
        long signCount = RedisUtils.getSignCount(jedis, 2000, date);
        System.out.println(signCount);
        System.out.println("当月签到情况：");
        Map<String, Boolean> signInfo = new TreeMap<>(RedisUtils.getSignInfo(jedis, 2000, date));
        for (Map.Entry<String, Boolean> entry : signInfo.entrySet()) {
            System.out.println(entry.getKey() + ": " + (entry.getValue() ? "√" : "-"));
        }
        ZonedDateTime zonedDateTime = ZonedDateTime.parse("2019-01-26T10:15:30+01:00[Europe/Paris]");

    }

    @Test
    public void TestRedisList() {
        this.jedis.sadd("user:1000", "1", "2", "3", "4");
        List<String> listUser = new ArrayList<String>() {
            private static final long serialVersionUID = 6238279873669248100L;

            {
                add(JSON.toJSONString(new User(1, "message0001")));
                add(JSON.toJSONString(new User(2, "message0002")));
                add(JSON.toJSONString(new User(3, "message0003")));
            }
        };
        this.jedis.sadd("Key:20000", listUser.toArray(new String[]{}));


        this.jedis.zadd("zadd", 50, "member001");
        this.jedis.zadd("zadd", 20, "member002");
        this.jedis.zadd("zadd", 30, "member003");
        this.jedis.zadd("zadd", 40, "member004");
        Set<String> zadd = this.jedis.zrevrange("zadd", 0, 1);


        this.jedis.zadd("zadd", new HashMap<String, Double>() {{
            put("member0005", 60.0);
            put("member0006", 70.0);
        }});

        this.jedis.zincrby("zadd", 90, "member0006");

        System.out.println(zadd.toString());
    }

    static String tryGetHongBaoScript =
//			"local bConsumed = redis.call('hexists', KEYS[3], KEYS[4]);\n"
//			+ "print('bConsumed:' ,bConsumed);\n"
            "if redis.call('hexists', KEYS[3], KEYS[4]) ~= 0 then\n"
                    + "return nil\n"
                    + "else\n"
                    + "local hongBao = redis.call('rpop', KEYS[1]);\n"
//			+ "print('hongBao:', hongBao);\n"
                    + "if hongBao then\n"
                    + "local x = cjson.decode(hongBao);\n"
                    + "x['userId'] = KEYS[4];\n"
                    + "local re = cjson.encode(x);\n"
                    + "redis.call('hset', KEYS[3], KEYS[4], KEYS[4]);\n"
                    + "redis.call('lpush', KEYS[2], re);\n"
                    + "return re;\n"
                    + "end\n"
                    + "end\n"
                    + "return nil";

    @Test
    public void testRedisSet() {
    /*    for (int i = 0; i < 100; i++) {
            this.jedis.sadd("sadd",i+"_userID");
        }

        Set<String> sadd = this.jedis.spop("sadd", 2);

        Set<String> sadd1 = this.jedis.spop("sadd", 5);

        System.out.println(sadd.toString());
        System.out.println(sadd1.toString());*/

        //this.jedis.smembers()

        //this.jedis.hset("Map_key","uuid","uuid_Map_key");

        //this.jedis.set("String-Key", "String=Key");
        Pipeline pipelined = this.jedis.pipelined();
        pipelined.getSet("String-Key", "dxxxxx");

    }


    class User {
        private Integer Id;
        private String message;

        public User(Integer id, String message) {
            Id = id;
            this.message = message;
        }

        public Integer getId() {
            return Id;
        }

        public void setId(Integer id) {
            Id = id;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }


}
