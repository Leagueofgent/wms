package com.warehouse.management.wms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *  入库明细
 * </p>
 *
 * @author gent
 * @since 2023-12-20
 */
@Getter
@Setter
@TableName("warehousing_registration")
@ApiModel(value = "WarehousingRegistration对象", description = " 入库明细")
public class WarehousingRegistration implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;

    @ApiModelProperty("货号")
    private Integer articleNo;

    @ApiModelProperty("物料代码")
    private String materialCode;

    @ApiModelProperty("原厂批号")
    private Integer originalBatchNumber;

    @ApiModelProperty("入库批号")
    private Integer warehousingLotNumber;

    @ApiModelProperty("生产日期")
    private LocalDate productionDate;

    @ApiModelProperty("复验日期")
    private LocalDate dateOfReinspection;

    @ApiModelProperty("有效期")
    private LocalDate validity;

    @ApiModelProperty("数量")
    private Long quantity;

    @ApiModelProperty("单位")
    private String unit;

    @ApiModelProperty("件数")
    private Integer numberOfPackages;

    @ApiModelProperty("尾数")
    private String mantissa;

    @ApiModelProperty("板数")
    private Integer plateNumber;

    @ApiModelProperty("单价")
    private BigDecimal unitPrice;

    @ApiModelProperty("金额")
    private BigDecimal amount;

    @ApiModelProperty("仓位")
    private String shippingSpace;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("入库日期")
    private LocalDate warehousingDate;

    @ApiModelProperty("入库类别")
    private String incomingCategory;

    @ApiModelProperty("订单号")
    private String orderNumber;

    @ApiModelProperty("中文名称")
    private String chineseName;

    @ApiModelProperty("英文名称")
    private String englishName;

    @ApiModelProperty("供应商")
    private String supplier;

    @ApiModelProperty("厂家")
    private String manufacturer;

    @ApiModelProperty("包装规格")
    private String packingSpecification;

    @ApiModelProperty("规格")
    private String specification;

    @ApiModelProperty("存储条件")
    private String storageCondition;
}
