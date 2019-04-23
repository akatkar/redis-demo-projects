package com.akatkar.java.learning.redis.demo.pubsub;

import redis.clients.jedis.Jedis;

import static com.akatkar.java.learning.redis.demo.Config.REDIS_HOST;

public class MainClass {
    public static void main(String[] args) {
        RedisChannel channel = new RedisChannel("sport","fashion");

        try(Jedis publisher = new Jedis(REDIS_HOST)) {
            publisher.publish("sport", "Barcelona wins");
            publisher.publish("fashion", "cat walks in Paris");
            channel.unsubscribe("sport");
            channel.unsubscribe("fashion");
        }
    }
}
