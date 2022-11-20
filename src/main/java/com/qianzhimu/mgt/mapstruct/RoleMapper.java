package com.qianzhimu.mgt.mapstruct;

import com.qianzhimu.mgt.base.BaseMapper;
import com.qianzhimu.mgt.dto.RoleDto;
import com.qianzhimu.mgt.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {MenuMapper.class, DeptMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper extends BaseMapper<RoleDto, Role> {

}
