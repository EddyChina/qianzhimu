package com.qianzhimu.ows.mapstruct;

import com.qianzhimu.mgt.base.BaseMapper;
import com.qianzhimu.ows.dto.OwsFavoriteTrademarkDto;
import com.qianzhimu.ows.entity.OwsFavoriteTrademark;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OwsFavoriteTrademarkMapper extends BaseMapper<OwsFavoriteTrademarkDto, OwsFavoriteTrademark> {

}
