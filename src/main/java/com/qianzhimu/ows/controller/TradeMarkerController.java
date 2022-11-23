package com.qianzhimu.ows.controller;

import com.qianzhimu.mgt.base.Response;
import com.qianzhimu.ows.query.TradeMarkerQueryCriteria;
import com.qianzhimu.ows.service.TradeMarkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ows/tm")
@RequiredArgsConstructor
public class TradeMarkerController {

    private final TradeMarkerService tradeMarkerService;

    @GetMapping("/list")
    public Response query(TradeMarkerQueryCriteria criteria, Pageable pageable) {
        return Response.SUCCESS(this.tradeMarkerService.queryAll(criteria, pageable));
    }
}
