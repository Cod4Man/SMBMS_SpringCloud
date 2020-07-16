package com.codeman.smbms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 张鸿杰
 * Date：2019-05-25
 * Time:11:15
 */
@SpringBootApplication
@EnableEurekaClient //注册
@EnableDiscoveryClient //发现
@MapperScan("com.codeman.smbms.mapper")
@EnableRedisHttpSession
public class Provider_Pro_App_8101 {
    public static void main(String[] args) {
        SpringApplication.run(Provider_Pro_App_8101.class, args);
    }
}
