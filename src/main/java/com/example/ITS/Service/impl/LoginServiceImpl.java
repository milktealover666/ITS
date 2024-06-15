package com.example.ITS.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.ITS.Entity.User;
import com.example.ITS.Mapper.LoginMapper;
import com.example.ITS.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginMapper loginMapper;
    @Override
    public User login(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
        return loginMapper.selectList(queryWrapper).get(0);
    }
}
