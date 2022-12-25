package com.qianzhimu.mgt.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.qianzhimu.api.utils.StringUtils;
import com.qianzhimu.mgt.annotation.Log;
import com.qianzhimu.mgt.base.Response;
import com.qianzhimu.mgt.entity.TmBaseInfo;
import com.qianzhimu.mgt.query.TmBaseInfoQueryCriteria;
import com.qianzhimu.mgt.service.TmBaseInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


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
    public Response query(@RequestBody TmBaseInfoQueryCriteria queryCriteria
            , @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size){

        this.checkQueryCriteria(queryCriteria);

        // 默认是-1，如果不等于-1 说明被赋值了
        String regId = StringUtils.trimToEmpty(queryCriteria.getRegId());
        if (regId.length() > 0) {
            queryCriteria = new TmBaseInfoQueryCriteria();
            queryCriteria.setRegId(regId);
        }

        Pageable pageable = PageRequest.of(page, size);

        Object data = tmBaseInfoService.queryAll(queryCriteria, pageable);

        return Response.SUCCESS(data);
    }

    /**
     * 检测请求参数有效性
     * @param queryCriteria 页面请求参数
     */
    private void checkQueryCriteria(TmBaseInfoQueryCriteria queryCriteria) {
        // 姓名只能有一次
        boolean nameConflict = (StringUtils.trimToEmpty(queryCriteria.getName()).length() > 0)
                && (StringUtils.trimToEmpty(queryCriteria.getNameLike()).length() > 0);

        boolean regNameConflict = (StringUtils.trimToEmpty(queryCriteria.getRegUserName()).length() > 0)
                && (StringUtils.trimToEmpty(queryCriteria.getRegUserNameLike()).length() > 0);

        boolean ownNameConflict = (StringUtils.trimToEmpty(queryCriteria.getOwnerUserName()).length() > 0)
                && (StringUtils.trimToEmpty(queryCriteria.getOwnerUserNameLike()).length() > 0);

        boolean priceConflict = !CollectionUtils.isEmpty(queryCriteria.getFloorPrice())
                && !CollectionUtils.isEmpty(queryCriteria.getTagPrice());

        if (nameConflict || regNameConflict || ownNameConflict || priceConflict) {
            throw new IllegalArgumentException("请求参数有冲突，请检查！");
        }

        Integer category = queryCriteria.getCategory();
        if (category != null && !(category >= 1 && category <= 45)) {
            throw new IllegalArgumentException("商标分类错误！");
        }

        if (CollectionUtil.isNotEmpty(queryCriteria.getContentType()) && queryCriteria.getContentType().size() > 8) {
            throw new IllegalArgumentException("商标属性错误！");
        }
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

}
