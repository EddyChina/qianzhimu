package com.qianzhimu.mgt.controller;

import com.qianzhimu.api.utils.FileUtil;
import com.qianzhimu.api.utils.StringUtils;
import com.qianzhimu.mgt.base.Response;
import com.qianzhimu.mgt.query.TmBaseInfoQueryCriteria;
import com.qianzhimu.mgt.service.TmBaseInfoService;
import com.qianzhimu.mgt.service.TradeMarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/mgt/tm")
@RequiredArgsConstructor
public class MgtTradeMarkController {
    private final TradeMarkService tradeMarkService;
    private final TmBaseInfoService tmBaseInfoService;

    @PostMapping("/upload")
    public Response upload(@RequestParam MultipartFile file){
        File tempFile = FileUtil.toFile(file);

        this.tradeMarkService.upload(tempFile);

        return Response.SUCCESS();
    }

    @PostMapping("/price/floor/{tmId}")
    public Response updateFloorPrice(@PathVariable Long tmId, @RequestParam double previousPrice, double updatedPrice) {
        this.tmBaseInfoService.updateFloorPrice(tmId, previousPrice, updatedPrice);
        return Response.SUCCESS();
    }

    @PostMapping("/price/tag/{tmId}")
    public Response updateTagPrice(@PathVariable Long tmId, @RequestParam double previousPrice, double updatedPrice) {
        this.tmBaseInfoService.updateTagPrice(tmId, previousPrice, updatedPrice);
        return Response.SUCCESS();
    }

    /**
     * contentType内容类型：
     *  如果选择的是"包含like"： 则筛选项为(可多选)：
     *                              中文(2,3,6,7) x1x (010,011,110,111) --> (value >> 1) & 1 = 1
     *                              英文(1,3,5,7) xx1 (001,101,011,111) --> (value & 1) = 1
     *                              数字(4,5,6,7) 1xx (100 101 110 111) --> (value >> 2) & 1 = 1
     *                              图形(0) xxx (000)
     *  如果选择的是"等于equal"： 则筛选项为(单选)：
     *                              图形-0 (000)
     *                              英文-1 (001)
     *                              中文-2 (010)
     *                              中文+英文-3 (011)
     *                              数字-4 (100)
     *                              数字+英文-5 (101)
     *                              数字+中文-6 (110)
     *                              数字+中文+英文-7 (111)
     * @return 分页查询结果
     */
    public Response query(@RequestBody TmBaseInfoQueryCriteria queryCriteria, @RequestParam  Pageable pageable) {
        // 默认是-1，如果不等于-1 说明被赋值了
        if (StringUtils.trimToEmpty(queryCriteria.getRegId()).length() > 0) {
            queryCriteria = new TmBaseInfoQueryCriteria();
            queryCriteria.setRegId(queryCriteria.getRegId());
        }

        Object stringObjectMap = tmBaseInfoService.queryAll(queryCriteria, pageable);

        return Response.SUCCESS(stringObjectMap);
    }
}
