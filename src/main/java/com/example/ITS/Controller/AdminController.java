package com.example.ITS.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.ITS.Entity.User;
import com.example.ITS.Service.UserService;

@Controller
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public String users(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/users";
    }

    @GetMapping("/user/resetPassword/{id}")
    public String resetPassword(@PathVariable("id") Long id) {
        userService.resetPassword(id, "123456");
        return "redirect:/users";
    }
}
