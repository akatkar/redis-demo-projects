package com.akatkar.java.learning.redis.demo;

import java.util.Map;
import redis.clients.jedis.Jedis;

import javax.xml.bind.SchemaOutputResolver;

import static com.akatkar.java.learning.redis.demo.Config.*;

/**
 * Redis Hashes are mapping between String fields and String values:
 */
public class Example04_Hashes {

    public static void main(String[] args) {

        try(Jedis jedis = new Jedis(REDIS_HOST)) {
            jedis.hset("user#1", "name", "Peter");
            jedis.hset("user#1", "job", "politician");

            // retrieve name of user1 from redis
            String name = jedis.hget("user#1", "name");
            System.out.println(name);
            // retrieve all fields of user1 from redis
            Map<String, String> fields = jedis.hgetAll("user#1");
            String job = fields.get("job");
            System.out.println(job);
        }
    }
}
