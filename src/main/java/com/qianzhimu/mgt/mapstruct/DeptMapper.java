package com.qianzhimu.mgt.mapstruct;

import com.qianzhimu.mgt.base.BaseMapper;
import com.qianzhimu.mgt.dto.DeptDto;
import com.qianzhimu.mgt.entity.Dept;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeptMapper extends BaseMapper<DeptDto, Dept> {

}