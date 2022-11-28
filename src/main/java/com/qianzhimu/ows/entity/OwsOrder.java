package com.qianzhimu.ows.entity;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author fanxiangchi
 * @date 2022-11-25
 */
@Entity
@Data
@Table(name = "ows_order")
public class OwsOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "自增ID")
    private Long id;

    @Column(name = "account_id", nullable = false)
    @ApiModelProperty(value = "账号ID")
    private Long accountId;

    @Column(name = "tm_reg_id", nullable = false)
    @NotBlank
    @ApiModelProperty(value = "商标注册号")
    private String tmRegId;

    @Column(name = "tm_price", nullable = false)
    @NotNull
    @ApiModelProperty(value = "商标标价")
    private Double tmPrice;

    @Column(name = "tm_commission", nullable = false)
    @ApiModelProperty(value = "服务费")
    private Double tmCommission;

    @Column(name = "order_amount", nullable = false)
    @NotNull
    @ApiModelProperty(value = "订单金额")
    private Double orderAmount;

    @Column(name = "payment", nullable = false)
    @ApiModelProperty(value = "支付方式")
    private Integer payment;

    @Column(name = "state", nullable = false)
    @ApiModelProperty(value = "订单状态")
    private Integer state;

    @Column(name = "create_time", nullable = false)
    @CreationTimestamp
    @ApiModelProperty(value = "订单创建时间")
    private Timestamp createTime;

    @Column(name = "update_time", nullable = false)
    @UpdateTimestamp
    @ApiModelProperty(value = "订单修改时间")
    private Timestamp updateTime;

    public void copy(OwsOrder source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
