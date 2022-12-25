package com.qianzhimu.mgt.entity;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.copier.CopyOptions;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;

import java.sql.Timestamp;
import java.io.Serializable;

/**
 * @author xiangchi
 * @date 2022-12-08
 */
@Entity
@Data
@Table(name = "tm_base_info")
public class TmBaseInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "主键ID")
    private Long id;

    @NotBlank
    @ApiModelProperty(value = "注册号")
    private String regId;

    @NotBlank
    @ApiModelProperty(value = "商标名称")
    private String name;

    @NotNull
    @ApiModelProperty(value = "商标分类")
    private Integer category;

    @NotBlank
    @ApiModelProperty(value = "适用项目")
    private String domain;

    @NotNull
    @ApiModelProperty(value = "中英文类型")
    private Integer contentType;

    @NotNull
    @ApiModelProperty(value = "注册日期")
    private Timestamp regDate;

    @ApiModelProperty(value = "有效日期")
    private Timestamp validDate;

    @ApiModelProperty(value = "注册人")
    private String regUserName;

    @ApiModelProperty(value = "注册人联系方式")
    private String regUserContact;

    @ApiModelProperty(value = "持有人")
    private String ownerUserName;

    @ApiModelProperty(value = "持有人联系方式")
    private String ownerContact;

    @ApiModelProperty(value = "持有人标量")
    private Integer ownerHolderCnt;

    @NotBlank
    @ApiModelProperty(value = "商标图片路径")
    private String picPath;

    @NotNull
    @ApiModelProperty(value = "标价")
    private Double tagPrice;

    @ApiModelProperty(value = "底价")
    private Double floorPrice;

    @ApiModelProperty(value = "商标长度")
    private Integer lengthRanger;

    @NotNull
    @ApiModelProperty(value = "上下架状态")
    private Integer state;

    @Column(name = "create_by")
    @ApiModelProperty(value = "创建人")
    private String createBy;

    @Column(name = "update_by")
    @ApiModelProperty(value = "修改人")
    private String updateBy;

    @Column(name = "create_time")
    @CreationTimestamp
    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;

    @Column(name = "update_time")
    @UpdateTimestamp
    @ApiModelProperty(value = "修改时间")
    private Timestamp updateTime;

    @ApiModelProperty(value = "类似群组")
    private String likeGroup;

    public void copy(TmBaseInfo source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
