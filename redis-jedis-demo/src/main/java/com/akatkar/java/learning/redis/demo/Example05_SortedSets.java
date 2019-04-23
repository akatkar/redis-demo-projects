package com.akatkar.java.learning.redis.demo;

import java.util.HashMap;
import java.util.Map;
import redis.clients.jedis.Jedis;
import static com.akatkar.java.learning.redis.demo.Config.*;

/**
 * Sorted Sets are like a Set where each member has an associated ranking, that
 * is used for sorting them:
 */
public class Example05_SortedSets {

    public static void main(String[] args) {

        try(Jedis jedis = new Jedis(REDIS_HOST)) {

            Map<String, Double> scores = new HashMap<>();
            scores.put("PlayerOne", 3000.0);
            scores.put("PlayerTwo", 1500.0);
            scores.put("PlayerThree", 8200.0);

            scores.forEach((key, value) -> jedis.zadd("scores", value, key));

            String player = jedis.zrevrange("scores", 0, 1).iterator().next();
            long rank = jedis.zrevrank("scores", "PlayerOne");
            System.out.println("player:" + player + ", rank:" + rank);
        }
    }
}
