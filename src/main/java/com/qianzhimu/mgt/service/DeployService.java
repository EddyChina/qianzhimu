package com.qianzhimu.mgt.service;

import com.qianzhimu.mgt.dto.DeployDto;
import com.qianzhimu.mgt.query.DeployQueryCriteria;
import com.qianzhimu.mgt.entity.Deploy;
import com.qianzhimu.mgt.entity.DeployHistory;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @author perye
 * @email peryedev@gmail.com
 * @date 2019/12/10
 */
public interface DeployService {

    /**
     * 分页查询
     * @param criteria 条件
     * @param pageable 分页参数
     * @return /
     */
    Object queryAll(DeployQueryCriteria criteria, Pageable pageable);

    /**
     * 查询全部数据
     * @param criteria 条件
     * @return /
     */
    List<DeployDto> queryAll(DeployQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id /
     * @return /
     */
    DeployDto findById(Long id);

    /**
     * 创建
     * @param resources /
     */
    void create(Deploy resources);

    /**
     * 编辑
     * @param resources /
     */
    void update(Deploy resources);

    /**
     * 删除
     * @param ids /
     */
    void delete(Set<Long> ids);

    void deploy(String fileSavePath, Long appId);

    String serverStatus(Deploy resources);

    String startServer(Deploy resources);

    String stopServer(Deploy resources);

    String serverReduction(DeployHistory resources);

    /**
     * 导出数据
     * @param queryAll /
     * @param response /
     */
    void download(List<DeployDto> queryAll, HttpServletResponse response) throws IOException;
}
