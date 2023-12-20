package com.warehouse.management.wms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 员工表
 * </p>
 *
 * @author gent
 * @since 2023-12-20
 */
@Getter
@Setter
@ApiModel(value = "Staff对象", description = "员工表")
public class Staff implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;

    @ApiModelProperty("名字")
    private String lastname;

    @ApiModelProperty("所属部门id")
    private Integer departmentId;
}
