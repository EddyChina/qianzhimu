package com.qianzhimu.website.query;

import com.qianzhimu.website.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class RoleQueryCriteria {

    // 多字段模糊
    @Query(blurry = "name,description")
    private String blurry;


    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
