package com.codeman.smbms.controller;

import com.alibaba.fastjson.JSONArray;
import com.codeman.smbms.entity.Role;
import com.codeman.smbms.entity.User;
import com.codeman.smbms.tools.Constants;
import com.codeman.smbms.tools.PageSupport;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 张鸿杰
 * Date：2019-05-23
 * Time:15:54
 */
@Controller
@RequestMapping(value = "/consumer/")
public class UserController_Consumer extends BaseController {
//    private final String PROVIDER_PREFIX = "http://localhost:8001/sys/user";
//    private final String PROVIDER_PREFIX = "http://SMBMS-PROVIDER-USER/sys/user";
    private final String PROVIDER_PREFIX = "http://SMBMS-ZUUL-GATEWAY/smbms/user/sys/user";

    @Autowired
    private RestTemplate restTemplate;

    //页面跳转=====================

    @RequestMapping("/goIndex")
    public String goIndex() {
        System.out.println("goIndex");
        return "index";
    }

    /**
     * 用户登录
     * @param userCode 账号
     * @param userPassword 密码
     * @return java.lang.String 跳转页面视图
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28
     */

    @RequestMapping(value = "/doLogin")
    public User doLogin(@RequestParam String userCode, @RequestParam String userPassword) {
        Map<String, Object> params = new HashMap<>();
        params.put("userCode", userCode);
        params.put("userPassword", userPassword);
        return restTemplate.getForObject("http://SMBMS-PROVIDER-USER/login/doLogin" , User.class);
    }
    /**
     * 页面跳转至修改密码页面
     * @param
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28
     */
    @RequestMapping(value = "/goPwdmodify")
    public String goPwdmodify(@ModelAttribute("user") User user) {
        return "pwdmodify";
    }

    @RequestMapping(value = "/goUseradd")
    public String goUseradd(@ModelAttribute("user") User user) {
        return "useradd";
    }

    //业务========================
    @RequestMapping(value = "/modifyexe")
    public String modifyexe(User user, HttpSession session) {
        user.setModifyBy(((User)session.getAttribute(Constants.USER_SESSION)).getId());
        user.setModifyDate(new Date());
        Boolean aBoolean = restTemplate.postForObject(PROVIDER_PREFIX + "/modifyexe", user, Boolean.class);
        if(aBoolean){
            return "redirect:query";
        }else{
            return "/jsp/usermodify";
        }
    }

    /**
     * 使用REST风格的用户详情查看
     * @param userid
     * @param model
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/29
     */
    @RequestMapping(value = {"/view/{userid}"},method = RequestMethod.GET)
    public String view(@PathVariable String userid,
                       Model model) {
        System.out.println("view  By REST");
        String id = userid;
        if (!StringUtils.isNullOrEmpty(id)) {
            //调用后台方法得到user对象
            User user = restTemplate.getForObject(PROVIDER_PREFIX + "/view/" + id, User.class);
            model.addAttribute(user);
        }
        return "userview";
    }

    /**
     * 使用REST风格的用户修改页面跳转
     * @param userid
     * @param model
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/29
     */
    @RequestMapping(value = "/modify/{userid}",method = RequestMethod.GET)
    public String modify(@PathVariable String userid,
                         Model model) {
        System.out.println("modify By REST");
        if (!StringUtils.isNullOrEmpty(userid)) {
            //调用后台方法得到user对象
            User user = restTemplate.getForObject(PROVIDER_PREFIX + "/modify/" + userid, User.class);
            model.addAttribute(user);
        }
        return "usermodify";
    }


    /**
     *  添加用户
     * @param session
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28
     */
    @RequestMapping(value = "/add")
    public String add(User user, HttpSession session) {
        System.out.println("add()================");
        user.setCreationDate(new Date());
        user.setCreatedBy(((User)session.getAttribute(Constants.USER_SESSION)).getId());
        System.out.println("userName=========" + user.getUserName());
        Boolean result = restTemplate.postForObject(PROVIDER_PREFIX + "/add", user, Boolean.class );
        if(result){
            return "redirect:query";
        }else{
            return "forward: goUseradd";
        }
    }
    /**
     * 修改密码
     * @param newpassword
     * @param session
     * @param model
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28
     */
    @RequestMapping(value = "/savepwd")
    public String savepwd(@RequestParam String newpassword,
                          HttpSession session, Model model) {
        System.out.println("修改密码操作");
        Object o = session.getAttribute(Constants.USER_SESSION);
        boolean flag = false;
        if(o != null && !StringUtils.isNullOrEmpty(newpassword)){
            MultiValueMap<String, Object> uriVariables = new LinkedMultiValueMap<>();
            uriVariables.put("id", Arrays.asList(((User)o).getId()));
            uriVariables.put("newpassword", Arrays.asList(newpassword));
            flag = restTemplate.postForObject(PROVIDER_PREFIX + "/savepwd",uriVariables, Boolean.class );
            if(flag){
                model.addAttribute(Constants.SYS_MESSAGE, "修改密码成功,请退出并使用新密码重新登录！");
                session.removeAttribute(Constants.USER_SESSION);//session注销
            }else{
                model.addAttribute(Constants.SYS_MESSAGE, "修改密码失败！");
            }
        }else{
            model.addAttribute(Constants.SYS_MESSAGE, "修改密码失败！");
        }
        //        request.getRequestDispatcher("pwdmodify.jsp").forward(request, response);
        return "forward:goPwdmodify";
    }

