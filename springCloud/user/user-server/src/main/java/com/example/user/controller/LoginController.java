package com.example.user.controller;

import com.example.user.constant.CookieConstant;
import com.example.user.constant.RedisConstant;
import com.example.user.domain.UserInfo;
import com.example.user.enums.ResultEnum;
import com.example.user.enums.RoleEnum;
import com.example.user.service.UserService;
import com.example.user.util.CookieUtil;
import com.example.user.util.ResultVOUtil;
import com.example.user.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * @Author : FuYu
 * @Description :
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/buyer")
    public ResultVO buyer(@RequestParam("openid")String openid, HttpServletResponse response){
        //1，openid和数据库里的数据匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null){
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }
        //2，判断角色
        if (!RoleEnum.BUYER.getCode().equals(userInfo.getRole())){
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }
        //4，cookie设置openid=abc
        CookieUtil.set(response, CookieConstant.OPENID, openid, CookieConstant.expire);
        return ResultVOUtil.success();
    }

    @GetMapping("/seller")
    public ResultVO sell(@RequestParam("openid")String openid, HttpServletRequest request, HttpServletResponse response){

        //判断是否登录
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null )
        {
            //从redis中取出值
            String  s = (String) redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue()));
            if (!StringUtils.isEmpty(s)){
                return ResultVOUtil.success();
            }
        }
        //1，openid和数据库里的数据匹配
        UserInfo userInfo = userService.findByOpenid(openid);
        if (userInfo == null){
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }
        //2，判断角色
        if (!RoleEnum.SELLER.getCode().equals(userInfo.getRole())){
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }
        //3，redis设置key=UUID，value=xyz
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE, token), openid, CookieConstant.expire, TimeUnit.SECONDS);

        //4，cookie设置openid=abc
        CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.expire);
        return ResultVOUtil.success();
    }
}
