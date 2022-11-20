package com.qianzhimu.api.entity;

import com.qianzhimu.mgt.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 商标基础信息
 */
@Entity
@Getter
@Setter
@Table(name = "tm_base_info")
public class TradeMarker extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    private String regId;

    @NotBlank
    private String name;

    @NotBlank
    private int category;

    @NotBlank
    private String domain;

    @NotBlank
    private String contentType;

    @NotBlank
    private Date regDate;

    @NotBlank
    private Date validDate;

    private String regUserName;
    private String regUserContact;
    private String ownerUserName;
    private String ownerContact;
    private Integer ownerHolderCnt;

    private String picPath;

    private Double tagPrice;
    private Double floorPrice;

    private Integer lengthRanger;
}
