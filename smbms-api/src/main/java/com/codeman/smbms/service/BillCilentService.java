package com.codeman.smbms.service;

import com.codeman.smbms.entity.Bill;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "SMBMS-PROVIDER-BILL", fallbackFactory = BillCilentServiceFallbackFactory.class)
public interface BillCilentService {

    @RequestMapping(value = "/sys/bill/goModify/{billid}")
    public Bill goModify(@PathVariable(value = "billid") String billid) ;
    /**
     * 查询全部订单
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28
     */
    @RequestMapping(value = "/sys/bill/query")
    public List<Bill> query(@RequestBody Bill bill);

    /**
     * 添加订单
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28
     */
    @RequestMapping(value = "/sys/bill/billadd")
    public Boolean add(@RequestBody Bill bill);

    /**
     *  Ajax删除订单
     * @param
     * @return java.lang.String Ajax返回删除结果
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28
     */
    @RequestMapping(value = "/sys/bill/delbill")
    public Boolean delbill(@RequestParam(value = "billid") String billid);

    /** Ajxa获取供应商列表
     *
     * @param
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28
     */
    @RequestMapping(value = "/sys/bill/getproviderlist"/*,produces = "text/html;charset=UTF-8"*/)
    public String getproviderlist();

    @RequestMapping(value = "/sys/bill/view/{billid}")
    public Bill view(@PathVariable(value = "billid") String billid);

    @RequestMapping(value = "/sys/bill/modify")
    public Boolean modify(@RequestBody  Bill bill);

    @RequestMapping(value = "/count/{id}")
    public Integer count(@PathVariable(value = "id") String id);

}
