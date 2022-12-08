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
@Table(name="tm_base_info")
public class TmBaseInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
            @ApiModelProperty(value = "主键ID")
        private Long id;

    @Column(name = "reg_id",unique = true,nullable = false)
    @NotBlank
            @ApiModelProperty(value = "注册号")
        private String regId;

    @Column(name = "name",nullable = false)
    @NotBlank
            @ApiModelProperty(value = "商标名称")
        private String name;

    @Column(name = "category",nullable = false)
    @NotNull
            @ApiModelProperty(value = "商标分类")
        private Integer category;

    @Column(name = "domain",nullable = false)
    @NotBlank
            @ApiModelProperty(value = "适用项目")
        private String domain;

    @Column(name = "content_type",nullable = false)
    @NotBlank
            @ApiModelProperty(value = "中英文类型")
        private String contentType;

    @Column(name = "reg_date",nullable = false)
    @NotNull
            @ApiModelProperty(value = "注册日期")
        private Timestamp regDate;

    @Column(name = "valid_date")
            @ApiModelProperty(value = "有效日期")
        private Timestamp validDate;

    @Column(name = "reg_user_name")
            @ApiModelProperty(value = "注册人")
        private String regUserName;

    @Column(name = "reg_user_contact")
            @ApiModelProperty(value = "注册人联系方式")
        private String regUserContact;

    @Column(name = "owner_user_name")
            @ApiModelProperty(value = "持有人")
        private String ownerUserName;

    @Column(name = "owner_contact")
            @ApiModelProperty(value = "持有人联系方式")
        private String ownerContact;

    @Column(name = "owner_holder_cnt")
            @ApiModelProperty(value = "持有人标量")
        private Integer ownerHolderCnt;

    @Column(name = "pic_path",nullable = false)
    @NotBlank
            @ApiModelProperty(value = "商标图片路径")
        private String picPath;

    @Column(name = "tag_price",nullable = false)
    @NotNull
            @ApiModelProperty(value = "标价")
        private Double tagPrice;

    @Column(name = "floor_price")
            @ApiModelProperty(value = "底价")
        private Double floorPrice;

    @Column(name = "length_ranger")
            @ApiModelProperty(value = "商标长度")
        private Integer lengthRanger;

    @Column(name = "state",nullable = false)
    @NotNull
            @ApiModelProperty(value = "上下架状态")
        private Integer state;

    @Column(name = "create_by")
    @UpdateTimestamp
            @ApiModelProperty(value = "创建人")
        private String createBy;

    @Column(name = "update_by")
    @UpdateTimestamp
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

    @Column(name = "like_group")
            @ApiModelProperty(value = "类似群组")
        private String likeGroup;

    public void copy(TmBaseInfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
