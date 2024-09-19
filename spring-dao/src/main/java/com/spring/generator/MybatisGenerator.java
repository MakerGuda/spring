package com.spring.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
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
                .globalConfig(builder -> builder.author("spring"))
                // 包配置
                .packageConfig(builder -> builder.entity("spring-dao/src/main/java/com/spring/entity")
                        .mapper("spring-dao/src/main/java/com/spring/mapper")
                        .xml("spring-dao/src/main/resources/mappers")
                )
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
                        .entityBuilder()
                        .enableLombok()
                        .enableFileOverride()
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
