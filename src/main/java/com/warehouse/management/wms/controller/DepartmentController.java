package com.warehouse.management.wms.controller;

import com.warehouse.management.wms.comment.Result;
import com.warehouse.management.wms.entity.Department;
import com.warehouse.management.wms.service.impl.DepartmentServiceImpl;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author gent
 * @since 2023-12-20
 */
@RestController
@RequestMapping("/admin/department")
public class DepartmentController {

    @Resource
    private DepartmentServiceImpl departmentService;

    @GetMapping("/list")
    public Result list(){
        List<Department> list = departmentService.list();
        return Result.ok().data("list",list).message("获取列表成功");
    }

    @Tag(name = "新增部门")
    @GetMapping("/save")
    @Operation(summary = "新增部门", description = "新增部门")
    public Result save(@ApiParam(value = "部门对象") @RequestBody Department department) {
        boolean result = departmentService.save(department);
        if (result) {
            return Result.ok().message("保存成功");
        } else {
            return Result.error().message("保存失败");
        }
    }


}
