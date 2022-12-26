package com.qianzhimu.ows.controller;

import com.qianzhimu.mgt.annotation.Log;
import com.qianzhimu.mgt.base.BaseOwsController;
import com.qianzhimu.mgt.base.Response;
import com.qianzhimu.ows.entity.OwsOrder;
import com.qianzhimu.ows.query.OwsOrderQueryCriteria;
import com.qianzhimu.ows.service.OwsOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequiredArgsConstructor
@Api(tags = "OwsOrderService管理")
@RequestMapping("/ows/order")
public class OwsOrderController extends BaseOwsController {

    private final OwsOrderService owsOrderService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    public void download(HttpServletResponse response, OwsOrderQueryCriteria criteria) throws IOException {
        owsOrderService.download(owsOrderService.queryAll(criteria), response);
    }

    @Log("查询OwsOrderService")
    @ApiOperation("查询OwsOrderService")
    @GetMapping()
    public Response query(OwsOrderQueryCriteria criteria, Pageable pageable, HttpServletRequest request){
        Long loginAccountId = super.getLoginAccountId(request, true);
        criteria.setAccountId(loginAccountId);
        return Response.SUCCESS(owsOrderService.queryAll(criteria,pageable));
    }

    @Log("新增OwsOrderService")
    @ApiOperation("新增OwsOrderService")
    @PostMapping
    public Response create(@Validated @RequestBody OwsOrder resources, HttpServletRequest request){
        Long loginAccountId = super.getLoginAccountId(request, true);
        resources.setAccountId(loginAccountId);
        return Response.SUCCESS(owsOrderService.create(resources));
    }

    @Log("修改OwsOrderService")
    @ApiOperation("修改OwsOrderService")
    @PutMapping
    public Response update(@Validated @RequestBody OwsOrder resources){
        owsOrderService.update(resources);
        return Response.SUCCESS();
    }

    @Log("删除OwsOrderService")
    @ApiOperation("删除OwsOrderService")
    @DeleteMapping
    public Response delete(@RequestBody Long[] ids) {
        owsOrderService.deleteAll(ids);
        return Response.SUCCESS();
    }

}
