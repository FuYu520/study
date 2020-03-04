package com.example.user.service;

import com.example.user.domain.UserInfo;

/**
 * @Author : FuYu
 * @Description :
 */
public interface UserService {

    UserInfo findByOpenid(String openid);
}
