package com.example.user.repository;


import com.example.user.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author : FuYu
 * @Description :
 */
@Repository
public interface UserRepository extends JpaRepository<UserInfo, String> {

    UserInfo findByOpenid(String openid);
}
