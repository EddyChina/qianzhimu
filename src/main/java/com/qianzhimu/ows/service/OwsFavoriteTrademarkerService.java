package com.qianzhimu.ows.service;

import com.qianzhimu.ows.dto.OwsFavoriteTrademarkerDto;
import com.qianzhimu.ows.entity.OwsFavoriteTrademarker;
import com.qianzhimu.ows.query.OwsFavoriteTrademarkerQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;


public interface OwsFavoriteTrademarkerService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(OwsFavoriteTrademarkerQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<OwsFavoriteTrademarkerDto>
    */
    List<OwsFavoriteTrademarkerDto> queryAll(OwsFavoriteTrademarkerQueryCriteria criteria);


    /**
    * 创建
    * @param resources /
    * @return OwsFavoriteTrademarkerDto
    */
    OwsFavoriteTrademarkerDto create(OwsFavoriteTrademarker resources);


    /**
    * 多选删除
    * @param ids /
    */
    void deleteAll(Long[] ids);
}
