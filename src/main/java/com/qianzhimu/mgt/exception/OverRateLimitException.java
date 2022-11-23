package com.qianzhimu.mgt.exception;

import com.qianzhimu.mgt.base.Response;
import lombok.Getter;

@Getter
public class OverRateLimitException extends RuntimeException{
    private Response response;
    private Response.RespCode respCode;

    public OverRateLimitException(Response response) {
        this.response = response;
    }

    public OverRateLimitException(Response.RespCode respCode) {
        this.response = Response.FAIL(respCode);
    }
}
