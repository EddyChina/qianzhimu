package com.qianzhimu.mgt.dto;

import cn.hutool.core.collection.CollectionUtil;
import com.qianzhimu.mgt.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author perye
 * @email peryedev@gmail.com
 * @date 2019/12/10
 */
@Getter
@Setter
public class DeployDto extends BaseDTO implements Serializable {

    /**
     * 部署编号
     */
    private String id;

    private AppDto app;

    /**
     * 服务器
     */
    private Set<ServerDeployDto> deploys;

    private String servers;

    /**
     * 服务状态
     */
    private String status;


    public String getServers() {
        if (CollectionUtil.isNotEmpty(deploys)) {
            return deploys.stream().map(ServerDeployDto::getName).collect(Collectors.joining(","));
        }
        return servers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeployDto deployDto = (DeployDto) o;
        return Objects.equals(id, deployDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
