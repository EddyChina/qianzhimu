package com.qianzhimu.mgt.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;



@Data
public class TmBaseInfoDto implements Serializable {
    // 主键ID
    private Long id;
    // 注册号
    private String regId;
    // 商标名称
    private String name;
    // 商标分类
    private Integer category;
    // 适用项目
    private String domain;
    // 中英文类型
    private String contentType;
    // 注册日期
    private Timestamp regDate;
    // 有效日期
    private Timestamp validDate;
    // 注册人
    private String regUserName;
    // 注册人联系方式
    private String regUserContact;
    // 持有人
    private String ownerUserName;
    // 持有人联系方式
    private String ownerContact;
    // 持有人标量
    private Integer ownerHolderCnt;
    // 商标图片路径
    private String picPath;
    // 标价
    private Double tagPrice;
    // 底价
    private Double floorPrice;
    // 商标长度
    private Integer lengthRanger;
    // 上下架状态
    private Integer state;
    // 创建人
    private String createBy;
    // 修改人
    private String updateBy;
    // 创建时间
    private Timestamp createTime;
    // 修改时间
    private Timestamp updateTime;
    // 类似群组
    private String likeGroup;
}
