package com.codeman.smbms.controller;

import com.codeman.smbms.entity.Provider;
import com.codeman.smbms.entity.User;
import com.codeman.smbms.service.BillCilentService;
import com.codeman.smbms.service.ProviderClientService;
import com.codeman.smbms.tools.Constants;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 张鸿杰
 * Date：2019-03-28
 * Time:21:25
 */
@Controller
@RequestMapping(value = "/consumer_pro"/*,produces = "text/html;charset=UTF-8"*/)
public class ProviderController_Consumer extends BaseController {
    @Autowired
    private ProviderClientService providerClientService;

    @Autowired
    private BillCilentService billCilentService;
    //业务处理

    /**
     *  修改供应商操作
     * @param session
     * @return
     */
    @RequestMapping(value = "/domodify")
    public String domodify(Provider provider, HttpSession session) {
        provider.setModifyBy(((User)session.getAttribute(Constants.USER_SESSION)).getId());
        provider.setModifyDate(new Date());
        boolean flag = false;
        flag = providerClientService.domodify(provider);
        if(flag){
            return "redirect:query";
        }else{
            return "providermodify";
        }
    }
    /**
     *  REST风格的 modify Provider跳转
     * @param proid
     * @param model
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28
     */
    @RequestMapping(value = {"/modify/{proid}"})
    public String modify(@ModelAttribute("provider") Provider provider2,
                                                            @PathVariable String proid,
                                                            Model model) {
        System.out.println("REST modify provider");
        if(!StringUtils.isNullOrEmpty(proid)){
            Provider provider = providerClientService.modify(proid);
            model.addAttribute("provider", provider);
        }
            return "providermodify";
    }
    /**
     *  REST风格的 view Provider跳转
     * @param proid
     * @param model
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28
     */
    @RequestMapping(value = {"/view/{proid}"})
    public String view(@ModelAttribute("provider") Provider provider2,
                                                            @PathVariable String proid,
                                                            Model model) {
        System.out.println("view & modify");
        if(!StringUtils.isNullOrEmpty(proid)){
            Provider provider = providerClientService.view(proid);
            model.addAttribute("provider", provider);
        }
            return "providerview";
    }
    /**
     *  处理增加供应商方法
     * @param session
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28
     */
    @RequestMapping(value = "/add")
    public String addProvider(Provider provider, HttpSession session) {
        provider.setCreatedBy(((User)session.getAttribute(Constants.USER_SESSION)).getId());
        provider.setCreationDate(new Date());
        boolean flag = false;
        flag = providerClientService.addProvider(provider);
        if(flag){
            return "redirect:query";
        }else{
            return "forward:add";
        }
    }
    /**
     * 查询供应商列表
     * @param queryProName
     * @param queryProCode
     * @param model
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28
     */
    @RequestMapping(value = "/query")
    public String query(@RequestParam(required = false) String queryProName,
            @RequestParam(required = false) String queryProCode,Model model) {
//        HttpSession session1 = session.getSessionContext().getSession(uid);
//        session = session1;
        if(StringUtils.isNullOrEmpty(queryProName)){
            queryProName = "";
        }
        if(StringUtils.isNullOrEmpty(queryProCode)){
            queryProCode = "";
        }
        List<Provider> providerList = providerClientService.query(queryProName, queryProCode);
        model.addAttribute("providerList", providerList);
        model.addAttribute("queryProName", queryProName);
        model.addAttribute("queryProCode", queryProCode);
        return "providerlist";
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
    @ResponseBody
    public String delprovider(@RequestParam String proid) {
        String result = "";
        if(!StringUtils.isNullOrEmpty(proid)){
            //如果provider内部有bill则不能删除：
            Integer count = billCilentService.count(proid);
            if ( count == 0) {
                Integer flag = providerClientService.delprovider(proid);
                if(flag >0 ){//删除成功
                    result = "true";
                }else {//删除失败
                    result = "false";
                }
            } else if (count == -1){
                result = "false";
            } else {
                result = String.valueOf(count);
            }
        }else{
            result = "notexit";
        }
        return result;
    }

    @RequestMapping(value = "/count/{id}")
    @ResponseBody
    public Integer count(@PathVariable String id) {
        return billCilentService.count(id);
    }
}
