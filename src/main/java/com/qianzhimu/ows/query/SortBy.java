package com.qianzhimu.ows.query;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SortBy implements Serializable {

    private String direction;
    private String propertyName;
}
