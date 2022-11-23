package com.qianzhimu.api.repository;

import com.qianzhimu.api.entity.TradeMarker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TradeMarkerRepository extends JpaRepository<TradeMarker, Long>, JpaSpecificationExecutor<TradeMarker> {
    TradeMarker getByRegId(String regId);
}
