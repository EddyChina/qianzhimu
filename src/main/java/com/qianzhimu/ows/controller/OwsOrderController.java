package com.qianzhimu.ows.controller;

import com.qianzhimu.mgt.annotation.Log;
import com.qianzhimu.ows.entity.OwsOrder;
import com.qianzhimu.ows.query.OwsOrderQueryCriteria;
import com.qianzhimu.ows.service.OwsOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequiredArgsConstructor
@Api(tags = "OwsOrderService管理")
@RequestMapping("/ows/order")
public class OwsOrderController {

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
    public ResponseEntity<Object> query(OwsOrderQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(owsOrderService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增OwsOrderService")
    @ApiOperation("新增OwsOrderService")
    @PostMapping
    public ResponseEntity<Object> create(@Validated @RequestBody OwsOrder resources){
        return new ResponseEntity<>(owsOrderService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改OwsOrderService")
    @ApiOperation("修改OwsOrderService")
    @PutMapping
    public ResponseEntity<Object> update(@Validated @RequestBody OwsOrder resources){
    owsOrderService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除OwsOrderService")
    @ApiOperation("删除OwsOrderService")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Long[] ids) {
    owsOrderService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
