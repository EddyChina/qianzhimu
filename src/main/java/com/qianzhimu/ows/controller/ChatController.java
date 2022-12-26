package com.qianzhimu.ows.controller;

import com.qianzhimu.api.utils.RedisUtils;
import com.qianzhimu.mgt.base.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ows/cs")
@RequiredArgsConstructor
public class ChatController {
    private final RedisUtils redisUtils;

    @GetMapping("/one")
    public Response getOne() {
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("csId", "kefu2");
        return Response.SUCCESS(dataMap);
    }
}
