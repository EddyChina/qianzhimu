package com.qianzhimu.ows.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.qianzhimu.mgt.base.BaseOwsController;
import com.qianzhimu.mgt.base.Response;
import com.qianzhimu.ows.dto.OwsTradeMarkerDTO;
import com.qianzhimu.ows.query.SortBy;
import com.qianzhimu.ows.query.TradeMarkerQueryCriteria;
import com.qianzhimu.ows.service.OwsFavoriteTrademarkService;
import com.qianzhimu.ows.service.TradeMarkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@RestController
@RequestMapping("/ows/tm")
@RequiredArgsConstructor
public class OwsTradeMarkerController extends BaseOwsController {

    private final TradeMarkerService tradeMarkerService;
    private final OwsFavoriteTrademarkService favoriteTrademarkerService;

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
    public Response query(@PathVariable String regId, HttpServletRequest request) {
        OwsTradeMarkerDTO tradeMarkerDTO = this.tradeMarkerService.getByRegId(regId);

        // 查询是否是登陆用户收藏过的商标
        // 获取当前登陆用户id
        Long accountId = super.getLoginAccountId(request, false);
        if (accountId != null) {
            boolean favorite = this.favoriteTrademarkerService.getFavorite(regId, accountId);
            if (favorite) {
                tradeMarkerDTO.setFavorite(true);
            }
        }

        return Response.SUCCESS();
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
