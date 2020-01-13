package com.echon.mybatis.example;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 西安中科天塔科技股份有限公司
 * Copyright (c) 2018-2028, tianta All Rights Reserved.<br/>
 * <b>@description:
 *
 * <b>@author: zwj
 *
 * <b>@create: 2020-01-09 17:03
 **/
public class Queue {
    public static final Logger logger = LoggerFactory.getLogger(Queue.class);
    public static final BlockingQueue<String> queue =new LinkedBlockingQueue<String>(){{
//        add("11111");
//        add("22222");
//        add("33333");
    }};


    public static void main(String[] args) throws InterruptedException {
        logger.info("来一首");
        String poll = queue.poll(10, TimeUnit.SECONDS);
        logger.info(poll);
        JSONObject json = new JSONObject();

    }

    class Mapper{
        private ObjectMapper objectMapper;
        private String name;

        public Mapper() {
        }


        public Mapper(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
        }

        public ObjectMapper getObjectMapper() {
            return objectMapper;
        }

        public void setObjectMapper(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
