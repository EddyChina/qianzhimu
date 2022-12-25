package com.qianzhimu.mgt.query;

import com.qianzhimu.mgt.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;


@Data
public class TmBaseInfoQueryCriteria{

    // 商标注册号，如果有注册号，忽略其他条件
    @Query(type = Query.Type.EQUAL)
    private String regId;

    @Query(type = Query.Type.EQUAL)
    private String name;

    @Query(type = Query.Type.INNER_LIKE)
    private String nameLike;

    @Query(type = Query.Type.EQUAL)
    private String regUserName;

    @Query(type = Query.Type.INNER_LIKE, propName = "regUserName")
    private String regUserNameLike;

    @Query(type = Query.Type.EQUAL)
    private String ownerUserName;

    @Query(type = Query.Type.INNER_LIKE, propName = "ownerUserName")
    private String ownerUserNameLike;

    // 精确
    @Query(type = Query.Type.EQUAL)
    private Integer category = null;

    @Query(type = Query.Type.EQUAL)
    private Integer lengthRange = null;

    // 模糊
    @Query(type = Query.Type.IN)
    private Set<Integer> contentType;

    // 模糊
    @Query(type = Query.Type.IN)
    private String likeGroup;

    /** BETWEEN */
     @Query(type = Query.Type.BETWEEN)
     private List<Timestamp> regDate;

    @Query(type = Query.Type.BETWEEN)
    private List<Double> floorPrice;

    @Query(type = Query.Type.BETWEEN)
    private List<Double> tagPrice;
}
