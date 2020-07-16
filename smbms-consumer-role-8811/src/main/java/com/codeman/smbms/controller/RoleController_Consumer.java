package com.codeman.smbms.controller;

import com.codeman.smbms.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 张鸿杰
 * Date：2019-05-24
 * Time:8:32
 */
@RestController
public class RoleController_Consumer {
    private static final String PREFIX_ROLE = "http://SMBMS-PROVIDER-ROLE";

    /**
     * 使用 使用restTemplate访问restful接口非常的简单粗暴无脑。 (url, requestMap,
     * ResponseBean.class)这三个参数分别代表 REST请求地址、请求参数、HTTP响应转换被转换成的对象类型。
     */
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/test/22")
    @SuppressWarnings("unchecked")
    public List<Role> getRoleList() {
        System.out.println("==================================");
        return restTemplate.getForObject(PREFIX_ROLE + "/role/getRoleList", List.class);
    }


//    使用Feign测试
//    @Autowired
//    private RoleClientService roleClientService;
//
//    @RequestMapping("/consumer/role/list")
//    public List<Role> getRoleList() {
//        System.out.println("========================");
//        List<Role> roleList = roleClientService.getRoleList();
//        System.out.println(roleList);
//        return roleList;
//    }




}
