package com.qianzhimu.ows.entity;

import com.qianzhimu.api.entity.TradeMarker;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author Eddy
 * @date 2022-11-21
 */
@Entity
@Data
@Table(name = "ows_favorite_trademarker")
public class OwsFavoriteTrademarker implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "PK")
    private Long id;

    @Column(name = "account_id", nullable = false)
    @NotNull
    @ApiModelProperty(value = "账号ID")
    private Long accountId;

    @OneToOne
    @JoinColumn(name = "tm_reg_id", referencedColumnName = "regId")
    private TradeMarker tradeMarker;

    @Column(name = "create_time", nullable = false)
    @NotNull
    @CreationTimestamp
    @ApiModelProperty(value = "收藏时间")
    private Timestamp createTime = Timestamp.valueOf(LocalDateTime.now());

}
