package com.codeman.smbms.service;
import java.util.Arrays;
import java.util.Date;

import com.codeman.smbms.entity.Provider;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component // 不要忘记添加，不要忘记添加
public class ProviderCilentServiceFallbackFactory implements FallbackFactory<ProviderClientService> {

    @Override
    public ProviderClientService create(Throwable throwable) {
        return new ProviderClientService() {
            @Override
            public String goProviderAdd(Provider provider) {
                return "没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭";
            }

            @Override
            public Boolean domodify(Provider provider) {
                return false;
            }

            @Override
            public Provider modify(String proid) {
                return getProvider();
            }

            @Override
            public Provider view(String proid) {
                Provider provider0 = new Provider();
                provider0.setId(-1);
                provider0.setProCode("6666666666");
                provider0.setProName("没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭");
                provider0.setProDesc("没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭");
                provider0.setProContact("没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭");
                provider0.setProPhone("没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭");
                provider0.setProAddress("没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭");
                provider0.setProFax("没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭");
                provider0.setCreatedBy(-1);
                provider0.setCreationDate(new Date());
                provider0.setModifyBy(-1);
                provider0.setModifyDate(new Date());
                return provider0;
            }

            @Override
            public Boolean addProvider(Provider provider) {
                return false;
            }

            @Override
            public List<Provider> query(String queryProName, String queryProCode) {
                return Arrays.asList(getProvider());
            }

            @Override
            public Integer delprovider(String proid) {
                return -1;
            }
        };
    }

    private Provider getProvider() {
        Provider provider0 = new Provider();
        provider0.setId(-1);
        provider0.setProCode("没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭");
        provider0.setProName("没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭");
        provider0.setProDesc("没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭");
        provider0.setProContact("没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭");
        provider0.setProPhone("没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭");
        provider0.setProAddress("没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭");
        provider0.setProFax("没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭");
        provider0.setCreatedBy(-1);
        provider0.setCreationDate(new Date());
        provider0.setModifyBy(-1);
        provider0.setModifyDate(new Date());
        return provider0;
    }
}
