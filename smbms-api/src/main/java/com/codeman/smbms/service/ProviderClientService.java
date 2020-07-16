package com.codeman.smbms.service;

import com.codeman.smbms.entity.Provider;
import com.codeman.smbms.entity.User;
import com.codeman.smbms.tools.Constants;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@FeignClient(value = "SMBMS-ZUUL-GATEWAY", fallbackFactory = ProviderCilentServiceFallbackFactory.class)
//@FeignClient(value = "SMBMS-PROVIDER-PRO", fallbackFactory = ProviderCilentServiceFallbackFactory.class)
public interface ProviderClientService {

    //页面跳转
    /**
     * 跳转至添加供应商页面
     * @param
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28
     */
    @RequestMapping(value = "/smbms/provider/sys/provide/goProviderAdd")
    public String goProviderAdd(@ModelAttribute("provider") Provider provider);

    //业务处理

    /**
     *  修改供应商操作
     * @return
     */
    @RequestMapping(value = "/smbms/provider/sys/provide/domodify")
    public Boolean domodify(@RequestBody Provider provider);
    /**
     *  REST风格的 modify Provider跳转
     * @param proid
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28
     */
    @RequestMapping(value = {"/smbms/provider/sys/provide/modify/{proid}"})
    public Provider modify(@PathVariable(value = "proid") String proid);
    /**
     *  REST风格的 view Provider跳转
     * @param proid
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28
     */
    @RequestMapping(value = {"/smbms/provider/sys/provide/view/{proid}"})
    public Provider view(@PathVariable(value = "proid") String proid);
    /**
     *  处理增加供应商方法
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28
     */
    @RequestMapping(value = "/smbms/provider/sys/provide/add")
    public Boolean addProvider(@RequestBody Provider provider);
    /**
     * 查询供应商列表
     * @param queryProName
     * @param queryProCode
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28
     */
    @RequestMapping(value = "/smbms/provider/sys/provide/query")
    public List<Provider> query(@RequestParam(value = "queryProName", required = false) String queryProName,
                        @RequestParam(value = "queryProCode", required = false) String queryProCode);

    //Ajax回应
    /**
     * Ajax 回应删除供应商
     * @param proid
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28
     */
    @RequestMapping(value = "/smbms/provider/sys/provide/delprovider")
    @ResponseBody
    public Integer delprovider(@RequestParam(value = "proid") String proid);
}
