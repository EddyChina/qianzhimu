package com.qianzhimu.website.query;

import com.qianzhimu.website.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class MenuQueryCriteria {

    @Query(blurry = "title,component,permission")
    private String blurry;


    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;

    @Query(type = Query.Type.IS_NULL, propName = "pid")
    private Boolean pidIsNull;

    @Query
    private Long pid;
}
