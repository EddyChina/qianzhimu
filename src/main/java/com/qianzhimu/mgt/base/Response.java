package com.qianzhimu.mgt.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class Response implements Serializable {
    private String code;
    private String message;
    private Object data;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date respTime;

    public static Response SUCCESS(Object data){
        Response response = new Response();
        response.setRespCodeEnum(RespCode.SUCCESS);
        response.setData(data);
        return response;
    }

    public static Response SUCCESS(){
        Response response = new Response();
        response.setRespCodeEnum(RespCode.SUCCESS);
        return response;
    }

    public static Response FAIL(RespCode failCodeEnum){
        Response response = new Response();
        response.setRespCodeEnum(failCodeEnum);
        return response;
    }

    public Response() {
        this.respTime = new Date();
    }

    public static Response of(String code, String message, Object data) {
        Response response = new Response();
        response.setCode(code);
        response.setMessage(message);
        response.setData(data);
        return response;
    }

    public static Response of(String code, String message) {
        Response response = new Response();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    public static Response of(int code, String message) {
        Response response = new Response();
        response.setCode(String.valueOf(code));
        response.setMessage(message);
        return response;
    }

    private void setRespCodeEnum(RespCode respCodeEnum) {
        this.setCode(respCodeEnum.code);
        this.setMessage(respCodeEnum.message);
    }


    public enum RespCode {
        SUCCESS("000000", "成功"),

        LOGIN_FAIL("199999", "登陆失败"),
        LOGIN_INCORRECT_INFO("100001", "用户名或密码错误"),
        NOT_LOGIN("100002", "用户需要登陆"),

        REGISTER_DUPLICATED("200001", "用户已注册"),

        PWD_INCORRECT("300001", "原密码不正确"),

        SYS_ERROR("900000", "系统异常"),
        REQUEST_TOO_MUCH("900002", "访问受限"),
        RESOURCE_404("900001", "找不到所需要的资源"),
        TRADE_MARK_404("900002", "商标信息不存在"),
        DATA_DUPLICATE("900003", "重复数据")
        ;

        private final String code;
        private final String message;

        RespCode(String code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}
