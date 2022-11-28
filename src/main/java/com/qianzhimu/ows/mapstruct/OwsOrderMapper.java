package com.qianzhimu.ows.mapstruct;

import com.qianzhimu.mgt.base.BaseMapper;
import com.qianzhimu.ows.dto.OwsOrderDto;
import com.qianzhimu.ows.entity.OwsOrder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OwsOrderMapper extends BaseMapper<OwsOrderDto, OwsOrder> {

}
