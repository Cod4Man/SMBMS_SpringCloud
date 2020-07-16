package com.codeman.smbms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


/**
 * Created with IntelliJ IDEA.
 *
 * @author 张鸿杰
 * Date：2019-05-26
 * Time:18:29
 */
@SpringBootApplication
@EnableZuulProxy
//@EnableRedisHttpSession(redisFlushMode = RedisFlushMode.IMMEDIATE)
public class ZUUL_APP_9527 {
    public static void main(String[] args) {
        SpringApplication.run(ZUUL_APP_9527.class, args);
    }
//    @Bean
//    public CorsFilter corsFilter() {
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        final CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        //config.addAllowedMethod("OPTIONS");
//        //config.addAllowedMethod("HEAD");
//        //config.addAllowedMethod("GET");
//        //config.addAllowedMethod("PUT");
//        //config.addAllowedMethod("POST");
//        //config.addAllowedMethod("DELETE");
//        //config.addAllowedMethod("PATCH");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("*//**", config);
//        return new CorsFilter(source);
//    }
}
