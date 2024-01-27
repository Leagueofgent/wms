package com.warehouse.management.wms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serial;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author gent
 * @since 2024-01-21
 */
@Getter
@Setter
@ApiModel(value = "Permission对象", description = "权限表")
public class Permission implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;

    private String name;

    private String url;

    private Integer parentId;

    private String type;

    private String permit;

    private String remark;
}
