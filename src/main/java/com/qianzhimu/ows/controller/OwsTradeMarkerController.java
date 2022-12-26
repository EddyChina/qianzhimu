package com.qianzhimu.ows.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.qianzhimu.mgt.base.Response;
import com.qianzhimu.ows.query.SortBy;
import com.qianzhimu.ows.query.TradeMarkerQueryCriteria;
import com.qianzhimu.ows.service.TradeMarkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/ows/tm")
@RequiredArgsConstructor
public class OwsTradeMarkerController {

    private final TradeMarkerService tradeMarkerService;

    @PostMapping("/list")
    public Response query(@RequestBody TradeMarkerQueryCriteria criteria
            , @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        this.checkQueryCriteria(criteria);

        SortBy sortBy = criteria.getSortBy();

        Pageable pageable = PageRequest.of(page, size);
        if (sortBy != null) {
            criteria.setSortBy(null);
            pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortBy.getDirection()), sortBy.getPropertyName()));
        }

        /**
         * 底价5000以上的，最终成交价=底价+底价*20%的服务费+1000元转让费
         * 底价5000以下的，最终成交价=底价+1000服务费+1000元转让费
         */

        return Response.SUCCESS(this.tradeMarkerService.queryAll(criteria, pageable));
    }

    @GetMapping("/r/{regId}")
    public Response query(@PathVariable String regId) {
        return Response.SUCCESS(this.tradeMarkerService.getByRegId(regId));
    }

    private void checkQueryCriteria(TradeMarkerQueryCriteria queryCriteria) {
        Set<Integer> categories = queryCriteria.getCategory();
        if (categories != null && categories.size() > 0) {
            for (Integer category : categories) {
                if (category != null && !(category >= 1 && category <= 45)) {
                    throw new IllegalArgumentException("商标分类错误！");
                }
            }
        }

        if (CollectionUtil.isNotEmpty(queryCriteria.getContentType()) && queryCriteria.getContentType().size() > 8) {
            throw new IllegalArgumentException("商标属性错误！");
        }
    }
}
