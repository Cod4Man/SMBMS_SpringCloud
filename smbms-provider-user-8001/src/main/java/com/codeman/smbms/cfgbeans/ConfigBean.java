package com.codeman.smbms.cfgbeans;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RetryRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 张鸿杰
 * Date：2019-05-13
 * Time:9:08
 */
@Configuration
public class ConfigBean {

    @Bean
    @LoadBalanced //为Rest加入Ribbon配置
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public IRule myRule() {
        //return new RoundRobinRule();
        //return new RandomRule();//达到的目的，用我们重新选择的随机算法替代默认的轮询。
        return new RetryRule();
    }
}
