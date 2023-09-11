package com.kingmed.immuno.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisLockUtil {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 获取锁
     * @param lockKey 锁的key
     * @param requestId 请求标识
     * @param expireTime 超时时间，单位为秒
     * @return 是否获取成功
     */
    public boolean lock(String lockKey, String requestId, long expireTime) {
        Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, requestId, expireTime, TimeUnit.SECONDS);
        if (result != null && result) {
            return true;
        } else {
            // 释放锁
            releaseLock(lockKey, requestId);
            return false;
        }
    }

    /**
     * 释放锁
     * @param lockKey 锁的key
     * @param requestId 请求标识
     */
    public void releaseLock(String lockKey, String requestId) {
//        DefaultRedisScript<Long> script = new DefaultRedisScript<>(new LongSerializationRedisSerializer(), Long::parseLong);
//        String luaScript = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
//        List<String> keys = Collections.singletonList(lockKey);
//        Long result = stringRedisTemplate.execute(script, keys, requestId);
//        if (result != null && result > 0) {
//            System.out.println("释放锁成功，requestId：" + requestId);
//        } else {
//            System.out.println("释放锁失败，requestId：" + requestId);
//        }
    }
}
