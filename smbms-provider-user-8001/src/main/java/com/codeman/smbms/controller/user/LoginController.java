package com.codeman.smbms.controller.user;

import com.codeman.smbms.entity.User;
import com.codeman.smbms.service.user.UserService;
import com.codeman.smbms.tools.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 张鸿杰
 * Date：2019-03-28
 * Time:8:38
 */

@RestController
@RequestMapping(value = "/login")
public class LoginController {
    @Autowired
    private UserService userService;
    
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
    public User doLogin(@RequestParam String userCode, @RequestParam String userPassword, HttpSession session) {
        //放入session
        User user = userService.login(userCode, userPassword);
        Optional.of(user).ifPresent(u -> session.setAttribute(Constants.USER_SESSION,u ));
        return user;
    }

}
