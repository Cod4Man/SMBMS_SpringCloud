package com.codeman.smbms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 张鸿杰
 * Date：2019-05-23
 * Time:14:24
 */
@SpringBootApplication
@EnableEurekaServer
public class Eureka_APP_7001 {
    public static void main(String[] args) {
        SpringApplication.run(Eureka_APP_7001.class,args);
    }
}
