package com.qianzhimu.ows.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PriceRanger implements Serializable {
    private Double low;
    private Double high;

}
