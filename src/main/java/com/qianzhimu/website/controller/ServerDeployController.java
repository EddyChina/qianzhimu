package com.qianzhimu.website.controller;

import com.qianzhimu.website.annotation.Log;
import com.qianzhimu.website.query.ServerDeployQueryCriteria;
import com.qianzhimu.website.entity.ServerDeploy;
import com.qianzhimu.website.service.ServerDeployService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * @author perye
 * @email peryedev@gmail.com
 * @date 2019/12/10 11:33 下午
 */
@RestController
@Api(tags = "运维：服务器管理")
@RequiredArgsConstructor
@RequestMapping("/api/serverDeploy")
public class ServerDeployController {

    private final ServerDeployService serverDeployService;

    @Log("导出服务器数据")
    @ApiOperation("导出服务器数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@dokit.check('serverDeploy:list')")
    public void download(HttpServletResponse response, ServerDeployQueryCriteria criteria) throws IOException {
        serverDeployService.download(serverDeployService.queryAll(criteria), response);
    }

    @Log("查询服务器")
    @ApiOperation(value = "查询服务器")
    @GetMapping
    @PreAuthorize("@dokit.check('serverDeploy:list')")
    public ResponseEntity<Object> query(ServerDeployQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(serverDeployService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @Log("新增服务器")
    @ApiOperation(value = "新增服务器")
    @PostMapping
    @PreAuthorize("@dokit.check('serverDeploy:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ServerDeploy resources) {
        serverDeployService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Log("修改服务器")
    @ApiOperation(value = "修改服务器")
    @PutMapping
    @PreAuthorize("@dokit.check('serverDeploy:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ServerDeploy resources) {
        serverDeployService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除服务器")
    @ApiOperation(value = "删除服务器")
    @DeleteMapping
    @PreAuthorize("@dokit.check('serverDeploy:del')")
    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids) {
        serverDeployService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Log("测试连接服务器")
    @ApiOperation(value = "测试连接服务器")
    @PostMapping("/testConnect")
    @PreAuthorize("@dokit.check('serverDeploy:add')")
    public ResponseEntity<Object> testConnect(@Validated @RequestBody ServerDeploy resources) {
        return new ResponseEntity<>(serverDeployService.testConnect(resources), HttpStatus.CREATED);
    }
}
