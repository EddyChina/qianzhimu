package com.qianzhimu.mgt.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 修改密码的 Vo 类
 */
@Data
public class UserPassVo {

    @NotBlank
    private String oldPass;

    @NotBlank
    private String newPass;
}

