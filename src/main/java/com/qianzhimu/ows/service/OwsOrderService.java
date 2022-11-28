package com.qianzhimu.ows.service;

import com.qianzhimu.ows.entity.OwsOrder;
import com.qianzhimu.ows.dto.OwsOrderDto;
import com.qianzhimu.ows.query.OwsOrderQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public interface OwsOrderService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(OwsOrderQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<OwsOrderDto>
    */
    List<OwsOrderDto> queryAll(OwsOrderQueryCriteria criteria);

    /**
    * 根据ID查询
    * @param id ID
    * @return OwsOrderDto
    */
    OwsOrderDto findById(Long id);

    /**
    * 创建
    * @param resources /
    * @return OwsOrderDto
    */
    OwsOrderDto create(OwsOrder resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(OwsOrder resources);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<OwsOrderDto> all, HttpServletResponse response) throws IOException;

    /**
    * 多选删除
    * @param ids /
    */
    void deleteAll(Long[] ids);
}
