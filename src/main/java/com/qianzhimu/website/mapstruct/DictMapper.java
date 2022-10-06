package com.qianzhimu.website.mapstruct;

import com.qianzhimu.website.base.BaseMapper;
import com.qianzhimu.website.dto.DictDto;
import com.qianzhimu.website.entity.Dict;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DictMapper extends BaseMapper<DictDto, Dict> {

}
