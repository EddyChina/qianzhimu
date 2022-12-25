package com.qianzhimu.ows.query;

import com.qianzhimu.mgt.annotation.Query;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
public class TradeMarkerQueryCriteria implements Serializable {
    // 精确
    @Query(type = Query.Type.IN)
    private Set<Integer> category = null;

    @Query(type = Query.Type.IN)
    private Set<Integer> lengthRange = null;

    // 模糊
    @Query(type = Query.Type.IN)
    private Set<Integer> contentType;

    @Query(type = Query.Type.BETWEEN, propName = "tagPrice")
    private List<Double> price;

    @Query(type = Query.Type.INNER_LIKE)
    private String nameLike;

    private SortBy sortBy;
}
