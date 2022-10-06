package com.qianzhimu.website.mapstruct;

import com.qianzhimu.website.base.BaseMapper;
import com.qianzhimu.website.dto.DictDetailDto;
import com.qianzhimu.website.entity.DictDetail;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {DictSmallMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DictDetailMapper extends BaseMapper<DictDetailDto, DictDetail> {

}
