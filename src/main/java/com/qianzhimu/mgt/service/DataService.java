package com.qianzhimu.mgt.service;

import com.qianzhimu.mgt.dto.UserDto;

import java.util.List;

/**
 * 数据权限服务类
 */
public interface DataService {

    /**
     * 获取数据权限
     * @param user /
     * @return /
     */
    List<Long> getDeptIds(UserDto user);

}
