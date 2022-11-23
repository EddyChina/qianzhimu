package com.qianzhimu.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Date regDate;

    @NotBlank
    @JsonIgnore
    private Date validDate;

    @JsonIgnore
    private String regUserName;

    @JsonIgnore
    private String regUserContact;

    @JsonIgnore
    private String ownerUserName;

    @JsonIgnore
    private String ownerContact;
    private Integer ownerHolderCnt;

    private String picPath;

    @JsonIgnore
    private Double tagPrice;

    @JsonIgnore
    private Double floorPrice;

    private Integer lengthRanger;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TradeMarker that = (TradeMarker) o;

        return regId.equals(that.regId);
    }

    @Override
    public int hashCode() {
        return regId.hashCode();
    }
}
