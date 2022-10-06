package com.qianzhimu.website.dto;

import com.qianzhimu.website.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class LocalStorageDto extends BaseDTO implements Serializable {

    // ID
    private Long id;

    private String realName;

    // 文件名
    private String name;

    // 后缀
    private String suffix;

    // 类型
    private String type;

    // 大小
    private String size;

}
