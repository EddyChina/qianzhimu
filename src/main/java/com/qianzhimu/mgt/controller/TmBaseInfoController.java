package com.qianzhimu.mgt.controller;

import com.qianzhimu.mgt.annotation.Log;
import com.qianzhimu.mgt.base.Response;
import com.qianzhimu.mgt.entity.TmBaseInfo;
import com.qianzhimu.mgt.query.TmBaseInfoQueryCriteria;
import com.qianzhimu.mgt.service.TmBaseInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequiredArgsConstructor
@Api(tags = "TmBaseInfoService管理")
@RequestMapping("/api/tmBaseInfo")
public class TmBaseInfoController {

    private final TmBaseInfoService tmBaseInfoService;

    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@preAuth.check('tmBaseInfo:list')")
    public void download(HttpServletResponse response, TmBaseInfoQueryCriteria criteria) throws IOException {
        tmBaseInfoService.download(tmBaseInfoService.queryAll(criteria), response);
    }

    @ApiOperation("查询TmBaseInfoService")
    @GetMapping()
    @PreAuthorize("@preAuth.check('tmBaseInfo:list')")
    public Response query(TmBaseInfoQueryCriteria criteria, Pageable pageable){
        return Response.SUCCESS(tmBaseInfoService.queryAll(criteria,pageable));
    }

    @Log("新增TmBaseInfoService")
    @ApiOperation("新增TmBaseInfoService")
    @PostMapping
    @PreAuthorize("@preAuth.check('tmBaseInfo:add')")
    public Response create(@Validated @RequestBody TmBaseInfo resources){
        return Response.SUCCESS(tmBaseInfoService.create(resources));
    }

    @Log("修改TmBaseInfoService")
    @ApiOperation("修改TmBaseInfoService")
    @PutMapping
    @PreAuthorize("@preAuth.check('tmBaseInfo:edit')")
    public Response update(@Validated @RequestBody TmBaseInfo resources){
        tmBaseInfoService.update(resources);
        return Response.SUCCESS();
    }

    @Log("删除TmBaseInfoService")
    @ApiOperation("删除TmBaseInfoService")
    @PreAuthorize("@preAuth.check('tmBaseInfo:del')")
    @DeleteMapping
    public Response delete(@RequestBody Long[] ids) {
        tmBaseInfoService.deleteAll(ids);
        return Response.SUCCESS();
    }

}
