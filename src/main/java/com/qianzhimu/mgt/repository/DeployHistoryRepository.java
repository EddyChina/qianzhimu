package com.qianzhimu.mgt.repository;

import com.qianzhimu.mgt.entity.DeployHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author perye
 * @email peryedev@gmail.com
 * @date 2019/12/10 11:24 下午
 */
public interface DeployHistoryRepository extends JpaRepository<DeployHistory,String>, JpaSpecificationExecutor<DeployHistory> {
}
