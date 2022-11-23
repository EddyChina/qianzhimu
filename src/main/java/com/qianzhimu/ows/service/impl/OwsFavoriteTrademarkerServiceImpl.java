package com.qianzhimu.ows.service.impl;

import com.qianzhimu.api.utils.PageUtil;
import com.qianzhimu.api.utils.QueryHelp;
import com.qianzhimu.ows.dto.OwsFavoriteTrademarkerDto;
import com.qianzhimu.ows.entity.OwsFavoriteTrademarker;
import com.qianzhimu.ows.mapstruct.OwsFavoriteTrademarkerMapper;
import com.qianzhimu.ows.query.OwsFavoriteTrademarkerQueryCriteria;
import com.qianzhimu.ows.repository.OwsFavoriteTrademarkerRepository;
import com.qianzhimu.ows.service.OwsAccountService;
import com.qianzhimu.ows.service.OwsFavoriteTrademarkerService;
import com.qianzhimu.ows.service.TradeMarkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OwsFavoriteTrademarkerServiceImpl implements OwsFavoriteTrademarkerService {

    private final OwsFavoriteTrademarkerRepository owsFavoriteTrademarkerRepository;
    private final OwsFavoriteTrademarkerMapper owsFavoriteTrademarkerMapper;
    private final OwsAccountService accountService;
    private final TradeMarkerService tradeMarkerService;

    @Override
    public Map<String,Object> queryAll(OwsFavoriteTrademarkerQueryCriteria criteria, Pageable pageable){
        Page<OwsFavoriteTrademarker> page = owsFavoriteTrademarkerRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(owsFavoriteTrademarkerMapper::toDto));
    }

    @Override
    public List<OwsFavoriteTrademarkerDto> queryAll(OwsFavoriteTrademarkerQueryCriteria criteria){
        return owsFavoriteTrademarkerMapper.toDto(owsFavoriteTrademarkerRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public OwsFavoriteTrademarkerDto create(OwsFavoriteTrademarker resources) {
        return owsFavoriteTrademarkerMapper.toDto(owsFavoriteTrademarkerRepository.save(resources));
    }



    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            owsFavoriteTrademarkerRepository.deleteById(id);
        }
    }

 }
