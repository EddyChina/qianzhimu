package com.qianzhimu.website.dto;

import com.qianzhimu.website.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class DictDetailDto extends BaseDTO implements Serializable {
    private Long id;

    private DictSmallDto dict;

    // 字典标签
    private String label;

    // 字典值
    private String value;

    private Integer dictSort;

}
