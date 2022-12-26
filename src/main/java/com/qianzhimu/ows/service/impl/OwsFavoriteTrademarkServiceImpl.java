package com.qianzhimu.ows.service.impl;

import com.qianzhimu.api.repository.TradeMarkerRepository;
import com.qianzhimu.api.utils.PageUtil;
import com.qianzhimu.api.utils.QueryHelp;
import com.qianzhimu.mgt.base.Response;
import com.qianzhimu.mgt.exception.CommonBizException;
import com.qianzhimu.ows.dto.OwsFavoriteTrademarkDto;
import com.qianzhimu.ows.dto.OwsTradeMarkerDTO;
import com.qianzhimu.ows.entity.OwsFavoriteTrademark;
import com.qianzhimu.ows.mapstruct.OwsFavoriteTrademarkMapper;
import com.qianzhimu.ows.query.OwsFavoriteTrademarkQueryCriteria;
import com.qianzhimu.ows.repository.OwsFavoriteTrademarkRepository;
import com.qianzhimu.ows.service.OwsFavoriteTrademarkService;
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
public class OwsFavoriteTrademarkServiceImpl implements OwsFavoriteTrademarkService {

    private final OwsFavoriteTrademarkRepository owsFavoriteTrademarkRepository;
    private final OwsFavoriteTrademarkMapper owsFavoriteTrademarkMapper;
    private final TradeMarkerService tradeMarkerService;
    private final TradeMarkerRepository tradeMarkerRepository;

    @Override
    public Map<String,Object> queryAll(OwsFavoriteTrademarkQueryCriteria criteria, Pageable pageable){
        Page<OwsFavoriteTrademark> page = owsFavoriteTrademarkRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);

        return PageUtil.toPage(page.map(owsFavoriteTrademark -> {
            OwsTradeMarkerDTO tmInfo = this.tradeMarkerService.getByRegId(owsFavoriteTrademark.getTmRegId());
            tmInfo.setFavorite(true);

            OwsFavoriteTrademarkDto favoriteTrademarkDto = new OwsFavoriteTrademarkDto();

            favoriteTrademarkDto.setId(owsFavoriteTrademark.getId());
            favoriteTrademarkDto.setAccountId(criteria.getAccountId());
            favoriteTrademarkDto.setCreateTime(owsFavoriteTrademark.getCreateTime());
            favoriteTrademarkDto.setTradeMarkerDTO(tmInfo);

            return favoriteTrademarkDto;
        }));
    }

    @Override
    public List<OwsFavoriteTrademarkDto> queryAll(OwsFavoriteTrademarkQueryCriteria criteria){
        return owsFavoriteTrademarkMapper.toDto(owsFavoriteTrademarkRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OwsFavoriteTrademark favorite(OwsFavoriteTrademark resources) {
        // 验证注册号是否存在
        String regId = resources.getTmRegId();
        if (!this.tradeMarkerRepository.existsByRegId(regId)) {
            throw new CommonBizException(Response.RespCode.TRADE_MARK_404);
        }

        // 验证是否重复数据
        if (this.owsFavoriteTrademarkRepository.existsByAccountIdAndTmRegId(resources.getAccountId(), regId)) {
            throw new CommonBizException(Response.RespCode.DATA_DUPLICATE);
        }

        return owsFavoriteTrademarkRepository.save(resources);
    }

    /**
     * @param regId
     * @param accountId
     */
    @Override
    public void unFavorite(String regId, Long accountId) {
        this.owsFavoriteTrademarkRepository.deleteByAccountIdAndTmRegId(accountId, regId);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            owsFavoriteTrademarkRepository.deleteById(id);
        }
    }

    /**
     * @param regId
     * @param accountId
     * @return
     */
    @Override
    public boolean getFavorite(String regId, Long accountId) {
        return this.owsFavoriteTrademarkRepository.existsByAccountIdAndTmRegId(accountId, regId);
    }

}
