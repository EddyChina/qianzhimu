package com.qianzhimu.ows.service.impl;

import com.qianzhimu.api.entity.TradeMarker;
import com.qianzhimu.api.mapper.TradeMarkerMapper;
import com.qianzhimu.api.repository.TradeMarkerRepository;
import com.qianzhimu.api.utils.PageUtil;
import com.qianzhimu.api.utils.QueryHelp;
import com.qianzhimu.ows.dto.TradeMarkerDTO;
import com.qianzhimu.ows.query.TradeMarkerQueryCriteria;
import com.qianzhimu.ows.service.TradeMarkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TradeMarkerServiceImpl implements TradeMarkerService {

    private final TradeMarkerRepository tradeMarkerRepository;

    private final TradeMarkerMapper tradeMarkerMapper;

    @Override
    public Object queryAll(TradeMarkerQueryCriteria criteria, Pageable pageable) {
        Page<TradeMarker> all = this.tradeMarkerRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);

        return PageUtil.toPage(all.map(tradeMarkerMapper::toDto));
    }

    @Override
    public TradeMarkerDTO getByRegId(String regId) {
        return tradeMarkerMapper.toDto(this.tradeMarkerRepository.getByRegId(regId));
    }
}
