package com.qianzhimu.mgt.query;

import com.qianzhimu.mgt.annotation.Query;
import lombok.Data;

@Data
public class QuartzJobQueryCriteria {
    @Query(type = Query.Type.INNER_LIKE)
    private String jobName;

    @Query
    private Boolean isSuccess;
}
