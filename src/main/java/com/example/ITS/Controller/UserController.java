package com.example.ITS.Controller;

import com.example.ITS.Entity.User;
import com.example.ITS.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public String Users(Model model){
        model.addAttribute("user",new User());
        Iterable<User> iterableUser = userService.findAllUsers();
        List<User> users = new ArrayList<>();
        iterableUser.forEach(users::add);

        model.addAttribute("users",users);
        return "users";
    }

    //id选择用户
    @GetMapping("/user/{id}")
    public String MyCourse(@PathVariable("id") long id,Model model){
        //ID获取用户
        User user = userService.findUserById(id);
        model.addAttribute("user",user);
        return "user";
    }

    //删除用户
    @PostMapping("/user/delete")
    public String DeleteUser(@RequestParam("id") long id,Model model){
        return "redirect:/users";
    }

    //更新用户
    @GetMapping("users/update/{id}")
    public String UpdateUser(@PathVariable("id") long id,Model model){
        User user  = userService.findUserById(id);
        model.addAttribute("user",user);
        return "updateUser";
    }

    @PostMapping("/users/update")
    public String updateUser(@ModelAttribute User user){
        userService.updateSelfInfo(user);
        return "redirect:/users/"+ user.getId();
    }
}
