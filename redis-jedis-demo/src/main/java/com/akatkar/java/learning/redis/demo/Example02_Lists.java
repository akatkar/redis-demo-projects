package com.akatkar.java.learning.redis.demo;

import redis.clients.jedis.Jedis;
import static com.akatkar.java.learning.redis.demo.Config.*;

/**
 * Redis Lists are simply lists of strings, sorted by insertion order
 * and make it an ideal tool to implement, for instance, message queues
 */
public class Example02_Lists {

    public static void main(String[] args) {

        try (Jedis jedis = new Jedis(REDIS_HOST)) {
            jedis.lpush("queue#tasks", "firstTask");
            jedis.lpush("queue#tasks", "secondTask");

            String  task = jedis.rpop("queue#tasks");
            System.out.println(task);
        }
    }
}
