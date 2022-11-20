package com.qianzhimu.ows.controller;

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
    public ResponseEntity<Object> query(TradeMarkerQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(this.tradeMarkerService.queryAll(criteria, pageable), HttpStatus.OK);
    }
}
