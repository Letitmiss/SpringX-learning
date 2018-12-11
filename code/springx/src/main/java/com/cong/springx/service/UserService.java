package com.cong.springx.service;

import com.cong.springx.mapper.UserMapper;
import com.cong.springx.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public int insertUser(User user) {
        userMapper.insert(user);
        return user.getUserId();
    }
}
