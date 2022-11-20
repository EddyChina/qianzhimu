package com.qianzhimu.mgt.mapstruct;

import com.qianzhimu.mgt.base.BaseMapper;
import com.qianzhimu.mgt.dto.AppDto;
import com.qianzhimu.mgt.entity.App;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author perye
 * @email peryedev@gmail.com
 * @date 2019/12/10
 */
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AppMapper extends BaseMapper<AppDto, App> {

}
