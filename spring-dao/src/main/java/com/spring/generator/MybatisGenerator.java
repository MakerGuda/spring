package com.spring.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

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
                        .outputDir(System.getProperty("user.dir") + "/src/main/java")   //设置输出路径：项目的 java 目录下【System.getProperty("user.dir")意思是获取到项目所在的绝对路径】
                        .commentDate("yyyy-MM-dd hh:mm:ss")     //注释时间
//                        .disableOpenDir()       //禁止打开输出目录
                )
                // 包配置
                .packageConfig(builder -> builder.parent("com.spring") // 设置父包名
                        .entity("entity")   //pojo 实体类包名
                        .mapper("mapper")   //Mapper 包名
                        .xml("mapper.xml")  //Mapper XML 包名
                        .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + "/src/main/resources/mappers"))    //配置 mapper.xml 路径信息：项目的 resources 目录下
                )
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))   //需要生成的表名
                        .enableSkipView()   // 创建实体类的时候跳过试图
                        .entityBuilder().enableLombok()     //开启lombok
                        .idType(IdType.AUTO)    //设置主键自增
                        .mapperBuilder().superClass(BaseMapper.class)   //设置mapper接口的父类
                        .enableBaseResultMap()
                        .enableBaseColumnList()
                        .build())
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

}
