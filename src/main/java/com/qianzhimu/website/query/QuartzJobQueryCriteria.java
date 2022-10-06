package com.qianzhimu.website.query;

import com.qianzhimu.website.annotation.Query;
import lombok.Data;

@Data
public class QuartzJobQueryCriteria {
    @Query(type = Query.Type.INNER_LIKE)
    private String jobName;

    @Query
    private Boolean isSuccess;
}
