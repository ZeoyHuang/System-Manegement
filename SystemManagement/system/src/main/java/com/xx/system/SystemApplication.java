package com.xx.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

//@SpringBootApplication(scanBasePackages = {"com.xx.system","com.xx.logging"})
@SpringBootApplication(scanBasePackages = {"com.xx.system","com.xx.tools","com.xx.logging","com.xx.common"})
@MapperScan(value = {"com.xx.logging.mapper","com.xx.system.mapper"})
@EnableScheduling
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }

}
