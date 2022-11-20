package com.qianzhimu.mgt.query;

import com.qianzhimu.mgt.annotation.Query;
import lombok.Data;

@Data
public class DictQueryCriteria {

    // 多字段模糊
    @Query(blurry = "name,description")
    private String blurry;
}

