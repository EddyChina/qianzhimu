package com.qianzhimu.ows.controller;

import com.qianzhimu.mgt.annotation.Limit;
import com.qianzhimu.mgt.annotation.Log;
import com.qianzhimu.mgt.aspect.LimitType;
import com.qianzhimu.mgt.base.BaseOwsController;
import com.qianzhimu.mgt.base.Response;
import com.qianzhimu.ows.entity.OwsFavoriteTrademarker;
import com.qianzhimu.ows.query.OwsFavoriteTrademarkerQueryCriteria;
import com.qianzhimu.ows.service.OwsFavoriteTrademarkerService;
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
public class OwsFavoriteTrademarkerController extends BaseOwsController {
    private final OwsFavoriteTrademarkerService owsFavoriteTrademarkerService;

    @Log("查询OwsFavoriteTmService")
    @ApiOperation("查询OwsFavoriteTmService")
    @GetMapping()
    @Limit(name = "注册接口限流保护", period = 3, count = 1, key = "ows:register", prefix = "limit", limitType = LimitType.IP)
    public Response query(OwsFavoriteTrademarkerQueryCriteria criteria, Pageable pageable, HttpServletRequest request){
        // 获取当前登陆用户id
        Long accountId = super.getLoginAccountId(request);
        criteria.setAccountId(accountId);
        return Response.SUCCESS(owsFavoriteTrademarkerService.queryAll(criteria,pageable));
    }

    @Log("新增OwsFavoriteTmService")
    @ApiOperation("新增OwsFavoriteTmService")
    @PostMapping
    @Limit(name = "注册接口限流保护", period = 1, count = 1, key = "ows:register", prefix = "limit", limitType = LimitType.IP)
    public Response create(@Validated @RequestBody OwsFavoriteTrademarker resources, HttpServletRequest request){
        // 获取当前登陆用户id
        Long accountId = super.getLoginAccountId(request);
        resources.setAccountId(accountId);
        return Response.SUCCESS(owsFavoriteTrademarkerService.create(resources));
    }


    @Log("删除OwsFavoriteTmService")
    @ApiOperation("删除OwsFavoriteTmService")
    @DeleteMapping
    @Limit(name = "注册接口限流保护", period = 1, count = 3, key = "ows:register", prefix = "limit", limitType = LimitType.IP)
    public Response delete(@RequestBody Long[] ids, HttpServletRequest request) {
        owsFavoriteTrademarkerService.deleteAll(ids);
        return Response.SUCCESS();
    }
}
