package com.qianzhimu.ows.dto;

import com.qianzhimu.api.entity.TradeMarker;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;


@Data
public class OwsFavoriteTrademarkerDto implements Serializable {

    private Long id;
    // 账号ID
    private Long accountId;
    // 商标ID
    private TradeMarker tradeMarker;

    private Timestamp createTime;
}
