package com.qianzhimu.mgt.exception;

import com.qianzhimu.mgt.base.Response;
import lombok.Getter;

@Getter
public class CommonBizException extends RuntimeException{
    private Response response;
    private Response.RespCode respCode;

    public CommonBizException(Response response) {
        this.response = response;
    }

    public CommonBizException(Response.RespCode respCode) {
        this.response = Response.FAIL(respCode);
    }

    public CommonBizException(Response.RespCode respCode, String message) {
        this.response = Response.FAIL(respCode);
        response.setMessage(message);
    }
}
