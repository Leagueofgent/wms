package com.warehouse.management.wms.config;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SuppressWarnings(value = {"unchecked", "rawtypes"})
@Component
public class RedisCache {

    @Resource
    private RedisTemplate redisTemplate;


    /**
     * 缓存基本对象
     *
     * @param key   缓存键
     * @param value 缓存值
     */
    public <T> void setCacheObject(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * @param key      缓存键
     * @param value    缓存值
     * @param timeOut  时间
     * @param timeUnit 时间颗粒度
     * @param <T>
     */
    public <T> void setCacheObject(final String key, final T value, final Integer timeOut, final TimeUnit timeUnit) {

        redisTemplate.opsForValue().set(key, value, timeOut, timeUnit);
    }

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeOut 超时时间
     * @return true:设置成功 false:设置失败
     */
    public boolean expire(final String key, final long timeOut) {
        return expire(key, timeOut);
    }

    /**
     * 设置有效时间
     *
     * @param key      Redis键
     * @param timeOut  超时时间
     * @param timeUnit 单位时间
     * @return true:设置成功 false:设置失败
     */
    public boolean expire(final String key, final long timeOut, final TimeUnit timeUnit) {
        return redisTemplate.expire(key, timeOut, timeUnit);
    }

    /**
     * 获取缓存的基本对象
     *
     * @param key 缓存键
     * @param <T> 泛型
     * @return 缓存键值对应的数据
     */
    public <T> T getCacheObject(final String key) {
        ValueOperations<String, T> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * 删除单个对象
     *
     * @param key 对象
     * @return boolean
     */
    public boolean deleteObject(final String key) {
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }

    /**
     * 删除对象集合
     *
     * @param collection 多个对象
     * @return long
     */
    public long deleteObject(final Collection collection) {
        return redisTemplate.delete(collection);
    }

    /**
     * 缓存list数据
     *
     * @param key      缓存键
     * @param dataList 待缓存的list数据
     * @param <T>      泛型
     * @return 缓存的对象
     */
    public <T> Long setCacheList(final String key, final List<T> dataList) {
        Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
        return count == null ? 0 : count;
    }

    /**
     * 获得缓存的list对象
     *
     * @param key 缓存的键
     * @param <T> 泛型
     * @return 缓存见对应的数据
     */
    public <T> List<T> getCacheList(final String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 缓存set
     *
     * @param key      缓存键
     * @param dataList 缓存的数据
     * @param <T>      泛型
     * @return 缓存数据的对象
     */
    public <T> BoundSetOperations<String, T> setCacheSet(final String key, final Set<T> dataList) {
        BoundSetOperations boundSetOperations = redisTemplate.boundSetOps(key);

        for (T t : dataList) {
            boundSetOperations.add(t);
        }
        return boundSetOperations;
    }

    /**
     * 获得缓存的set
     *
     * @param key 缓存键
     * @param <T> 泛型
     * @return 返回缓存数据
     */
    public <T> Set<T> getCacheSet(final String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 缓存map
     *
     * @param key      缓存键
     * @param dataList 缓存的数据
     * @param <T>      泛型
     * @return 缓存数据的对象
     */
    public <T> void setCacheMap(final String key, final Map<String, T> dataList) {
        if (dataList != null) {
            redisTemplate.opsForHash().putAll(key, dataList);
        }
    }

    /**
     * 获得缓存的map
     *
     * @param key 缓存键
     * @param <T> 泛型
     * @return 返回缓存数据
     */
    public <T> Map<String, T> getCacheMap(final String key) {
        return redisTemplate.opsForHash().entries(key);
    }


    /**
     * 缓存Hash
     *
     * @param key   redis缓存键
     * @param key1  hash缓存键
     * @param value 值
     * @param <T>   泛型
     */
    public <T> void setCacheHash(final String key, final String key1, final T value) {
        redisTemplate.opsForHash().put(key, key1, value);
    }

    /**
     * 获得缓存的map
     *
     * @param key 缓存键
     * @param <T> 泛型
     * @return 返回缓存数据
     */
    public <T> T getCacheHash(final String key, final String key1) {
        HashOperations<String, String, T> hashOperations = redisTemplate.opsForHash();
        return hashOperations.get(key, key1);
    }

    /**
     * 删除hash中的数据
     *
     * @param key  redis键
     * @param key1 hash键集合
     */
    public void delCacheHash(final String key, final String key1) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(key, key1);
    }

    /**
     * 获得hash中的数据
     *
     * @param key  redis键
     * @param key1 hash键集合
     * @return Hash对象集合
     */
    public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> key1) {
        return redisTemplate.opsForHash().multiGet(key, key1);
    }

    /**
     * 获得缓存的基本对象列
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    public Collection<String> keys(final String pattern) {
        return redisTemplate.keys(pattern);
    }

}
