package com.qianzhimu.ows.service;

import com.qianzhimu.ows.dto.OwsFavoriteTrademarkDto;
import com.qianzhimu.ows.entity.OwsFavoriteTrademark;
import com.qianzhimu.ows.query.OwsFavoriteTrademarkQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;


public interface OwsFavoriteTrademarkService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(OwsFavoriteTrademarkQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<OwsFavoriteTrademarkerDto>
    */
    List<OwsFavoriteTrademarkDto> queryAll(OwsFavoriteTrademarkQueryCriteria criteria);


    /**
    * 创建
    * @param resources /
    * @return OwsFavoriteTrademarkerDto
    */
    OwsFavoriteTrademark favorite(OwsFavoriteTrademark resources);

    void unFavorite(String regId, Long accountId);

    /**
    * 多选删除
    * @param ids /
    */
    void deleteAll(Long[] ids);

    boolean getFavorite(String regId, Long accountId);
}
