package com.eyeieye.melody.demo.cache;

import com.eyeieye.melos.rpc.hessian.HessianZipSerializer;
import com.eyeieye.melos.web.nosession.SessionStore;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

public class SessionRedisStore implements SessionStore {

    @Autowired
    JedisPool jedisPool;

    Set<String> attributeNames = new HashSet<>();

    public Set<String> getAttributeNames() {
        return attributeNames;
    }

    public void setAttributeNames(Set<String> attributeNames) {
        this.attributeNames = attributeNames;
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public void put(String sessionId, String key, Object value) {

        Jedis jedis = jedisPool.getResource();
        if(null == value){
            jedis.del(key);
        }else {
            jedis.hset(sessionId.getBytes(),key.getBytes(), HessianZipSerializer.encode(value));
        }
        jedis.close();
    }

    @Override
    public Object get(String sessionId, String key) {

        Jedis jedis = jedisPool.getResource();
        byte[] bytes = jedis.hget(sessionId.getBytes() , key.getBytes());

        jedis.close();

        return bytes == null ? null:HessianZipSerializer.decode(bytes);
    }

    @Override
    public Set<String> keys() {

        return attributeNames;
    }

    @Override
    public void invalidate(String sessionId) {
        Jedis jedis = jedisPool.getResource();
        jedis.del(sessionId.getBytes());
        jedis.close();


    }

    @Override
    public int getOrder() {
        return 0;
    }
}
