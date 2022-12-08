package com.qianzhimu.mgt.controller;

import com.qianzhimu.mgt.annotation.Log;
import com.qianzhimu.mgt.query.LocalStorageQueryCriteria;
import com.qianzhimu.mgt.entity.LocalStorage;
import com.qianzhimu.mgt.service.LocalStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api(tags = "工具：本地存储管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/localStorage")
public class LocalStorageController {

    private final LocalStorageService localStorageService;

    @ApiOperation("查询文件")
    @GetMapping()
    @PreAuthorize("@preAuth.check('storage:list')")
    public ResponseEntity<Object> query(LocalStorageQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(localStorageService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@preAuth.check('storage:list')")
    public void download(HttpServletResponse response, LocalStorageQueryCriteria criteria) throws IOException {
        localStorageService.download(localStorageService.queryAll(criteria), response);
    }

    @ApiOperation("上传文件")
    @PostMapping()
    @PreAuthorize("@preAuth.check('storage:add')")
    public ResponseEntity<Object> create(@RequestParam String name, @RequestParam("file") MultipartFile file){
        localStorageService.create(name, file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation("修改文件")
    @PutMapping()
    @PreAuthorize("@preAuth.check('storage:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody LocalStorage resources){
        localStorageService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("多选删除")
    @DeleteMapping
    @ApiOperation("多选删除")
    public ResponseEntity<Object> delete(@RequestBody Long[] ids) {
        localStorageService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
