package com.qianzhimu.ows.service.impl;

import com.qianzhimu.api.utils.FileUtil;
import com.qianzhimu.api.utils.PageUtil;
import com.qianzhimu.api.utils.QueryHelp;
import com.qianzhimu.api.utils.ValidationUtil;
import com.qianzhimu.ows.dto.OwsOrderDto;
import com.qianzhimu.ows.entity.OwsOrder;
import com.qianzhimu.ows.mapstruct.OwsOrderMapper;
import com.qianzhimu.ows.query.OwsOrderQueryCriteria;
import com.qianzhimu.ows.repository.OwsOrderRepository;
import com.qianzhimu.ows.service.OwsOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OwsOrderServiceImpl implements OwsOrderService {

    private final OwsOrderRepository owsOrderRepository;

    private final OwsOrderMapper owsOrderMapper;

    @Override
    public Map<String,Object> queryAll(OwsOrderQueryCriteria criteria, Pageable pageable){
        Page<OwsOrder> page = owsOrderRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(owsOrderMapper::toDto));
    }

    @Override
    public List<OwsOrderDto> queryAll(OwsOrderQueryCriteria criteria){
        return owsOrderMapper.toDto(owsOrderRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public OwsOrderDto findById(Long id) {
        OwsOrder owsOrder = owsOrderRepository.findById(id).orElseGet(OwsOrder::new);
        ValidationUtil.isNull(owsOrder.getId(),"OwsOrder","id",id);
        return owsOrderMapper.toDto(owsOrder);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OwsOrderDto create(OwsOrder resources) {
        return owsOrderMapper.toDto(owsOrderRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(OwsOrder resources) {
        OwsOrder owsOrder = owsOrderRepository.findById(resources.getId()).orElseGet(OwsOrder::new);
        ValidationUtil.isNull( owsOrder.getId(),"OwsOrder","id",resources.getId());
        owsOrder.copy(resources);
        owsOrderRepository.save(owsOrder);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            owsOrderRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<OwsOrderDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (OwsOrderDto owsOrder : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("账号ID", owsOrder.getAccountId());
            map.put("商标注册号", owsOrder.getTmRegId());
            map.put("商标标价", owsOrder.getTmPrice());
            map.put("服务费", owsOrder.getTmCommission());
            map.put("订单金额", owsOrder.getOrderAmount());
            map.put("支付方式", owsOrder.getPayment());
            map.put("订单状态", owsOrder.getState());
            map.put("订单创建时间", owsOrder.getCreateTime());
            map.put("订单修改时间", owsOrder.getUpdateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

 }
