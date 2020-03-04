package com.example.user.service.impl;

import com.example.user.domain.UserInfo;
import com.example.user.repository.UserRepository;
import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author : FuYu
 * @Description :
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserInfo findByOpenid(String openid) {
        return userRepository.findByOpenid(openid);
    }
}
