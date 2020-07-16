/*
package com.codeman.smbms.controller;

import com.codeman.smbms.entity.Role;
import com.codeman.smbms.service.RoleClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

*/
/**
 * Created with IntelliJ IDEA.
 *
 * @author 张鸿杰
 * Date：2019-05-23
 * Time:17:40
 *//*

@RestController
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleClientService roleService;
    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/getRoleList")
    public List<Role> getRoleList() {
        System.out.println("8101GetRoleList2==============");
        System.out.println(roleService.getRoleList());
        return roleService.getRoleList();
    }

    @RequestMapping(value = "/dept/discovery", method = RequestMethod.GET)
    public Object discovery() {
        List<String> list = client.getServices();
        System.out.println("**********" + list);

        List<ServiceInstance> srvList = client.getInstances("MICROSERVICECLOUD-DEPT");
        for (ServiceInstance element : srvList) {
            System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t"
                    + element.getUri());
        }
        return this.client;
    }
}
*/
