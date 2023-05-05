package com.demo.springcloud.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.demo.springcloud.dao"})
public class MyBatisConfig {
}
