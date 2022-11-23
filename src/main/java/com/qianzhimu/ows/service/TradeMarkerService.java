package com.qianzhimu.ows.service;

import com.qianzhimu.ows.dto.TradeMarkerDTO;
import com.qianzhimu.ows.query.TradeMarkerQueryCriteria;
import org.springframework.data.domain.Pageable;

public interface TradeMarkerService {

    Object queryAll(TradeMarkerQueryCriteria criteria, Pageable pageable);

    TradeMarkerDTO getByRegId(String regId);
}
