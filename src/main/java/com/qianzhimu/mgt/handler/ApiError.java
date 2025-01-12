package com.qianzhimu.mgt.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiError {
    private Integer code = 400;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime respTime;
    private String message;

    private ApiError() {
        respTime = LocalDateTime.now();
    }

    public static ApiError error(String message){
        ApiError apiError = new ApiError();
        apiError.setMessage(message);
        return apiError;
    }

    public static ApiError error(Integer code, String message){
        ApiError apiError = new ApiError();
        apiError.setCode(code);
        apiError.setMessage(message);
        return apiError;
    }
}
