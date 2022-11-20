package com.qianzhimu.ows.query;

import com.qianzhimu.mgt.annotation.Query;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class TradeMarkerQueryCriteria implements Serializable {

    @Query(type = Query.Type.EQUAL)
    private Integer category;

    @Query(propName = "contentType", type = Query.Type.IN)
    private Set<String> contentTypeSet = new HashSet<>(16);

    @Query(propName = "tagPrice", type = Query.Type.BETWEEN)
    private List<Double> tagPrices = new ArrayList<>(2);

    @Query(type = Query.Type.EQUAL)
    private Integer lengthRanger;
}
