package com.redis.demo;

import redis.clients.jedis.Jedis;

public class RedisConnect {
    //1.单独连接一台redis服务器
    private static Jedis jedis;
    public static void main(String[] args){
        jedis = new Jedis("127.0.0.1", 32770);
        System.out.println(jedis);
        jedis.append("name", "wb");
        jedis.mset("name", "wb","age", "25", "sex", "man");
    }
}
