package com.codeman.smbms.controller;

import com.codeman.smbms.entity.User;
import com.codeman.smbms.tools.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 张鸿杰
 * Date：2019-03-28
 * Time:8:38
 */
@Controller
@RequestMapping(value = "/consumer/login")
public class LoginController_Consumer {
//    private final String PROVIDER_PREFIX = "http://localhost:8001/login";
//    private final String PROVIDER_PREFIX = "http://SMBMS-PROVIDER-USER/login";
    private final String PROVIDER_PREFIX = "http://SMBMS-ZUUL-GATEWAY/smbms/user/login";
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 用户登录
     * @param userCode 账号
     * @param userPassword 密码
     * @param model 模型
     * @param request  servlet-request
     * @param session  servlet-session
     * @return java.lang.String 跳转页面视图
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28 
     */
    @RequestMapping(value = "/doLogin")
    public String doLogin(@RequestParam String userCode, @RequestParam String userPassword, Model model, HttpServletRequest request, HttpSession session) {
        System.out.println("login ============ ");

        MultiValueMap<String, String> params= new LinkedMultiValueMap();
        params.put("userCode", Arrays.asList(userCode));
        params.put("userPassword", Arrays.asList(userPassword));
        //调用service方法，进行用户匹配
        User user = restTemplate.postForObject(PROVIDER_PREFIX + "/doLogin", params,User.class);
        if (null != user) {//登录成功
            System.out.println("登录成功");
            //放入session
            session.setAttribute(Constants.USER_SESSION, user);
            //页面跳转（frame.jsp）
            return "redirect:sys/main";
        } else {
            //页面跳转（login.jsp）带出提示信息--转发
            model.addAttribute("error", "用户名或密码不正确");
            return "index";
        }
    }

    @RequestMapping(value = "/sys/main")
    public String mian() {
        return "frame";
    }
    
    /**
     * 跳转至登录页面
     * @param 
     * @return java.lang.String  跳转页面视图
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28 
     */
    @RequestMapping(value = "/loginPage")
    public String loginPage() {
        return "index";
    }
    
    /**
     * 注销登录
     * @param session servlet-session
     * @return java.lang.String 登录视图
     * @author zhj
     * @creed: Talk is cheap,show me the code
     * @date 2019/3/28 
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(Constants.USER_SESSION);
        return "index";
    }
}
