package com.qianzhimu.ows.dto;

import com.qianzhimu.mgt.base.BaseDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class TradeMarkerDTO implements Serializable {

    private String regId;
    private String name;
    private Integer category;
    private String contentType;
    private Double tagPrice;
    private String domain;
    private String regDate;
    private String validDate;
    private String picPath;

}