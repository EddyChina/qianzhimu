package com.qianzhimu.api.mapper;

import com.qianzhimu.api.entity.TradeMarker;
import com.qianzhimu.mgt.base.BaseMapper;
import com.qianzhimu.ows.dto.TradeMarkerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TradeMarkerMapper extends BaseMapper<TradeMarkerDTO, TradeMarker> {

}