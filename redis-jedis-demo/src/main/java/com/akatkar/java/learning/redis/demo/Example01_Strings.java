package com.akatkar.java.learning.redis.demo;

import redis.clients.jedis.Jedis;
import static com.akatkar.java.learning.redis.demo.Config.*;

/**
 * Strings are the most basic kind of Redis value, 
 * useful for when you need to persist simple key-value data types:
 */
public class Example01_Strings {
    public static void main(String[] args) {
        try (Jedis jedis = new Jedis(REDIS_HOST)) {
            jedis.set("events/city/istanbul", "32,15,223,828");
            String  cachedResponse = jedis.get("events/city/rome");
            System.out.println(cachedResponse);
        }
    }
}
