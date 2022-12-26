package com.qianzhimu.ows.controller;

import com.qianzhimu.api.utils.SecurityUtils;
import com.qianzhimu.mgt.annotation.Limit;
import com.qianzhimu.mgt.annotation.Log;
import com.qianzhimu.mgt.aspect.LimitType;
import com.qianzhimu.mgt.base.BaseOwsController;
import com.qianzhimu.mgt.base.Response;
import com.qianzhimu.mgt.config.RsaProperties;
import com.qianzhimu.api.utils.RsaUtils;
import com.qianzhimu.api.utils.VerifyCodeUtils;
import com.qianzhimu.mgt.vo.UserPassVo;
import com.qianzhimu.ows.dto.OwsAccountDTO;
import com.qianzhimu.ows.entity.OwsAccount;
import com.qianzhimu.ows.service.OwsAccountService;
import com.qianzhimu.ows.vo.LoginAccountVO;
import com.qianzhimu.ows.vo.OwsAccountRegisterVO;
import com.qiniu.util.Md5;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.qianzhimu.mgt.base.Response.RespCode.PWD_INCORRECT;

@Api(tags = "官网：用户管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ows/account")
public class AccountController extends BaseOwsController {
    private final OwsAccountService accountService;

    @Log("用户登陆")
    @ApiOperation("用户登陆")
    @PostMapping("/login")
    @Limit(name = "注册接口限流保护", period = 10, count = 1, key = "ows:register", prefix = "limit", limitType = LimitType.IP)
    public Response login(@Validated @RequestBody LoginAccountVO loginAccountVO) throws Exception{
        try {
            // 密码解密
            String password = RsaUtils.decryptByPrivateKey(RsaProperties.privateKey, loginAccountVO.getPassword());

            OwsAccount account = this.accountService.exists(loginAccountVO.getUsername(), md5Pwd(password));
            if (account == null) {
                return Response.FAIL(Response.RespCode.LOGIN_INCORRECT_INFO);
            } else {
                // 生成令牌
                String token = VerifyCodeUtils.generateVerifyCode(32);

                // 保存token 一个小时有效期
                // todo 查看这个用户是否有可以复用的token
                redisUtils.setOwsToken(token, account.getId(), 60 * 60);

                // 返回 token 与 用户信息
                Map<String,Object> authInfo = new HashMap<String,Object>(2){{
                    put("token", token);
                    put("mobile", SecurityUtils.coverPhone(account.getPhone()));
                }};

                return Response.SUCCESS(authInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.FAIL(Response.RespCode.LOGIN_FAIL);
        }
    }

    @PostMapping("/logout")
    @Limit(name = "注册接口限流保护", period = 10, count = 1, key = "ows:register", prefix = "limit", limitType = LimitType.IP)
    public Response logout(HttpServletRequest request) {
        String token = super.getTokenFromHeader(request);
        this.redisUtils.logout(token);
        return Response.SUCCESS();
    }

    @Log("用户注册")
    @ApiOperation("用户注册")
    @PostMapping("/register")
    @Limit(name = "注册接口限流保护", period = 10, count = 2, key = "ows:register", prefix = "limit", limitType = LimitType.IP)
    public Response register(@Validated @RequestBody OwsAccountRegisterVO account) throws Exception {
        try {
            if (this.accountService.findByPhone(account.getPhone()) != null) {
                return Response.FAIL(Response.RespCode.REGISTER_DUPLICATED);
            }
            OwsAccountDTO register = accountService.register(account);
            return Response.SUCCESS(register);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.FAIL(Response.RespCode.SYS_ERROR);
        }
    }

    private String md5Pwd(String origin) {
        return Md5.md5((origin+RsaProperties.md5Key).getBytes());
    }

    @ApiOperation("修改密码")
    @PostMapping(value = "/updatePass")
    @Limit(name = "注册接口限流保护", period = 300, count = 3, key = "ows:register", prefix = "limit", limitType = LimitType.IP)
    public Response updatePass(@Validated @RequestBody UserPassVo passVo, HttpServletRequest request) throws Exception {
        Long accountId = super.getLoginAccountId(request, true);
        // get account
        OwsAccount accountDTO = this.accountService.get(accountId);

        String oldPass = RsaUtils.decryptByPrivateKey(RsaProperties.privateKey,passVo.getOldPass());
        if (!accountDTO.getPassword().equals(md5Pwd(oldPass))) {
            return Response.FAIL(PWD_INCORRECT);
        }

        String newPass = RsaUtils.decryptByPrivateKey(RsaProperties.privateKey,passVo.getNewPass());
        this.accountService.updatePass(accountId, md5Pwd(newPass));

        return Response.SUCCESS();
    }

}

