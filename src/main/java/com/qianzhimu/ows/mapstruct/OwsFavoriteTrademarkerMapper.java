package com.qianzhimu.ows.mapstruct;

import com.qianzhimu.mgt.base.BaseMapper;
import com.qianzhimu.ows.dto.OwsFavoriteTrademarkerDto;
import com.qianzhimu.ows.entity.OwsFavoriteTrademarker;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OwsFavoriteTrademarkerMapper extends BaseMapper<OwsFavoriteTrademarkerDto, OwsFavoriteTrademarker> {

}
