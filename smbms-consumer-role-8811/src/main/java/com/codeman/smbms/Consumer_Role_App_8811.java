package com.codeman.smbms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 张鸿杰
 * Date：2019-05-24
 * Time:8:31
 */
@SpringBootApplication
@EnableEurekaClient
//@EnableDiscoveryClient //服务发现
//@EnableFeignClients(basePackages = {"com.codeman.smbms"})
//在服务启动时，加载我们自定义的配置类，使其生效
//@RibbonClient(name = "MICROSERVICECLOUD-DEPT", configuration = MyselfRule.class)
public class Consumer_Role_App_8811 {
    public static void main(String[] args) {
        SpringApplication.run(Consumer_Role_App_8811.class,args);
    }
}
