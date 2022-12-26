package com.qianzhimu.ows.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OwsTradeMarkerDTO implements Serializable {

    private String regId;
    private String name;
    private Integer category;
    private String contentType;
    private Double floorPrice;
    private String domain;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date regDate;
    private String picPath;

    private Double commission;

    public String getContentType() {
        /**
         * contentTypeMap["图形"] = 0
         * 	contentTypeMap["中文"] = 2
         * 	contentTypeMap["英文"] = 1
         * 	contentTypeMap["数字"] = 4
         * 	contentTypeMap["中文+英文+数字"] = 7
         * 	contentTypeMap["中文+英文"] = 3
         * 	contentTypeMap["中文+数字"] = 6
         * 	contentTypeMap["英文+数字"] = 5
         */
        switch (contentType) {
            case "0":
                contentType = "图形";
                break;
            case "1":
                contentType = "英文";
                break;
            case "2":
                contentType = "中文";
                break;
            case "3":
                contentType = "中文+英文";
                break;
            case "4":
                contentType = "数字";
                break;
            case "5":
                contentType = "英文+数字";
                break;
            case "6":
                contentType = "中文+数字";
                break;
            case "7":
                contentType = "中文+英文+数字";
                break;

        }
        return contentType;
    }
}
