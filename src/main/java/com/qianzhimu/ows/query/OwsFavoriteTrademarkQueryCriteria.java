package com.qianzhimu.ows.query;

import com.qianzhimu.mgt.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class OwsFavoriteTrademarkQueryCriteria {

    // 精确
    @Query
    private Long accountId;

    @Query(type = Query.Type.EQUAL, propName = "regId", joinName = "tradeMarker")
    private String regId;

     /** BETWEEN */
     @Query(type = Query.Type.BETWEEN)
     private List<Timestamp> createTime;
}
