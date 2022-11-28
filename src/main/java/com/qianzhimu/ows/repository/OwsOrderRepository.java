package com.qianzhimu.ows.repository;

import com.qianzhimu.ows.entity.OwsOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface OwsOrderRepository extends JpaRepository<OwsOrder, Long>, JpaSpecificationExecutor<OwsOrder> {
}
