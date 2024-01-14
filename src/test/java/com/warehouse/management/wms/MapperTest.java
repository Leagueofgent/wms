package com.warehouse.management.wms;

import com.warehouse.management.wms.entity.SysUser;
import com.warehouse.management.wms.mapper.SysUserMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
public class MapperTest {
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Test
    public void setSysUserMapper() {
        List<SysUser> sysUsers = sysUserMapper.selectList(null);
        System.out.println(sysUsers);
    }

    @Test
    public void TestPasswordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.matches("gent", "$2a$10$PlKnZmQqQnvKjXIUmm3fF.T6kG2tyeJgv3ndS1dTFE8kMM1zUIiNC"));
        String gent = bCryptPasswordEncoder.encode("gent");
        String gent1 = bCryptPasswordEncoder.encode("gent");
        System.out.println(gent1);
        System.out.println(gent);
    }

}
