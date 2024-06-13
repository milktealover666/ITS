package com.example.ITS.Service;

import com.example.ITS.Entity.User;
import com.example.ITS.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //所有用户
    public List<User> findAllUsers(){
        Iterable<User> users = userRepository.findAll();
        List<User> userList = new ArrayList<>();
        users.forEach(userList::add);
        return userList;
    }

    //查找用户
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    //更新自己信息
    public User updateSelfInfo(User user){
        return userRepository.save(user);
    }

    //删除
    public boolean deleteSelf(User user){
        userRepository.delete(user);
        return userRepository.findById(user.getId()).isEmpty();
    }


}
