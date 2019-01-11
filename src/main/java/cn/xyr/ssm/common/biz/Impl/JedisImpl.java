package cn.xyr.ssm.common.biz.Impl;

import cn.xyr.ssm.common.biz.JedisBiz;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author xyr
 * @time 2018/10/18 15:10
 */
@Service
public class JedisImpl implements JedisBiz {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public Object getObj(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void setObj(String key, Object obj) {
        redisTemplate.opsForValue().set(key, obj);
    }


    @Override
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    @Override
    public long incr(String key) {
        return 0;
    }

    @Override
    public Long hset(String hkey, String key, String value) {

        return null;
    }

    @Override
    public String hget(String hkey, String key) {
        return null;
    }

    @Override
    public Long del(String key) {
        return null;
    }

    @Override
    public Long hdel(String hkey, String key) {
        return null;
    }

    @Override
    public Long expire(String key, int second) {
        return null;
    }
}
