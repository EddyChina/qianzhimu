package ${package}.mapstruct;

import base.com.qianzhimu.mgt.BaseMapper;
import ${package}.entity.${className};
import ${package}.dto.${className}Dto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ${className}Mapper extends BaseMapper<${className}Dto, ${className}> {

}
