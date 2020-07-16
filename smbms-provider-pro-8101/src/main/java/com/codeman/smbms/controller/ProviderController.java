package com.codeman.smbms.controller;

import com.codeman.smbms.entity.Provider;
import com.codeman.smbms.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 张鸿杰
 * Date：2019-03-28
 * Time:21:25
 */
@RestController
@RequestMapping(value = "/sys/provide"/*,produces = "text/html;charset=UTF-8"*/)
public class ProviderController extends BaseController {
    @Autowired
    private ProviderService providerService;


    //业务处理

    /**
     *  修改供应商操作
     * @return
     */
    @RequestMapping(value = "/domodify")
    public Boolean domodify(Provider provider) {
        return providerService.modify(provider);
    }
    /**
     *  REST风格的 modify Provider跳转
     * @param proid
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28
     */
    @RequestMapping(value = {"/modify/{proid}"})
    public Provider modify(@PathVariable String proid) {
        return providerService.getProviderById(proid);
    }
    /**
     *  REST风格的 view Provider跳转
     * @param proid
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28
     */
    @RequestMapping(value = {"/view/{proid}"})
    public Provider view(@PathVariable String proid) {
        return providerService.getProviderById(proid);
    }
    /**
     *  处理增加供应商方法
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28
     */
    @RequestMapping(value = "/add")
    public Boolean addProvider(Provider provider) {
        return providerService.add(provider);
    }
    /**
     * 查询供应商列表
     * @param queryProName
     * @param queryProCode
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28
     */
    @RequestMapping(value = "/query")
    public List<Provider> query(@RequestParam(required = false) String queryProName,
            @RequestParam(required = false) String queryProCode) {
        return providerService.getProviderList(queryProName,queryProCode);
    }

    //Ajax回应
    /**
     * Ajax 回应删除供应商
     * @param proid
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28
     */
    @RequestMapping(value = "/delprovider")
    public Integer delprovider(@RequestParam String proid) {
        return providerService.deleteProviderById(proid);
    }

}
