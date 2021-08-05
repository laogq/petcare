package com.anvy.petcare.constant;

/**
* @Description: 常量类
* @author Anvy Lao
* @date 2020/5/6 10:46
*/
public class Constant {

    public static final int CODE_SUCCESS = 0;
    public static final int CODE_FAIL = 9999;
    public static final String INFO_SUCCESS = "操作成功！";
    public static final String LOGIN_FAIL = "操作失败！";
    public static final int CODE_PARAM_ERROR = 501;
    public static final String INFO_PARAM_ERROR = "参数错误！";

    public static class stateType {
        public static final Integer YES = 1;
        public static final Integer NO = 0;
    }


    public static void main(String[] args) {
        try {
            System.out.println(1/0);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
