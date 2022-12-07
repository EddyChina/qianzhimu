package com.qianzhimu.mgt.controller;

import com.qianzhimu.api.utils.FileUtil;
import com.qianzhimu.mgt.base.Response;
import com.qianzhimu.mgt.service.TradeMarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/mgt/tm")
@RequiredArgsConstructor
public class TradeMarkController {

    private final TradeMarkService tradeMarkService;

    @PostMapping("/upload")
    public Response upload(@RequestParam MultipartFile file){
        File tempFile = FileUtil.toFile(file);

        this.tradeMarkService.upload(tempFile);

        return Response.SUCCESS();
    }
}
