package com.qianzhimu.mgt.repository;

import com.qianzhimu.mgt.entity.TmBaseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface TmBaseInfoRepository extends JpaRepository<TmBaseInfo, Long>, JpaSpecificationExecutor<TmBaseInfo> {

     /**
     * 根据 RegId 查询
     * @param reg_id /
     * @return /
     */
    TmBaseInfo findByRegId(String reg_id);
}
