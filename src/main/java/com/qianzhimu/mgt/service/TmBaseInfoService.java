package com.qianzhimu.mgt.service;

import com.qianzhimu.mgt.entity.TmBaseInfo;
import com.qianzhimu.mgt.dto.TmBaseInfoDto;
import com.qianzhimu.mgt.query.TmBaseInfoQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public interface TmBaseInfoService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(TmBaseInfoQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<TmBaseInfoDto>
    */
    List<TmBaseInfoDto> queryAll(TmBaseInfoQueryCriteria criteria);

    /**
    * 根据ID查询
    * @param id ID
    * @return TmBaseInfoDto
    */
    TmBaseInfoDto findById(Long id);

    /**
    * 创建
    * @param resources /
    * @return TmBaseInfoDto
    */
    TmBaseInfoDto create(TmBaseInfo resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(TmBaseInfo resources);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<TmBaseInfoDto> all, HttpServletResponse response) throws IOException;

    /**
    * 多选删除
    * @param ids /
    */
    void deleteAll(Long[] ids);
}
