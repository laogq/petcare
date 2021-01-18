package com.anvy.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ToolUtils {

    private static Logger log = LoggerFactory.getLogger(ToolUtils.class);

    public static void main(String[] args) {
        String mysql_url = getConfig("mysql_url");
        System.out.println(mysql_url);
    }

    /**
    * @Description: 读取配置文件。
    * @author Anvy Lao
    * @date 2020/5/12 15:57
    */
    public static String getConfig(String key){
        try {
            Properties properties = new Properties();
            InputStream resourceAsStream = ToolUtils.class.getResourceAsStream("/application.properties");
            properties.load(resourceAsStream);
            Object object = properties.get(key);
            return object.toString();
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * @Description:
     * @author Anvy Lao
     * @date 2020/5/12 15:57
     */
    public static Boolean isBlank(Object value){
        if(value == null){
            return true;
        }
        if(value instanceof String){
            String str = (String) value;
            if(StringUtils.isBlank((str))){
                return true;
            }
            return false;
        }
        if(value instanceof Integer){
            Integer i = (Integer) value;
            return false;
        }
        return false;
    }



}
