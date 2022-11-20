package com.qianzhimu.ows.vo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
public class OwsAccountRegisterVO implements Serializable {
    @NotNull(message = "请选择账号类型！")
    private Integer accountType;

    @NotEmpty(message = "手机号将作为登陆账号，请谨慎输入！")
    @Size(min = 11, max = 11, message = "请输入正确的手机号!")
    private String phone;

    @NotEmpty(message = "请设置登陆密码")
    private String password;
}
