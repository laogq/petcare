package com.anvy.petcare.modules.wx.util;

import java.util.UUID;

public class MsgModel {

    /**
     * <xml>
     *   <ToUserName><![CDATA[toUser]]></ToUserName>
     *   <FromUserName><![CDATA[fromUser]]></FromUserName>
     *   <CreateTime>1348831860</CreateTime>
     *   <MsgType><![CDATA[text]]></MsgType>
     *   <Content><![CDATA[this is a test]]></Content>
     *   <MsgId>1234567890123456</MsgId>
     * </xml>
     * @return
     */
    public static String normalMsg (String toUserName,String fromUser){
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        sb.append("<FromUserName><![CDATA["+fromUser+"]]></FromUserName>");
        sb.append("<ToUserName><![CDATA["+toUserName+"]]></ToUserName>");
        sb.append("<CreateTime>"+System.currentTimeMillis()+"</CreateTime>");
        sb.append("<MsgType><![CDATA[text]]></MsgType>");
        sb.append("<Content><![CDATA[love you so much !]]></Content>");
        sb.append("</xml>");
        return sb.toString();
    }

}
