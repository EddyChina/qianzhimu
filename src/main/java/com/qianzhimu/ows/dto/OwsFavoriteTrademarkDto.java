package com.qianzhimu.ows.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;


@Data
public class OwsFavoriteTrademarkDto implements Serializable {

    private Long id;
    // 账号ID
    private Long accountId;
    // 商标ID
    private OwsTradeMarkerDTO tradeMarkerDTO;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;
}
