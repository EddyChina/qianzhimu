package com.qianzhimu.ows.controller;

import com.qianzhimu.api.utils.StringUtils;
import com.qianzhimu.mgt.annotation.Limit;
import com.qianzhimu.mgt.annotation.Log;
import com.qianzhimu.mgt.aspect.LimitType;
import com.qianzhimu.mgt.base.BaseOwsController;
import com.qianzhimu.mgt.base.Response;
import com.qianzhimu.ows.entity.OwsFavoriteTrademark;
import com.qianzhimu.ows.query.OwsFavoriteTrademarkQueryCriteria;
import com.qianzhimu.ows.service.OwsFavoriteTrademarkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequiredArgsConstructor
@Api(tags = "OwsFavoriteTmService管理")
@RequestMapping("/ows/favorite")
public class OwsFavoriteTrademarkController extends BaseOwsController {
    private final OwsFavoriteTrademarkService owsFavoriteTrademarkerService;

    @Log("查询OwsFavoriteTmService")
    @ApiOperation("查询OwsFavoriteTmService")
    @GetMapping()
    @Limit(name = "查询个人收藏", period = 3, count = 1, key = "ows:register", prefix = "limit", limitType = LimitType.IP)
    public Response query(OwsFavoriteTrademarkQueryCriteria criteria, Pageable pageable, HttpServletRequest request){
        // 获取当前登陆用户id
        Long accountId = super.getLoginAccountId(request, true);
        criteria.setAccountId(accountId);
        return Response.SUCCESS(owsFavoriteTrademarkerService.queryAll(criteria,pageable));
    }

    @Log("收藏/取消收藏商标")
    @ApiOperation("收藏/取消收藏商标")
    @PostMapping("/op/{operate}")
    @Limit(name = "收藏/取消收藏商标", period = 1, count = 2, key = "ows:register", prefix = "limit", limitType = LimitType.IP)
    public Response favorite(@Validated @RequestBody OwsFavoriteTrademark resources, @PathVariable int operate, HttpServletRequest request){
        // 获取当前登陆用户id
        Long accountId = super.getLoginAccountId(request, true);
        resources.setAccountId(accountId);

        // 收藏
        if (operate == 1) {
            return Response.SUCCESS(owsFavoriteTrademarkerService.favorite(resources));
        } else if (operate == 0){
            // 取消收藏
            if (resources.getId() != null) {
                owsFavoriteTrademarkerService.deleteAll(new Long[]{resources.getId()});
            } else if (StringUtils.trimToEmpty(resources.getTmRegId()).length() > 0) {
                this.owsFavoriteTrademarkerService.unFavorite(resources.getTmRegId(), accountId);
            }
            return Response.SUCCESS();
        }

        return Response.FAIL(Response.RespCode.RESOURCE_404);
    }


    @Log("删除OwsFavoriteTmService")
    @ApiOperation("删除OwsFavoriteTmService")
    @DeleteMapping
    @Limit(name = "删除收藏的商标", period = 1, count = 3, key = "ows:register", prefix = "limit", limitType = LimitType.IP)
    public Response delete(@RequestBody Long[] ids, HttpServletRequest request) {
        owsFavoriteTrademarkerService.deleteAll(ids);
        return Response.SUCCESS();
    }
}
