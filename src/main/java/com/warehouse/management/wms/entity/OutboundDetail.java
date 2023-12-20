package com.warehouse.management.wms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 出库明细
 * </p>
 *
 * @author gent
 * @since 2023-12-20
 */
@Getter
@Setter
@TableName("outbound_detail")
@ApiModel(value = "OutboundDetail对象", description = "出库明细")
public class OutboundDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;

    @ApiModelProperty("货号")
    private Integer articleNo;

    @ApiModelProperty("物料代码")
    private String materialCode;

    @ApiModelProperty("中文名称")
    private String chineseName;

    @ApiModelProperty("原厂批号")
    private Integer originalBatchNumber;

    @ApiModelProperty("入库批号")
    private Integer warehousingLotNumber;

    @ApiModelProperty("生产日期")
    private LocalDate productionDate;

    @ApiModelProperty("复验日期")
    private LocalDate dateReinspection;

    @ApiModelProperty("有效期")
    private LocalDate validity;

    @ApiModelProperty("单位")
    private String unit;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("仓位")
    private String shippingSpace;

    @ApiModelProperty("库存数量")
    private Integer inventoryQuantity;

    @ApiModelProperty("计划数量")
    private Integer plannedQuantity;

    @ApiModelProperty("出库数量")
    private Integer outboundQuantity;

    @ApiModelProperty("退库数量")
    private String returnQuantity;

    @ApiModelProperty("生产指令")
    private String productionOrder;

    @ApiModelProperty("库房")
    private String storeroom;

    @ApiModelProperty("领料部门")
    private String materialsRequisitionDepartment;

    @ApiModelProperty("操作人")
    private String operator;

    @ApiModelProperty("出库项目号")
    private String outboundItemNumber;

    @ApiModelProperty("出库编号")
    private String outboundNumber;

    @ApiModelProperty("出库日期")
    private LocalDate dateDelivery;

    @ApiModelProperty("出库类别")
    private String outboundCategory;

    @ApiModelProperty("包装规格")
    private String packingSpecification;

    @ApiModelProperty("规格")
    private String specification;
}
