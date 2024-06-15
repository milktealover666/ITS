package com.example.ITS.Controller;

import com.example.ITS.Entity.User;
import com.example.ITS.Service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    LoginService loginService;

    // 跳动登录页
    @GetMapping(value = {"/","/login"})
    public String loginPage(){
        return "login";
    }

    @PostMapping(value = "/login")
    public String login(User user, HttpSession session, Model model){
        User loginUser = loginService.login(user);
        if (loginUser != null){
            session.setAttribute("user",loginUser);
            model.addAttribute("msg","登录成功");
            return "main";
        }
        model.addAttribute("msg","账号或密码错误");
        return "login";
    }
}
