package cn.xyr.ssm.common.biz;

/**
 * @author xyr
 * @time 2018/10/18 14:56
 */

public interface JedisBiz {
    String get(String key);

    /**
     * 获取对象 这个对象必须实现 Serializable 接口
     *
     * @param key
     * @return
     */
    Object getObj(String key);

    /**
     * 存对象 这个对象必须实现 Serializable 接口
     *
     * @param key
     * @param obj
     */
    void setObj(String key, Object obj);

    void set(String key, String value);

    long incr(String key);

    Long hset(String hkey, String key, String value);

    String hget(String hkey, String key);

    Long del(String key);

    Long hdel(String hkey, String key);

    Long expire(String key, int second);
}
