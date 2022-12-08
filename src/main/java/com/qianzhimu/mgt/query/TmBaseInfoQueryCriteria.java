package com.qianzhimu.mgt.query;

import com.qianzhimu.mgt.annotation.Query;
import lombok.Data;
import java.sql.Timestamp;
import java.util.List;


@Data
public class TmBaseInfoQueryCriteria{

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String domain;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String contentType;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String likeGroup;
     /** BETWEEN */
     @Query(type = Query.Type.BETWEEN)
     private List<Timestamp> createTime;

}