    /**
     * 查询用户
     * @param queryname
     * @param queryUserRoleS
     * @param pageIndex
     * @param model
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28
     */
    @RequestMapping(value = "/query")
    public String query(@RequestParam(required = false) String queryname,
                        @RequestParam(required = false, value = "queryUserRole") String queryUserRoleS,
                        @RequestParam(required = false) String pageIndex,
                        Model model) {
        //查询用户列表
        String queryUserName = queryname;
        String temp = queryUserRoleS;
        int queryUserRole = 0;
        //当前页码
        Integer currentPageNo = 1;
        /**
         * http://localhost:8090/SMBMS/userlist.do
         * ----queryUserName --NULL
         * http://localhost:8090/SMBMS/userlist.do?queryname=
         * --queryUserName ---""*/


        System.out.println("queryUserName servlet--------"+queryUserName);
        System.out.println("queryUserRole servlet--------"+queryUserRole);
        System.out.println("query pageIndex--------- > " + pageIndex);
        if(queryUserName == null){
            queryUserName = "";
        }
        if(temp != null && !temp.equals("")){
            queryUserRole = Integer.parseInt(temp);
        }

        if(pageIndex != null){
            try{
                currentPageNo = Integer.valueOf(pageIndex);
            }catch(NumberFormatException e){
                return "error";
            }
        }
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.put("currentPageNo", Arrays.asList(currentPageNo) );
        params.put("queryUserRole", Arrays.asList(queryUserRole));
        params.put("queryUserName", Arrays.asList(queryUserName));
        Map<String, Object> models = restTemplate.postForObject(PROVIDER_PREFIX + "/query", params, Map.class);
        models.forEach((k,v) -> model.addAttribute(k,v));
        return "userlist";
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
    @RequestMapping(value = "/deluser")
    @ResponseBody
    public String deluser(@RequestParam String uid) {
        String id = uid;
        Integer delId = 0;
        try{
            delId = Integer.parseInt(id);
        }catch (Exception e) {
            e.printStackTrace();
            delId = 0;
        }
        String result = "";
        if(delId <= 0){
            result = "notexist";
        }else{
            Boolean res = restTemplate.getForObject(PROVIDER_PREFIX + "/deluser/" + delId, Boolean.class);
            if(res){
                result = "true";
            }else{
                result = "false";
            }
        }
        return result;
    }
    /**
     * Ajax判断用户是否存在
     * @param userCode
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28
     */
    @RequestMapping(value = "/ucexist")
    @ResponseBody
    public Object ucexist(@RequestParam String userCode) {
        //判断用户账号是否可用
        HashMap<String, String> resultMap = new HashMap<String, String>();
        if (StringUtils.isNullOrEmpty(userCode)) {
            //userCode == null || userCode.equals("")
            resultMap.put("result", "exist");
        } else {
            User user = restTemplate.getForObject(PROVIDER_PREFIX + "/ucexist/" + userCode, User.class);
            if (null != user) {
                resultMap.put("result", "exist");
            } else {
                resultMap.put("result", "notexist");
            }
        }
        return JSONArray.toJSONString(resultMap);
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
    @ResponseBody
    public String getrolelist() {
        List<Role> roleList = restTemplate.getForObject(PROVIDER_PREFIX + "/getrolelist", List.class );
        return JSONArray.toJSONString(roleList);
    }
    /**
     * Ajax验证旧密码
     * @param oldpassword
     * @param session
     * @return java.lang.String
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28
     */
    @RequestMapping(value = "/pwdmodify", method = RequestMethod.POST)
    @ResponseBody
    public String pwdmodify(@RequestParam String oldpassword,
                            HttpSession session) {
        Object o = session.getAttribute(Constants.USER_SESSION);
        String result = "";
        if(null == o ){//session过期
            result = "sessionerror";
        }else if(StringUtils.isNullOrEmpty(oldpassword)){//旧密码输入为空
            result = "error";
        }else{
            String sessionPwd = ((User)o).getUserPassword();
            if(oldpassword.equals(sessionPwd)){
                result = "true";
            }else{//旧密码输入不正确
                result = "false";
            }
        }
        return result;
    }
}
