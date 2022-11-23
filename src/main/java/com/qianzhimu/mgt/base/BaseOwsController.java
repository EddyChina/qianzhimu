package com.qianzhimu.mgt.base;

import com.qianzhimu.mgt.exception.CommonBizException;
import com.qianzhimu.api.utils.RedisUtils;
import com.qianzhimu.api.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class BaseOwsController {

    @Autowired
    protected RedisUtils redisUtils;
    public String getTokenFromHeader(HttpServletRequest request) {
        String token = request.getHeader("X-Login-Token");
        return token;
    }

    public Long getLoginAccountId(HttpServletRequest request) {
        String tokenFromHeader = getTokenFromHeader(request);
        if (StringUtils.isBlank(tokenFromHeader)) {
            throw new CommonBizException(Response.RespCode.NOT_LOGIN);
        }
        // 根据token获取用户
        Long accountId = redisUtils.getCachedAccountId(tokenFromHeader);
        if (accountId == null) {
            throw new CommonBizException(Response.RespCode.NOT_LOGIN);
        }
        return accountId;
    }
}
