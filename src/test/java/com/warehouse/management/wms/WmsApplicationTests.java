package com.warehouse.management.wms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WmsApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void Auto(){
        FastAutoGenerator.create("jdbc:mysql://124.223.89.149:3306/wms", "root", "gy4516...")
                .globalConfig(builder -> {
                    builder
                            .author("gent") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .disableOpenDir() //不打开生成文件的目录
                            .outputDir(System.getProperty("user.dir")+"/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.warehouse.management.wms"); // 设置父包名
//                            .moduleName("system") // 设置父包模块名

//                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D://")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder
//                            .addInclude("") // 设置需要生成的表名，不设置表名即为生成该数据库中所有的表
                            .addTablePrefix("t_", "c_")  // 设置过滤表前缀
                            .entityBuilder().idType(IdType.ASSIGN_ID) //主键id自动生成策略 雪花算法
                            .logicDeleteColumnName("is_deleted") //数据库逻辑删除字段属性
                            .entityBuilder().enableRemoveIsPrefix().enableFileOverride()  //去掉布尔值的is_前缀
                            .enableLombok() //开启lombok模型

                            .controllerBuilder().enableRestStyle().enableFileOverride()  //restful 所有controller都是RestController
                            .serviceBuilder().formatServiceFileName("%sService").enableFileOverride(); //去掉Service接口的首字母I
                })
                // Freemarker引擎模板的RequestMapper地址不是驼峰形式 Velocity是驼峰形式
                //.templateEngine(new VelocityTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板

                .execute();
    }

}
