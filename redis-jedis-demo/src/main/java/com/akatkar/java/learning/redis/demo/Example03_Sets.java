package com.akatkar.java.learning.redis.demo;

import java.util.Set;
import redis.clients.jedis.Jedis;
import static com.akatkar.java.learning.redis.demo.Config.*;

/**
 * Redis Sets are an unordered collection of Strings that come in handy 
 * when you want to exclude repeated members:
 */
public class Example03_Sets {

    public static void main(String[] args) {

        try(Jedis jedis = new Jedis(REDIS_HOST)) {
            jedis.sadd("nicknames", "zeyno");
            jedis.sadd("nicknames", "sülo");
            jedis.sadd("nicknames", "fatoş");

            // retrieve nicknames from redis
            Set<String> nicknames = jedis.smembers("nicknames");
            System.out.println(nicknames);
            // check if nickname#1 is exists from redis
            boolean exists = jedis.sismember("nicknames", "apo");
            System.out.println(exists);
        }
    }
}
