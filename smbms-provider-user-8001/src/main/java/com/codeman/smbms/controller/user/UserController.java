package com.codeman.smbms.controller.user;

import com.codeman.smbms.controller.BaseController;
import com.codeman.smbms.entity.Role;
import com.codeman.smbms.entity.User;
import com.codeman.smbms.service.role.RoleService;
import com.codeman.smbms.service.user.UserService;
import com.codeman.smbms.tools.Constants;
import com.codeman.smbms.tools.PageSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 张鸿杰
 * Date：2019-03-28
 * Time:14:23
 */
@RestController
@RequestMapping(value = "/sys/user"/*, produces = "text/html;charset=UTF-8"*/)
public class UserController extends BaseController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private RestTemplate restTemplate;

    //业务========================
    @RequestMapping(value = "/modifyexe")
    public Boolean modifyexe(@RequestBody User user) {
        return userService.modify(user);
    }

    /**
     * 使用REST风格的用户详情查看
     * @param userid
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/29
     */
    @RequestMapping(value = {"/view/{userid}"},method = RequestMethod.GET)
    public User view(@PathVariable Integer userid) {
        System.out.println("view  By REST");
        //调用后台方法得到user对象
       return userService.getUserById(userid+"");
    }

    /**
     * 使用REST风格的用户修改页面跳转
     * @param userid
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/29
     */
    @RequestMapping(value = "/modify/{userid}",method = RequestMethod.GET)
    public User modify(@PathVariable String userid) {
        System.out.println("modify By REST");
        return userService.getUserById(userid);
    }


    /**
     *  添加用户
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28
     */
    @RequestMapping(value = "/add")
    public Boolean add(@RequestBody User user) {
        System.out.println("add()================");
        System.out.println("UserName ======= " + user.getUserName());
        return userService.add(user);
    }
    /**
     * 修改密码
     * @param newpassword
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28
     */
    @RequestMapping(value = "/savepwd")
    public Boolean savepwd(@RequestParam Integer id ,@RequestParam String newpassword) {
        System.out.println("修改密码操作");
        return userService.updatePwd(id,newpassword);
    }

    /**
     * 查询用户
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28
     */
    @RequestMapping(value = "/query")
    public Map<String, Object> query(@RequestParam(required = false) String queryUserName,
                                     @RequestParam(required = false, value = "queryUserRole") Integer queryUserRole,
                                     @RequestParam(required = false) Integer currentPageNo) {
        int pageSize = Constants.pageSize;
        //总数量（表）
        int totalCount	= userService.getUserCount(queryUserName,queryUserRole);
        //总页数
        PageSupport pages=new PageSupport();
        pages.setCurrentPageNo(currentPageNo);
        pages.setPageSize(pageSize);
        pages.setTotalCount(totalCount);

        int totalPageCount = pages.getTotalPageCount();

        //控制首页和尾页
        if(currentPageNo < 1){
            currentPageNo = 1;
        }else if(currentPageNo > totalPageCount){
            currentPageNo = totalPageCount;
        }
        List<User> userList = new ArrayList<>();
        userList = userService.getUserList(queryUserName,queryUserRole,currentPageNo, pageSize);
        List<Role> roleList = null;
//        roleList = restTemplate.getForObject(ROLE_PREFIX + "/getRoleLost", List.class);
        roleList = roleService.getRoleList();
        userList.forEach(System.out::println);
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("userList", userList);
        objectMap.put("roleList", roleList);
        objectMap.put("queryUserName", queryUserName);
        objectMap.put("queryUserRole", queryUserRole);
        objectMap.put("totalPageCount", totalPageCount);
        objectMap.put("totalCount", totalCount);
        objectMap.put("currentPageNo", currentPageNo);
        return objectMap;
    }




    //Ajax
    /**
     * Ajax 删除用户数据
     * @param uid
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28
     */
    @RequestMapping(value = "/deluser/{uid}")
    public Boolean deluser(@PathVariable Integer uid) {
        return userService.deleteUserById(uid);
    }
    /**
     * Ajax判断用户是否存在
     * @param userCode
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28
     */
    @RequestMapping(value = "/ucexist/{userCode}")
    public User ucexist(@PathVariable String userCode) {
        return userService.selectUserCodeExist(userCode);
    }
    /**
     * Ajax返回roleList
     * @param
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28
     */
    @RequestMapping(value = "/getrolelist")
    @SuppressWarnings("unchecked")
    public List<Role> getrolelist() {
//        return restTemplate.getForObject(ROLE_PREFIX + "/getRoleList", List.class);
        return roleService.getRoleList();
    }
}
