package com.qianzhimu.mgt.repository;

import com.qianzhimu.mgt.entity.TmBaseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;


public interface TmBaseInfoRepository extends JpaRepository<TmBaseInfo, Long>, JpaSpecificationExecutor<TmBaseInfo> {

     /**
     * 根据 RegId 查询
     * @param reg_id /
     * @return /
     */
    TmBaseInfo findByRegId(String reg_id);

    /**
     * 更新标价
     * @param tmId 商标ID
     * @param prev 更新前价格
     * @param current 更新后价格
     * @return 影响行数
     */
    @Modifying
    @Query(nativeQuery = true, value = "update tm_base_info set tag_price=?3 where id=?1 and tag_price=?2")
    int updateTagPrice(Long tmId, Double prev, Double current);

    /**
     * 更新底价
     * @param tmId 商标ID
     * @param prev 更新前价格
     * @param current 更新后价格
     * @return 影响行数
     */
    @Modifying
    @Query(nativeQuery = true, value = "update tm_base_info set floor_price=?3 where id=?1 and floor_price=?2")
    int updateFloorPrice(Long tmId, Double prev, Double current);

    /**
     * 商标下架
     * @param tmIdSet 商标ID集合
     */
    @Modifying
    @Query(nativeQuery = true, value = "update tm_base_info set state=0 where id in ?1")
    void down(Set<Long> tmIdSet);

    /**
     * 商标上架
     * @param tmIdSet 商标ID集合
     */
    @Modifying
    @Query(nativeQuery = true, value = "update tm_base_info set state=1 where id in ?1")
    void up(Set<Long> tmIdSet);
}
