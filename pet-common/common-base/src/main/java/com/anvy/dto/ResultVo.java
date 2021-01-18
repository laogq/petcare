package com.anvy.dto;

import lombok.Data;

@Data
public class ResultVo {

    private Boolean success;
    private int code;
    private String message;
    private Object data;

    private ResultVo() {}

    public static ResultVo success() {
        ResultVo r = new ResultVo();
        r.setCode(ResultCode.SUCCESS);
        r.setSuccess(true);
        r.setMessage("");
        return r;
    }

    public static ResultVo error() {
        ResultVo r = new ResultVo();
        r.setCode(ResultCode.ERROR);
        r.setSuccess(false);
        return r;
    }


    public ResultVo data(Object data){
        this.setData(data);
        return this;
    }

    public ResultVo message (String message){
        this.setMessage(message);
        return this;
    }

}
