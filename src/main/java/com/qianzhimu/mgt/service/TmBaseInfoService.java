package com.qianzhimu.mgt.service;

import com.qianzhimu.mgt.dto.TmBaseInfoDto;
import com.qianzhimu.mgt.entity.TmBaseInfo;
import com.qianzhimu.mgt.query.TmBaseInfoQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;


public interface TmBaseInfoService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Object queryAll(TmBaseInfoQueryCriteria criteria, Pageable pageable);

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

    /**
     * 更新标价
     * @param tmId 商标ID
     * @param prev 更新前价格
     * @param current 更新后价格
     * @return 影响行数
     */
    int updateTagPrice(Long tmId, Double prev, Double current);

    /**
     * 更新底价
     * @param tmId 商标ID
     * @param prev 更新前价格
     * @param current 更新后价格
     * @return 影响行数
     */
    int updateFloorPrice(Long tmId, Double prev, Double current);

    /**
     * 商标下架
     * @param tmIdSet 商标ID集合
     */
    void down(Set<Long> tmIdSet);

    /**
     * 商标上架
     * @param tmIdSet 商标ID集合
     */
    void up(Set<Long> tmIdSet);
}
