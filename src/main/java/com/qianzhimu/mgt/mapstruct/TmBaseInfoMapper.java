package com.qianzhimu.mgt.mapstruct;

import com.qianzhimu.mgt.base.BaseMapper;
import com.qianzhimu.mgt.entity.TmBaseInfo;
import com.qianzhimu.mgt.dto.TmBaseInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TmBaseInfoMapper extends BaseMapper<TmBaseInfoDto, TmBaseInfo> {

}
