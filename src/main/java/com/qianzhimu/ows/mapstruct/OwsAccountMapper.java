package com.qianzhimu.ows.mapstruct;

import com.qianzhimu.mgt.base.BaseMapper;
import com.qianzhimu.ows.dto.OwsAccountDTO;
import com.qianzhimu.ows.entity.OwsAccount;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OwsAccountMapper extends BaseMapper<OwsAccountDTO, OwsAccount> {
}
