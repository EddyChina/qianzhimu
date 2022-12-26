package com.qianzhimu.api.repository;

import com.qianzhimu.api.entity.OwsTradeMarker;
import com.qianzhimu.api.entity.OwsTradeMarker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TradeMarkerRepository extends JpaRepository<OwsTradeMarker, Long>, JpaSpecificationExecutor<OwsTradeMarker> {
    OwsTradeMarker getByRegId(String regId);

    boolean existsByRegId(String regId);
}
