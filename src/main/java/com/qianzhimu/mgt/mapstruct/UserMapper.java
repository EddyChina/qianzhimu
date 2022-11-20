package com.qianzhimu.mgt.mapstruct;

import com.qianzhimu.mgt.base.BaseMapper;
import com.qianzhimu.mgt.dto.UserDto;
import com.qianzhimu.mgt.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",uses = {RoleMapper.class, DeptMapper.class, JobMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends BaseMapper<UserDto, User> {

}

