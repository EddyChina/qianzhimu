package com.qianzhimu.ows.query;

import com.qianzhimu.mgt.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class OwsFavoriteTrademarkerQueryCriteria{

    // 精确
    @Query
    private Long accountId;

    @Query(type = Query.Type.INNER_LIKE, propName = "regId", joinName = "tradeMarker")
    private String regId;

     /** BETWEEN */
     @Query(type = Query.Type.BETWEEN)
     private List<Timestamp> createTime;
}
