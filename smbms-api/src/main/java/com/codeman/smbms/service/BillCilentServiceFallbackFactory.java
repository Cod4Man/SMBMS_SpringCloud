package com.codeman.smbms.service;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import java.util.List;

import com.codeman.smbms.entity.Bill;
import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

@Component // 不要忘记添加，不要忘记添加
public class BillCilentServiceFallbackFactory implements FallbackFactory<BillCilentService> {

    @Override
    public BillCilentService create(Throwable throwable) {
        return new BillCilentService(){

            @Override
            public Bill goModify(String billid) {
                Bill bill = new Bill();
                bill.setProviderName("没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭");
                bill.setId(-1);
                bill.setBillCode("没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭");
                bill.setProductName("没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭");
                bill.setProductDesc("没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭");
                bill.setProductUnit("没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭");
                bill.setProductCount(new BigDecimal("0"));
                bill.setTotalPrice(new BigDecimal("0"));
                bill.setIsPayment(0);
                bill.setProviderId(0);
                bill.setCreatedBy(0);
                bill.setCreationDate(new Date());
                bill.setModifyBy(0);
                bill.setModifyDate(new Date());

                return bill;
            }

            @Override
            public List<Bill> query(Bill bill2) {
                Bill bill = new Bill();
                bill.setProviderName("没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭");
                bill.setId(-1);
                bill.setBillCode("没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭");
                bill.setProductName("没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭");
                bill.setProductDesc("没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭");
                bill.setProductUnit("没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭");
                bill.setProductCount(new BigDecimal("0"));
                bill.setTotalPrice(new BigDecimal("0"));
                bill.setIsPayment(0);
                bill.setProviderId(0);
                bill.setCreatedBy(0);
                bill.setCreationDate(new Date());
                bill.setModifyBy(0);
                bill.setModifyDate(new Date());
                return Arrays.asList(bill);
            }

            @Override
            public Boolean add(Bill bill) {
                return false;
            }

            @Override
            public Boolean delbill(String billid) {
                return false;
            }

            @Override
            public String getproviderlist() {
                return "没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭";
            }

            @Override
            public Bill view(String billid) {
                Bill bill = new Bill();
                bill.setProviderName("没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭");
                bill.setId(-1);
                bill.setBillCode("没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭");
                bill.setProductName("没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭");
                bill.setProductDesc("没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭");
                bill.setProductUnit("没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭");
                bill.setProductCount(new BigDecimal("0"));
                bill.setTotalPrice(new BigDecimal("0"));
                bill.setIsPayment(0);
                bill.setProviderId(0);
                bill.setCreatedBy(0);
                bill.setCreationDate(new Date());
                bill.setModifyBy(0);
                bill.setModifyDate(new Date());
                return bill;
            }

            @Override
            public Boolean modify(Bill bill) {
                return false;
            }

            @Override
            public Integer count(String id) {
                return -1;
            }
        };
    }
}
