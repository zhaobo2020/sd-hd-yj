package com.sl.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(
        basePackages = {
                "com.sl.water.dao",
                "com.sl.model.dao",
                "com.sl.dispatch.dao"
        },
        sqlSessionFactoryRef =
                "mysqlSqlSessionFactory"
)
public class MybatisPlus_Mysql_Config {

    @Bean
    public CustomMetaObjectHandler customMetaObjectHandler() {
            return new CustomMetaObjectHandler();
    }
}

