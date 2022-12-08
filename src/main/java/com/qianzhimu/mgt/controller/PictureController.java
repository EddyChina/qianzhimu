package com.qianzhimu.mgt.controller;

import com.qianzhimu.mgt.annotation.Log;
import com.qianzhimu.mgt.query.PictureQueryCriteria;
import com.qianzhimu.mgt.entity.Picture;
import com.qianzhimu.mgt.service.PictureService;
import com.qianzhimu.api.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/pictures")
@RequiredArgsConstructor
@Api(tags = "工具：免费图床管理")
public class PictureController {

    private final PictureService pictureService;

    @Log("查询图片")
    @PreAuthorize("@preAuthcheck('pictures:list')")
    @GetMapping()
    @ApiOperation("查询图片")
    public ResponseEntity<Object> query(PictureQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(pictureService.queryAll(criteria,pageable), HttpStatus.OK);
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@preAuthcheck('pictures:list')")
    public void download(HttpServletResponse response, PictureQueryCriteria criteria) throws IOException {
        pictureService.download(pictureService.queryAll(criteria), response);
    }

    @Log("上传图片")
    @PreAuthorize("@preAuthcheck('pictures:add')")
    @PostMapping
    @ApiOperation("上传图片")
    public ResponseEntity<Object> upload(@RequestParam MultipartFile file){
        String userName = SecurityUtils.getCurrentUsername();
        Picture picture = pictureService.upload(file,userName);
        return new ResponseEntity<>(picture,HttpStatus.OK);
    }

    @Log("同步图床数据")
    @ApiOperation("同步图床数据")
    @PostMapping(value = "/synchronize")
    public ResponseEntity<Object> synchronize(){
        pictureService.synchronize();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Log("多选删除图片")
    @ApiOperation("多选删除图片")
    @PreAuthorize("@preAuthcheck('pictures:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Long[] ids) {
        pictureService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

