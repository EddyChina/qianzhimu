package com.qianzhimu.ows.query;

import com.qianzhimu.mgt.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class OwsOrderQueryCriteria{

    // 精确
    @Query
    private Long accountId;

    // 精确
    @Query
    private String tmRegId;
     /** BETWEEN */
     @Query(type = Query.Type.BETWEEN)
     private List<Timestamp> createTime;
     /** BETWEEN */
     @Query(type = Query.Type.BETWEEN)
     private List<Timestamp> updateTime;
}
