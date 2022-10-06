package com.qianzhimu.website.mapstruct;

import com.qianzhimu.website.base.BaseMapper;
import com.qianzhimu.website.dto.UserDto;
import com.qianzhimu.website.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",uses = {RoleMapper.class, DeptMapper.class, JobMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends BaseMapper<UserDto, User> {

}

