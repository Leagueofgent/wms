package com.warehouse.management.wms.mapper;

import com.warehouse.management.wms.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gent
 * @since 2024-01-21
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> selectRoleByUserId(Long id);
}
