package com.yy.shirosecurity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.yy.shirosecurity.base.mapper")
public class ShiroSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroSecurityApplication.class, args);
    }

}
