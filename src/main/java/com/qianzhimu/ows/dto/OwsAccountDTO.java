package com.qianzhimu.ows.dto;

import com.qianzhimu.mgt.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class OwsAccountDTO extends BaseDTO implements Serializable {

    private Long id;
    private String accountType;
    private String phone;
    private String email;
    private String gender;

}
