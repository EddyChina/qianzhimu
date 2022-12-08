package ${package}.controller;

import com.qianzhimu.mgt.annotation.Log;
import ${package}.entity.${className};
import ${package}.service.${className}Service;
import ${package}.query.${className}QueryCriteria;
import com.qianzhimu.mgt.base.Response;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequiredArgsConstructor
@Api(tags = "${apiAlias}管理")
@RequestMapping("/api/${changeClassName}")
public class ${className}Controller {

    private final ${className}Service ${changeClassName}Service;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@preAuth.check('${changeClassName}:list')")
    public void download(HttpServletResponse response, ${className}QueryCriteria criteria) throws IOException {
        ${changeClassName}Service.download(${changeClassName}Service.queryAll(criteria), response);
    }

    @Log("查询${apiAlias}")
    @ApiOperation("查询${apiAlias}")
    @GetMapping()
    @PreAuthorize("@preAuth.check('${changeClassName}:list')")
    public Response query(${className}QueryCriteria criteria, Pageable pageable){
        return Response.SUCCESS(${changeClassName}Service.queryAll(criteria,pageable));
    }

    @Log("新增${apiAlias}")
    @ApiOperation("新增${apiAlias}")
    @PostMapping
    @PreAuthorize("@preAuth.check('${changeClassName}:add')")
    public Response create(@Validated @RequestBody ${className} resources){
        return Response.SUCCESS(${changeClassName}Service.create(resources));
    }

    @Log("修改${apiAlias}")
    @ApiOperation("修改${apiAlias}")
    @PutMapping
    @PreAuthorize("@preAuth.check('${changeClassName}:edit')")
    public Response update(@Validated @RequestBody ${className} resources){
        ${changeClassName}Service.update(resources);
        return Response.SUCCESS();
    }

    @Log("删除${apiAlias}")
    @ApiOperation("删除${apiAlias}")
    @PreAuthorize("@preAuth.check('${changeClassName}:del')")
    @DeleteMapping
    public Response delete(@RequestBody ${pkColumnType}[] ids) {
        ${changeClassName}Service.deleteAll(ids);
        return Response.SUCCESS();
    }

}
