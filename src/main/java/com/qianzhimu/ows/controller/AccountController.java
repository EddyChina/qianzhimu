package com.qianzhimu.ows.controller;

import com.qianzhimu.mgt.annotation.Log;
import com.qianzhimu.mgt.config.RsaProperties;
import com.qianzhimu.mgt.exception.EntityNotFoundException;
import com.qianzhimu.mgt.security.TokenProvider;
import com.qianzhimu.mgt.utils.RedisUtils;
import com.qianzhimu.mgt.utils.RsaUtils;
import com.qianzhimu.mgt.utils.VerifyCodeUtils;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "官网：用户管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ows/account")
public class AccountController {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final RedisUtils redisUtils;
    private final OwsAccountService accountService;


    @Log("用户登陆")
    @ApiOperation("用户登陆")
    @PostMapping("/login")
    public ResponseEntity<Object> login(@Validated @RequestBody LoginAccountVO loginAccountVO) throws Exception{
        // 密码解密
        String password = RsaUtils.decryptByPrivateKey(RsaProperties.privateKey, loginAccountVO.getPassword());

        // 验证用户名 密码
        String md5Pwd = Md5.md5((password+RsaProperties.md5Key).getBytes());

        OwsAccount account = this.accountService.exists(loginAccountVO.getUsername(), md5Pwd);
        if (account == null) {
            throw new EntityNotFoundException("用户名或密码不正确");
        } else {
            // 生成令牌
            String token = VerifyCodeUtils.generateVerifyCode(32);

            // 保存token 一个小时有效期
            redisUtils.setOwsToken(token, account.getId(), 60 * 60);

            // 返回 token 与 用户信息
            Map<String,Object> authInfo = new HashMap<String,Object>(2){{
                put("token", token);
            }};
            return ResponseEntity.ok(authInfo);
        }

    }

    @Log("用户注册")
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public ResponseEntity<Object> register(@Validated @RequestBody OwsAccountRegisterVO account) throws Exception {
        OwsAccountDTO register = accountService.register(account);
        return new ResponseEntity<>(register, HttpStatus.OK);
    }



    @ApiOperation("修改密码")
    @PostMapping(value = "/updatePass")
    public ResponseEntity<Object> updatePass(@RequestBody UserPassVo passVo, HttpServletRequest request) throws Exception {
//        String oldPass = RsaUtils.decryptByPrivateKey(RsaProperties.privateKey,passVo.getOldPass());
//
//        String newPass = RsaUtils.decryptByPrivateKey(RsaProperties.privateKey,passVo.getNewPass());
//
//        UserDto user = userService.findByName(SecurityUtils.getCurrentUsername());
//
//        if (!passwordEncoder.matches(oldPass, user.getPassword())) {
//            throw new BadRequestException("修改失败，旧密码错误");
//        }
//        if (passwordEncoder.matches(newPass, user.getPassword())) {
//            throw new BadRequestException("新密码不能与旧密码相同");
//        }
//        userService.updatePass(user.getUsername(), passwordEncoder.encode(newPass));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

