package com.warehouse.management.wms.service;

import com.warehouse.management.wms.comment.Result;
import com.warehouse.management.wms.entity.SysUser;

public interface LoginService {
    Result login(SysUser sysUser);
}
