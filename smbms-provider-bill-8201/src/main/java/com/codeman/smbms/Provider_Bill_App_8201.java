package com.codeman.smbms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 张鸿杰
 * Date：2019-05-25
 * Time:11:26
 */
@SpringBootApplication
@MapperScan("com.codeman.smbms.mapper")
@EnableEurekaClient
@EnableDiscoveryClient
public class Provider_Bill_App_8201 {
    public static void main(String[] args) {
        SpringApplication.run(Provider_Bill_App_8201.class, args);
    }
}
