package com.qianzhimu.mgt.base;

import com.qianzhimu.api.utils.RedisUtils;
import com.qianzhimu.api.utils.StringUtils;
import com.qianzhimu.mgt.exception.CommonBizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class BaseOwsController {

    @Autowired
    protected RedisUtils redisUtils;
    public String getTokenFromHeader(HttpServletRequest request) {
        return request.getHeader("X-Login-Token");
    }

    public Long getLoginAccountId(HttpServletRequest request, boolean needThrows) {
        String tokenFromHeader = getTokenFromHeader(request);
        if (StringUtils.isBlank(tokenFromHeader)) {
            throw new CommonBizException(Response.RespCode.NOT_LOGIN);
        }
        // 根据token获取用户
        Long accountId = redisUtils.getCachedAccountId(tokenFromHeader);
        if (accountId == null && needThrows) {
            throw new CommonBizException(Response.RespCode.NOT_LOGIN);
        }

        // token 续期
        if (accountId != null) {
            redisUtils.setOwsToken(tokenFromHeader, accountId, 60 * 60);
        }

        return accountId;
    }
}
