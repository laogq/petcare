package com.anvy.petcare.modules.wx;

import com.alibaba.fastjson.JSONObject;
import com.anvy.petcare.config.ApplicationContextRegister;
import com.anvy.petcare.modules.wx.util.MsgModel;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

@Slf4j
@RestController
public class WxConfig {

    @Resource
    private RestTemplate restTemplate;

    @Value("${wx.secretKey}")
    private String secretKey;

    @Value("${wx.access_token_url}")
    private String tokenUrl;

    private static JSONObject accessTokenJson = new JSONObject();

    private static String accessToken;

    @RequestMapping("wxConfig")
    public String verifyConfig(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info(" PARAM VAL: >>>signature" + request.getParameter("signature"));
        log.info(" PARAM VAL: >>>timestamp" + request.getParameter("timestamp"));
        log.info(" PARAM VAL: >>>nonce" + request.getParameter("nonce"));
        log.info(" PARAM VAL: >>>echostr" + request.getParameter("echostr"));
        String echostr = request.getParameter("echostr");
        ServletInputStream inputStream = request.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
        String msg = "";
        String str = "";
        while((str = reader.readLine()) != null){
            msg += str;
        }
        String openid = request.getParameter("openid");
        log.info("用户OPENID：{}",openid);
        log.info("用户发送的消息：{}",msg);
        String anvy = MsgModel.normalMsg(openid,"gh_eb62f9fe0cb6");
        log.info("返回用户的消息：{}",anvy);

        return anvy;
    }



    @RequestMapping("sendMsg")
    public void sendMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject object = new JSONObject();
        object.put("touser","o18sY6-KFL2Wx7SVjM5XjdR6dElg");
        object.put("msgtype","text");
        JSONObject obj = new JSONObject();
        obj.put("content","abc");
        object.put("text",obj);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JSONObject> entity = new HttpEntity<>(object,header);
        addKf();
        log.info("post url====={}","https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken);
        ResponseEntity<JSONObject> result = restTemplate.postForEntity("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+accessToken, entity, JSONObject.class);
        JSONObject body = result.getBody();
        log.info("send Msg::{}",body);

    }

    private void addKf(){
        validToken();
        String url = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token="+accessToken;
        JSONObject data = new JSONObject();
        data.put("kf_account","test1@test");
        data.put("nickname","小小客服");
        data.put("password","pswmd5");
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JSONObject> entity = new HttpEntity<>(header);
        ResponseEntity<JSONObject> json = restTemplate.postForEntity(url, entity, JSONObject.class);
        log.info("添加客服******************{}",json);
    }

/*
    public String getAccessToken(String accessToken) {
        accessToken = accessToken;
        if (accessToken == null) {
            return getToken();
        } else {
            getAccessTokenTime = Long.parseLong(configurationService.get("getAccessTokenTime"));
            long totalSeconds = (System.currentTimeMillis() - getAccessTokenTime) / 1000;
            expires_in = Integer.parseInt(configurationService.get("expires_in"));
            if (totalSeconds > expires_in) {
                return doAccessToken(configurationService);
            } else {
                return accessToken;
            }
        }
    }*/

    private void validToken(){
        String access_token = accessTokenJson.getString("access_token");
        if(null == access_token){
            accessToken = getToken();
        }
        if(null != access_token){
            Long startTime = accessTokenJson.getLong("start_time");
            long temp = System.currentTimeMillis() - startTime;
            Long expiresIn = accessTokenJson.getLong("expires_in");
            if(temp > expiresIn){
                accessToken = getToken();
            }
        }
    }
    private String getToken() {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<JSONObject> forEntity = restTemplate.getForEntity(tokenUrl, JSONObject.class);
        JSONObject body = forEntity.getBody();
        log.info("********************{}********************",body);
        accessTokenJson.putAll(body);
        accessTokenJson.put("start_time",System.currentTimeMillis());
        return body.getString("access_token");
    }
}
