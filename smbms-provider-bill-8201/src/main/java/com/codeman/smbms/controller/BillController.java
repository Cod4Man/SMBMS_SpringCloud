package com.codeman.smbms.controller;

import com.codeman.smbms.entity.Bill;
import com.codeman.smbms.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 张鸿杰
 * Date：2019-03-28
 * Time:9:24
 */
@RestController
@RequestMapping(value = "/sys/bill"/*,produces = "text/html;charset=UTF-8"*/)
public class BillController extends BaseController {
    @Autowired
    private BillService billService;

  @RequestMapping(value = "/goModify/{billid}")
  public Bill goModify(@PathVariable String billid) {
      return billService.getBillById(billid);
  }
  /**
   * 查询全部订单
   * @return java.lang.String
   * @author zhj
   * @creed: Talk is cheap,show me the code
   * @date 2019/3/28
   */
  @RequestMapping(value = "/query")
  public List<Bill> query(@RequestBody Bill bill) {
    return billService.getBillList(bill);
  }

  /**
   * 添加订单
   * @param session
   * @return java.lang.String
   * @author zhj
   * @creed: Talk is cheap,show me the code
   * @date 2019/3/28
   */
  @RequestMapping(value = "/billadd")
  public Boolean add(Bill bill) {
    return billService.add(bill);
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
  public Boolean delbill(@RequestParam String billid) {
    return billService.deleteBillById(billid);
  }

  @RequestMapping(value = "/view/{billid}")
  public Bill view(@PathVariable String billid) {
    return billService.getBillById(billid);
  }
  @RequestMapping(value = "/modify")
  public Boolean modify(Bill bill) {
    return billService.modify(bill);
  }

  @RequestMapping(value = "/count/{id}")
  public Integer count(@PathVariable String id) {
    return billService.getBillCountByProviderId(id);
  }
}
