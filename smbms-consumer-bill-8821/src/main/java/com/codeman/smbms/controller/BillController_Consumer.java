package com.codeman.smbms.controller;

import com.alibaba.fastjson.JSONArray;
import com.codeman.smbms.entity.Bill;
import com.codeman.smbms.entity.Provider;
import com.codeman.smbms.entity.User;
import com.codeman.smbms.service.BillCilentService;
import com.codeman.smbms.service.ProviderClientService;
import com.codeman.smbms.tools.Constants;
import com.mysql.jdbc.StringUtils;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 *
 * @author 张鸿杰
 * Date：2019-03-28
 * Time:9:24
 */
@Controller
@RequestMapping(value = "/consumer_bill/"/*,produces = "text/html;charset=UTF-8"*/)
public class BillController_Consumer extends BaseController {
    @Autowired
    private BillCilentService billCilentService;
    @Autowired
    private ProviderClientService providerClientService;

    //页面跳转
    @RequestMapping(value = "/gobilladd")
    public String goBillAdd(@ModelAttribute("bill") Bill bill){
        return "billadd";
    }
    @RequestMapping(value = "/goModify/{billid}")
    public String goModify(@ModelAttribute("bill") Bill bill2, @PathVariable String billid, Model model){
        String id = billid;
        if (!StringUtils.isNullOrEmpty(id)) {
            Bill bill = billCilentService.goModify(billid);
            model.addAttribute("bill", bill);
        }
        return "billmodify";
    }
    /**
     * 查询全部订单
     * @param queryProductName
     * @param queryProviderId
     * @param queryIsPayment
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28
     */
    @RequestMapping(value = "/query")
    public String query(@RequestParam(required = false) String queryProductName,
                        @RequestParam(required = false) String queryProviderId,
                        @RequestParam(required = false) String queryIsPayment, Model model){
        int queryProviderIdI = -1;
        int queryIsPaymentI = -1;
        List<Provider> providerList = new ArrayList<Provider>();
        providerList = providerClientService.query("","");
        if(StringUtils.isNullOrEmpty(queryProductName)){
            queryProductName = "";
        }
        Bill bill = new Bill();
        if(StringUtils.isNullOrEmpty(queryIsPayment)){
            queryIsPaymentI = 0;
            bill.setIsPayment(queryIsPaymentI);
        }else{
            queryIsPaymentI = Integer.parseInt(queryIsPayment);
            bill.setIsPayment(queryIsPaymentI);
        }
        if(StringUtils.isNullOrEmpty(queryProviderId)){
            queryProviderIdI = 0;
            bill.setProviderId(queryProviderIdI);
        }else{
            queryProviderIdI = Integer.parseInt(queryProviderId);
            bill.setProviderId(queryProviderIdI);
        }
        bill.setProductName(queryProductName);
        List<Bill> billList = billCilentService.query(bill);
        model.addAttribute(providerList);
        model.addAttribute(billList);
        model.addAttribute(queryProductName);
        model.addAttribute( "queryProviderId", queryProviderIdI);
        model.addAttribute("queryIsPayment", queryIsPaymentI);
        return "billlist";
    }

    /**
     * 添加订单
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28
     */
    @RequestMapping(value = "/billadd")
    public String add(Bill bill, HttpSession session){
        bill.setProductCount(new BigDecimal(bill.getProductCount() + "").setScale(2,BigDecimal.ROUND_DOWN));
        bill.setTotalPrice(new BigDecimal(bill.getTotalPrice() + "").setScale(2,BigDecimal.ROUND_DOWN));
        bill.setCreatedBy(((User)session.getAttribute(Constants.USER_SESSION)).getId());
        bill.setCreationDate(new Date());
        Boolean flag = billCilentService.add(bill);
        if(flag){
            return "redirect:query";
        }else{
            return  "redirect:billadd";
        }
    }

    /**
     *  Ajax删除订单
     * @param
     * @return java.lang.String Ajax返回删除结果
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28
     */
    @RequestMapping(value = "/delbill")
    public String delbill(@RequestParam String billid){
        System.out.println("删除订单");
        String id = billid;
        String data = "";
        if(!StringUtils.isNullOrEmpty(id)){
            Boolean flag = billCilentService.delbill(billid);
            if(flag){//删除成功
                data = "true";
            }else{//删除失败
                data = "false";
            }
        }else{
            data = "notexit";
        }
        //直接使用map传参回去试试,不行，ajax无法接收
        //        System.out.println("mapjson====" + JSONArray.toJSONString(resultMap));
        return data;
    }

    /** Ajxa获取供应商列表
     *
     * @param
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28
     */
    @RequestMapping(value = "/getproviderlist"/*,produces = "text/html;charset=UTF-8"*/)
    @ResponseBody
    public String getproviderlist(){
        System.out.println("getproviderlist ========================= ");
        List<Provider> providerList = new ArrayList<Provider>();
        providerList = providerClientService.query("","");
        //传回数据
        System.out.println("listJson===" + JSONArray.toJSONString(providerList));
        return JSONArray.toJSONString(providerList);
    }

    @RequestMapping(value = "/view/{billid}")
    public String view(@PathVariable String billid, Model model){
        String id = billid;
        if (!StringUtils.isNullOrEmpty(id)) {
            Bill bill = billCilentService.view(billid);
            model.addAttribute(bill);
        }
        return "billview";
    }
    @RequestMapping(value = "/modify")
    public String modify(Bill bill, HttpSession session){
        System.out.println("modify===============");
        bill.setProductCount(new BigDecimal(bill.getProductCount() + "").setScale(2,BigDecimal.ROUND_DOWN));
        bill.setTotalPrice(new BigDecimal(bill.getTotalPrice() + "").setScale(2,BigDecimal.ROUND_DOWN));
        bill.setModifyBy(((User)session.getAttribute(Constants.USER_SESSION)).getId());
        bill.setModifyDate(new Date());
        boolean flag = false;
        flag = billCilentService.modify(bill);
        if(flag){
            return "redirect:query";
        }else{
            return "redirect:modify";
        }
    }
}
