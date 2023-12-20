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
 * 库存仓位台账
 * </p>
 *
 * @author gent
 * @since 2023-12-20
 */
@Getter
@Setter
@TableName("inventory_position_ledger")
@ApiModel(value = "InventoryPositionLedger对象", description = "库存仓位台账")
public class InventoryPositionLedger implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;

    @ApiModelProperty("库房")
    private String storeroom;

    @ApiModelProperty("仓位")
    private String shippingSpace;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("货号")
    private Integer articleNo;

    @ApiModelProperty("物料代码")
    private String materialCode;

    @ApiModelProperty("入库批号")
    private Integer warehousingLotNumber;

    @ApiModelProperty("原厂批号")
    private Integer originalBatchNumber;

    @ApiModelProperty("库存数量")
    private String inventoryQuantity;

    @ApiModelProperty("单位")
    private String unit;

    @ApiModelProperty("中文名称")
    private String chineseName;

    @ApiModelProperty("英文名称")
    private String englishName;

    @ApiModelProperty("供应商")
    private String supplier;

    @ApiModelProperty("简称")
    private String forShort;

    @ApiModelProperty("包装规格")
    private String packingSpecification;

    @ApiModelProperty("规格")
    private String specification;

    @ApiModelProperty("存储条件")
    private String storageCondition;

    @ApiModelProperty("生产日期")
    private LocalDate productionDate;

    @ApiModelProperty("有效期")
    private LocalDate validity;
}
