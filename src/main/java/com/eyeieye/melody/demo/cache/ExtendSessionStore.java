package com.eyeieye.melody.demo.cache;

import com.eyeieye.melos.rpc.hessian.HessianZipSerializer;
import com.eyeieye.melos.util.ObjectFactory;
import com.eyeieye.melos.web.nosession.SessionStore;
import org.apache.commons.lang.SerializationUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

@Component
public class ExtendSessionStore implements SessionStore,InitializingBean {

    private JedisPool jedisPool;

    @Autowired
    private ObjectFactory objectFactory;

    private Set<String> attributeNames = new HashSet<>();


    //key一般为sessionId等，或者userid，用来标识一个用户
    @Override
    public void put(String key,Object obj) {
        Jedis jedis = jedisPool.getResource();
        jedis.hset(key.getBytes(),obj.getClass().toString().getBytes(),HessianZipSerializer.encode(obj));
        jedis.close();

    }

    @Override
    public Object get(String key) {
        Jedis jedis = jedisPool.getResource();
      //  jedis.hexists()
        return null;
    }

    @Override
    public void invalidate() {

    }

    @Override
    public Set<String> keys() {
        return attributeNames;
    }

    public Set<String> getAttributeNames() {
        return attributeNames;
    }


    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        JedisPool jedisPool = objectFactory.getBean(JedisPool.class);
        if (jedisPool != null) {
            this.jedisPool = jedisPool;
        }

    }

    private Object execute(JedisExcutor excutor) {
        Jedis jedis = jedisPool.getResource();
        Object o = excutor.execute(jedis);
        if (jedis != null) {
            jedisPool.returnResourceObject(jedis);
        }
        return o;
    }

    interface JedisExcutor {
        Object execute(Jedis jedis);
    }
}
