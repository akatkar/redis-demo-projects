package com.akatkar.java.learning.redis.demo.pubsub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import static com.akatkar.java.learning.redis.demo.Config.REDIS_HOST;

public class RedisChannel extends JedisPubSub implements Runnable {
    private Jedis connection;
    private String[] channels;

    public RedisChannel(String... channels) {
        connection = new Jedis(REDIS_HOST);
        this.channels = channels;
        new Thread(this).start();
    }

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

    @Override
    public void run() {
        connection.subscribe(this,channels);
    }
}
