package com.example.ITS.Controller;

import com.example.ITS.Entity.CourseResource;
import com.example.ITS.Entity.User;
import com.example.ITS.Service.UserService;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // 跳动登录页
    @GetMapping(value = {"/","/login"})
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model,HttpSession session) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        User foundUser = userService.login(user);
        if (foundUser != null) {
            session.setAttribute("user", foundUser);
            model.addAttribute("user", foundUser);
            // 根据用户类型跳转到对应的页面
            if ("student".equals(foundUser.getType())) {
                List<CourseResource> courseResources = foundUser.getStudent().getCourseResources();
                model.addAttribute("courseResources", courseResources);
                return "studentinfo";
            } else if ("teacher".equals(foundUser.getType())) {
                List<CourseResource> courseResources = foundUser.getTeacher().getCourseResources();
                model.addAttribute("courseResources", courseResources);
                return "teacherinfo";
            }else if ("admin".equals(foundUser.getType())) {
                return "adminview";
            }
        }
        return "login";
    }

    // 注册
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        User registeredUser;
        if ("student".equals(user.getType())) {
            registeredUser = userService.registerStudentUser(user);
        } else if ("teacher".equals(user.getType())) {
            registeredUser = userService.registerTeacherUser(user);
        } else {
            // 如果用户类型既不是 "student" 也不是 "teacher"，则返回到注册页面并显示错误消息
            return "register?error=invalid_type";
        }

        if (registeredUser != null) {
            // 如果注册成功，重定向到登录页面
            return "redirect:/login";
        } else {
            // 如果注册失败，返回到注册页面并显示错误消息
            return "register?error=registration_failed";
        }
    }

    // 注销
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 清除会话
        return "redirect:/login"; // 重定向到登录页面
    }

}

