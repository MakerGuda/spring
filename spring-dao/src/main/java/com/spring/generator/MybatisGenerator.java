package com.spring.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MybatisGenerator {

    private static final String URL = "jdbc:mysql://localhost:3306/spring";

    private static final String USERNAME = "root";

    private static final String PASSWORD = "123456";

    public static void main(String[] args) {
        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                // 全局配置
                .globalConfig(builder -> builder.author("spring")    //作者
                        .commentDate("yyyy-MM-dd hh:mm:ss")     //注释时间
                        .disableOpenDir()       //禁止打开输出目录
                        .outputDir(Paths.get(System.getProperty("user.dir")) + "/src/main/java")
                )
                // 包配置
                .packageConfig(builder -> builder.parent("com.spring") // 设置父包名
                        .entity("entity")   //pojo 实体类包名
                        .mapper("mapper")   //Mapper 包名
                        .xml("mappers")  //Mapper XML 包名
                )
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))   //需要生成的表名
                        .enableSkipView()   // 创建实体类的时候跳过试图
                        .entityBuilder().enableLombok()     //开启lombok
                        .idType(IdType.AUTO)    //设置主键自增
                        .mapperBuilder().superClass(BaseMapper.class)   //设置mapper接口的父类
                        .enableBaseResultMap()  //生成baseResultMap
                        .serviceBuilder().disable().controllerBuilder().disable()   //禁止生成service和controller
                        .build())
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

    // 处理all情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

}
