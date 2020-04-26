package com.xinjing.dxg.handler.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/10/19.
 */
@Component
public class RedisUtil {
    @Autowired //操作字符串的template，StringRedisTemplate是RedisTemplate的一个子集
    private StringRedisTemplate stringRedisTemplate;

    @Autowired  // RedisTemplate，可以进行所有的操作
    private RedisTemplate<Object, Object> redisTemplate;

    private static RedisUtil redisUtil;

    @PostConstruct
    public void init(){
        redisUtil = this;
    }

    public static void set(String key, String value) {
        redisUtil.stringRedisTemplate.opsForValue().set(key, value);       
    }

    public static void set(String key, String value, long timeout) {
        redisUtil.stringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.MILLISECONDS);
    }

    public static String get(String key) {
        return redisUtil.stringRedisTemplate.opsForValue().get(key);
    }

    public static void remove(String key){
        redisUtil.stringRedisTemplate.delete(key);
    }

    public static void setHash(String key, Map<Object,Object> value, long outTime){
        redisUtil.redisTemplate.opsForHash().putAll(key,value);
        redisUtil.redisTemplate.expire(key,outTime,TimeUnit.MILLISECONDS);
    }

    public static Map<Object,Object> getHash(String key){
        return redisUtil.redisTemplate.opsForHash().entries(key);
    }
    
    public static void increment(String key, Double data){
    	redisUtil.stringRedisTemplate.opsForValue().increment(key, data);
    }
    
    //获取过期时间
    public static Long getExpire(String key){
    	return redisUtil.stringRedisTemplate.getExpire(key);
    }
    
    public static Long getExpire(String key, TimeUnit timeUnit){
    	return redisUtil.stringRedisTemplate.getExpire(key, timeUnit);
    }
}
