package com.akatkar.java.learning.redis.demo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import static com.akatkar.java.learning.redis.demo.Config.*;

/**
 * We can use the Redis messaging broker functionality to send messages between
 * the different components of our system. Make sure the subscriber and
 * publisher threads do not share the same Jedis connection.
 */
public class Example07_PubSub {

    private static final String CHANNEL_NAME = "sport";

    private static final JedisPubSub channel = new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println(channel + " : " + message);
            }
            @Override
            public void onSubscribe(String channel, int subscribedChannels) {
                System.out.println("subcribed succesfully to " + channel + "["+subscribedChannels+"]");
            }

            @Override
            public void onUnsubscribe(String channel, int subscribedChannels) {
                System.out.println("unsubcribed succesfully from " + channel + "["+subscribedChannels+"]");
            } 
        };

    private static void subscribe() {
        try(Jedis subscriber = new Jedis(REDIS_HOST)) {
            subscriber.subscribe(channel, CHANNEL_NAME);
        }
    }

    public static void main(String[] args) {
        new Thread(Example07_PubSub::subscribe).start();
        
        try(Jedis publisher = new Jedis(REDIS_HOST)) {
            publisher.publish(CHANNEL_NAME, "Barcelona");
            publisher.publish(CHANNEL_NAME, "Real Madrid");
            channel.unsubscribe(CHANNEL_NAME);
        }
    }
}
