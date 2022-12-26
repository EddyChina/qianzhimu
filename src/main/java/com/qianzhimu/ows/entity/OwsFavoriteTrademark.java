package com.qianzhimu.ows.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Eddy
 * @date 2022-11-21
 */
@Entity
@Getter
@Setter
@Table(name = "ows_favorite_trademarker")
public class OwsFavoriteTrademark implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "PK")
    private Long id;

    @ApiModelProperty(value = "账号ID")
    private Long accountId;

    @NotNull
    private String tmRegId;

    @ApiModelProperty(value = "收藏时间")
    @Column(insertable = false)
    private Timestamp createTime;

}
