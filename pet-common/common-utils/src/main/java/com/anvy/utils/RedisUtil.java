package com.anvy.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {

    private static Logger log = LoggerFactory.getLogger(RedisUtil.class);
    private static final String ID_INDEX_KEY = "ID_INDEX_KEY_";

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("ID_START_KEY")
    private String  ID_START_KEY;


    public Object get(String key){
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    public Object set(String key,Object value,long time){
        try {
            if(time > 0){
                redisTemplate.opsForValue().set(key,value,time);
            } else {
                redisTemplate.opsForValue().set(key,value);
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return false;
        }
    }

    public Long idGenerator(String tableName){
        Long defaultId = Long.valueOf(ToolUtils.getConfig("ID_START_KEY"));
        Object idIndex = get(ID_INDEX_KEY + tableName);
        if(ToolUtils.isBlank(idIndex)){
            set(ID_INDEX_KEY+tableName,defaultId,-1);
            return defaultId;
        }
        Long index = Long.valueOf(idIndex.toString());
        index++;
        set(ID_INDEX_KEY+tableName,index,-1);
        return index;
    }

}
