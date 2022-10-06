package com.qianzhimu.website.mapstruct;

import com.qianzhimu.website.base.BaseMapper;
import com.qianzhimu.website.dto.DeptDto;
import com.qianzhimu.website.entity.Dept;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeptMapper extends BaseMapper<DeptDto, Dept> {

}
