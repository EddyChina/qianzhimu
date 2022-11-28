package com.qianzhimu.ows.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;



@Data
public class OwsOrderDto implements Serializable {
    // 自增ID
    private Long id;

    // 商标注册号
    private String tmRegId;
    // 商标标价
    private Double tmPrice;

    // 订单金额
    private Double orderAmount;
    // 支付方式
    private Integer payment;
    // 订单状态
    private Integer state;
    // 订单创建时间
    private Timestamp createTime;
    // 订单修改时间
    private Timestamp updateTime;
}
