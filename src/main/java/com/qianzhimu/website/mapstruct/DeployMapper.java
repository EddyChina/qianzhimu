package com.qianzhimu.website.mapstruct;

import com.qianzhimu.website.base.BaseMapper;
import com.qianzhimu.website.dto.DeployDto;
import com.qianzhimu.website.entity.Deploy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author perye
 * @email peryedev@gmail.com
 * @date 2019/12/10
 */
@Mapper(componentModel = "spring",uses = {AppMapper.class, ServerDeployMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeployMapper extends BaseMapper<DeployDto, Deploy> {

}
